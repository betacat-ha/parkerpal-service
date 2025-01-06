package cn.com.betacat.parkerpal.core.service;

import cn.com.betacat.parkerpal.apicontracts.mapper.IotDeviceMapper;
import cn.com.betacat.parkerpal.apicontracts.service.IotDeviceService;
import cn.com.betacat.parkerpal.apicontracts.service.MqttService;
import cn.com.betacat.parkerpal.common.constants.IotConstant;
import cn.com.betacat.parkerpal.common.constants.RedisMessageConstant;
import cn.com.betacat.parkerpal.common.utils.RedisUtil;
import cn.com.betacat.parkerpal.domain.entity.IotDevice;
import cn.com.betacat.parkerpal.domain.entity.IotDeviceStatus;
import cn.com.betacat.parkerpal.domain.entity.ParkingSpace;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IotDeviceServiceImpl extends
    ServiceImpl<IotDeviceMapper, IotDevice> implements IotDeviceService {

    @Autowired
    private MqttService mqttService;

    @Value("${mqtt.topic.subscribe}")
    private String subscribedTopic;

    @Value("${mqtt.topic.space-sensor.prefix}")
    private String spaceSensorPrefix;

    @Override
    public void setDeviceStatus(IotDeviceStatus deviceStatus) {
        String deviceStatusStr = JSONObject.toJSONString(deviceStatus);
        RedisUtil.set(deviceStatus.getDeviceId() + RedisMessageConstant.IOT_DEVICE_STATUS, deviceStatusStr);
    }


    @Override
    public void updateSpaceStatus(IotDeviceStatus deviceStatus) {
        IotDeviceStatus status = getIotStatus(deviceStatus.getDeviceId());

        // 安全设定，如果设备记录不存在，不允许更新
        if (status == null) {
            // TODO: 调试方便，暂时允许未注册的设备，后续需要处理
            // log.warn(deviceStatus.getDeviceId() + "设备记录不存在，非法操作");
        }

        if (status == null) {
            setDeviceStatus(deviceStatus);
            return;
        }

        for (ParkingSpace newSpaceStatus : deviceStatus.getParkingSpace()) {
            // 找到对应的车位
            ParkingSpace currentSpaceStatus = Arrays.stream(deviceStatus.getParkingSpace()).filter(parkingSpace -> parkingSpace.getId().equals(newSpaceStatus.getId())).findFirst().orElse(null);
            if (currentSpaceStatus == null) {
                // log.warn("未注册的车位" + currentSpaceStatus.getId());
                // TODO: 调试方便，暂时允许未注册的车位，后续需要处理
                Arrays.asList(status.getParkingSpace()).add(newSpaceStatus);
                continue;
            }

            // 更新车位状态
            currentSpaceStatus.setOccupied(newSpaceStatus.isOccupied());
            // currentSpaceStatus.setParkedTime(newSpaceStatus.getParkedTime());
            // currentSpaceStatus.setLeftTime(newSpaceStatus.getLeftTime());
        }

        setDeviceStatus(status);
    }

    /**
     * 下发设备配置
     *
     * @param macAddress 设备MAC地址
     */
    @Override
    public void sendConfig(String macAddress) {
        IotDevice iotDevice = this.baseMapper.getByMacAddress(macAddress);
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
        return status == null ? null : (IotDeviceStatus) status;
    }

    public void sendToSpaceSensor(String macAddress, String payload) {
        // 发送消息到指定设备
        mqttService.sendToMqtt(subscribedTopic + "/" + spaceSensorPrefix + macAddress, 1, payload);
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

}
