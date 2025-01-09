package cn.com.betacat.parkerpal.core.service;

import cn.com.betacat.parkerpal.apicontracts.mapper.IotDeviceMapper;
import cn.com.betacat.parkerpal.apicontracts.mapper.SystemParkingSpaceMapper;
import cn.com.betacat.parkerpal.apicontracts.service.IotDeviceService;
import cn.com.betacat.parkerpal.common.constants.IotConstant;
import cn.com.betacat.parkerpal.common.constants.RedisMessageConstant;
import cn.com.betacat.parkerpal.common.utils.RedisUtil;
import cn.com.betacat.parkerpal.core.config.MqttGateway;
import cn.com.betacat.parkerpal.domain.entity.IotDevice;
import cn.com.betacat.parkerpal.domain.entity.IotDeviceStatus;
import cn.com.betacat.parkerpal.domain.entity.SystemParkingSpace;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
@Lazy
public class IotDeviceServiceImpl extends
    ServiceImpl<IotDeviceMapper, IotDevice> implements IotDeviceService {

    @Resource
    private MqttGateway mqttGateway;

    @Autowired
    private SystemParkingSpaceMapper systemParkingSpaceMapper;

    @Value("${mqtt.topic.subscribe}")
    private String subscribedTopic;

    @Value("${mqtt.topic.space-sensor.subPrefix}")
    private String spaceSensorPrefix;


    @Override
    public void setDeviceStatus(IotDeviceStatus deviceStatus) {
        String deviceStatusStr = JSONObject.toJSONString(deviceStatus);
        RedisUtil.set(deviceStatus.getId() + RedisMessageConstant.IOT_DEVICE_STATUS, deviceStatusStr);
    }


    @Override
    public void updateSpaceOccupiedStatus(IotDeviceStatus deviceStatus) {
        IotDeviceStatus statusFromRedis = getIotStatus(deviceStatus.getId());

        // 安全设定，如果设备记录不存在，不允许更新
        if (statusFromRedis == null) {
            // TODO: 调试方便，暂时允许未注册的设备，后续需要处理
            // log.warn(deviceStatus.getDeviceId() + "设备记录不存在，非法操作");
        }

        if (statusFromRedis == null) {
            setDeviceStatus(deviceStatus);
            return;
        }

        for (SystemParkingSpace newSpaceStatus : deviceStatus.getParkingSpaces()) {
            // 找到对应的车位

            SystemParkingSpace currentSpaceStatus = statusFromRedis.getParkingSpaces().stream().filter(parkingSpace -> parkingSpace.getId().equals(newSpaceStatus.getId())).findFirst().orElse(null);
            if (currentSpaceStatus == null) {
                // log.warn("未注册的车位" + currentSpaceStatus.getId());
                // TODO: 调试方便，暂时允许未注册的车位，后续需要处理
                statusFromRedis.getParkingSpaces().add(newSpaceStatus);
                continue;
            }

            // 更新车位状态
            currentSpaceStatus.setOccupied(newSpaceStatus.isOccupied());
            // currentSpaceStatus.setParkedTime(newSpaceStatus.getParkedTime());
            // currentSpaceStatus.setLeftTime(newSpaceStatus.getLeftTime());
        }

        setDeviceStatus(statusFromRedis);
    }

    /**
     * 通过设备ID和车位更新车位状态
     *
     * @param deviceId
     * @param parkingSpace
     */
    @Override
    public void updateSpaceOccupiedStatus(String deviceId, List<SystemParkingSpace> parkingSpace) {
        IotDeviceStatus status = getIotStatus(deviceId);
        if (status == null) {
            status = new IotDeviceStatus();
            status.setId(deviceId);
        }

        // 只更新车位占用状态

    }

    /**
     * 下发设备配置
     *
     * @param macAddress 设备MAC地址
     */
    @Override
    public void sendConfig(String macAddress) {
        IotDevice iotDevice = this.baseMapper.selectByMacAddressWithSpaces(macAddress);
        if (iotDevice == null) {
            log.warn("设备配置下发失败，设备不存在：" + macAddress);
            sendErrorToSpaceSensor(macAddress, IotConstant.MESSAGE_CODE_NOT_FOUND, IotConstant.MESSAGE_DETAIL_CONFIGURATION_MAC_ADDRESS_NOT_FOUND);
            return;
        }

        JSONObject deviceConfig = (JSONObject) JSONObject.toJSON(iotDevice);

        // 添加操作命令，告诉设备是配置
        deviceConfig.put(IotConstant.JSON_KEY_OPERATION, IotConstant.MESSAGE_OPERATION_CONFIGURATION);

        sendSuccessToSpaceSensor(macAddress, deviceConfig.toJSONString());
    }


    @Override
    public IotDeviceStatus getIotStatus(String deviceId) {
        Object status = RedisUtil.get(deviceId + RedisMessageConstant.IOT_DEVICE_STATUS);
        return status == null ? null : JSON.parseObject((String) status, IotDeviceStatus.class);
    }

    public void sendToSpaceSensor(String macAddress, String payload) {
        // 发送消息到指定设备
        sendToMqtt(subscribedTopic + spaceSensorPrefix + macAddress, 1, payload);
    }

    public void sendSuccessToSpaceSensor(String macAddress, String data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(IotConstant.JSON_KEY_CODE, IotConstant.MESSAGE_CODE_SUCCESS);
        jsonObject.put(IotConstant.JSON_KEY_DATA, data);
        sendToSpaceSensor(macAddress, jsonObject.toJSONString());
    }

    public void sendErrorToSpaceSensor(String macAddress, String data, String code, String message) {
        JSONObject result = new JSONObject();
        result.put(IotConstant.JSON_KEY_CODE, code);
        result.put(IotConstant.JSON_KEY_MESSAGE, message);
        result.put(IotConstant.JSON_KEY_DATA, data);

        sendToSpaceSensor(macAddress, result.toJSONString());
    }

    public void sendErrorToSpaceSensor(String macAddress, String code) {
        sendErrorToSpaceSensor(macAddress, null, code, IotConstant.MESSAGE_CODE_MAP.get(code));
    }

    public void sendErrorToSpaceSensor(String macAddress, String code, String message) {
        sendErrorToSpaceSensor(macAddress, null, code, message);
    }

    @Override
    public void sendToMqtt(String topic, int qos, String payload) {
        // 发送消息到指定主题
        mqttGateway.sendToMqtt(topic, qos, payload);
    }

    public void handleMessage(String topic, String payload) {
        String deviceId = null;
        JSONObject jsonObject = null;

        // 解析负载
        try {
            // 处理设备编号
            if (!topic.matches(subscribedTopic + "/(.*)")) {
                log.warn("丢弃消息：主题[" + topic + "]，负载：" + payload);
                return;
            }
            deviceId = topic.split("/")[2];
            log.info("获取设备编码为：" + deviceId + "，负载为：" + payload);

            // 处理接收到的消息
            jsonObject = JSON.parseObject(payload);
            if (jsonObject == null) {
                log.warn("丢弃消息：主题[" + topic + "]，负载：" + payload);
                return;
            }


            // 处理消息
            switch (jsonObject.getString(IotConstant.JSON_KEY_TYPE)) {
                case IotConstant.MESSAGE_TYPE_SPACE_STATUS -> {
                    // 处理设备状态
                    SystemParkingSpace[] parkingSpaceArr = new SystemParkingSpace[]{jsonObject.toJavaObject(SystemParkingSpace.class)};
                    List<SystemParkingSpace> parkingSpaces = List.of(parkingSpaceArr);
                    IotDeviceStatus deviceStatus = new IotDeviceStatus();
                    deviceStatus.setParkingSpaces(parkingSpaces);
                    deviceStatus.setId(jsonObject.getString(IotConstant.JSON_KEY_DEVICE_ID));
                    deviceStatus.setMacAddress(jsonObject.getString(IotConstant.JSON_KEY_MAC_ADDRESS));
                    updateSpaceOccupiedStatus(deviceStatus);
                }
                case IotConstant.MESSAGE_TYPE_WIFI_LIST -> {
                    // 处理Wi-Fi列表

                }
                case IotConstant.MESSAGE_TYPE_CONFIGURATION_REQUEST -> {
                    // 从负载中获取mac地址
                    String macAddress = (String) jsonObject.get(IotConstant.JSON_KEY_MAC_ADDRESS);
                    // 处理设备的配置请求
                    sendConfig(macAddress);
                }
                case IotConstant.MESSAGE_TYPE_CONFIGURATION_COMPLETE -> {
                    // 处理设备的配置回复

                }
                default -> log.warn("未知消息类型：" + jsonObject.getString(IotConstant.JSON_KEY_TYPE));
            }
        } catch (Exception e) {
            log.error("处理消息时发生错误：" + e.getMessage());
            log.debug("丢弃消息：主题[" + topic + "]，负载：" + payload);
        }
    }

}
