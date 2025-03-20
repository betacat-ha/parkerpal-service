package cn.com.betacat.parkerpal.core.service;

import cn.com.betacat.parkerpal.apicontracts.mapper.IotDeviceMapper;
import cn.com.betacat.parkerpal.apicontracts.mapper.SystemParkingSpaceMapper;
import cn.com.betacat.parkerpal.apicontracts.service.IotDeviceService;
import cn.com.betacat.parkerpal.apicontracts.service.SystemParkingSpaceService;
import cn.com.betacat.parkerpal.common.constants.IotConstant;
import cn.com.betacat.parkerpal.common.constants.RedisMessageConstant;
import cn.com.betacat.parkerpal.common.constants.SystemConstant;
import cn.com.betacat.parkerpal.common.constants.WebSocketConstant;
import cn.com.betacat.parkerpal.common.utils.RedisUtil;
import cn.com.betacat.parkerpal.common.utils.websocket.WebSocketResult;
import cn.com.betacat.parkerpal.common.utils.websocket.WebSocketServer;
import cn.com.betacat.parkerpal.core.config.MqttGateway;
import cn.com.betacat.parkerpal.core.exception.BizException;
import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import cn.com.betacat.parkerpal.domain.base.PageInfoUtil;
import cn.com.betacat.parkerpal.domain.entity.*;
import cn.com.betacat.parkerpal.domain.enums.RespEnum;
import cn.com.betacat.parkerpal.domain.query.IotDeviceQuery;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.runtime.directive.Foreach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@Lazy
public class IotDeviceServiceImpl extends
    ServiceImpl<IotDeviceMapper, IotDevice> implements IotDeviceService {

    @Resource
    private MqttGateway mqttGateway;

    @Autowired
    private SystemParkingSpaceService systemParkingSpaceService;

    @Value("${mqtt.topic.subscribe}")
    private String subscribedTopic;

    @Value("${mqtt.topic.space-sensor.subPrefix}")
    private String spaceSensorPrefix;


    @Override
    public void setDeviceStatus(IotDeviceStatus deviceStatus) {
        String deviceStatusStr = JSONObject.toJSONString(deviceStatus);
        RedisUtil.set(deviceStatus.getId() + RedisMessageConstant.IOT_DEVICE_STATUS, deviceStatusStr);
    }

    /**
     * 缓存Wi-Fi信号列表
     *
     * @param sensorData 传感器数据
     */
    public void updateWifiList(SensorData sensorData) {
        /* 传感器数据格式
         * {
         *  "macAddress": "00:00:00:00:00:00", // 传感器MAC地址
         * "rssiList": [{
         *   "sourceMacAddress": "00:00:00:00:00:00",
         *   "sensorMacAddress": null,
         *   "rssi": -50,
         *   "timestamp": 1631577600000
         * }]
         * }
         */
        List<RssiRecord> rssiList = sensorData.getRssiList();
        if (rssiList == null) {
            return;
        }

        String sensorMacAddress = sensorData.getMacAddress();

        for (RssiRecord rssiRecord : rssiList) {
            String sourceMacAddress = rssiRecord.getSourceMacAddress();
            Object obj = RedisUtil.get(sourceMacAddress + RedisMessageConstant.IOT_DEVICE_WIFI_RSSI);
            SignalSource signalSource = obj == null ? new SignalSource(sourceMacAddress) : JSON.parseObject((String) obj, SignalSource.class);
            List<RssiRecord> currentRssiList = signalSource.getRssiRecords();

            // 如果列表不存在，创建新的列表
            if (currentRssiList == null) {
                currentRssiList = List.of(rssiRecord);
            }

            // 检查记录是否存在
            RssiRecord currentRecord = currentRssiList.stream().filter(record -> record.getSensorMacAddress().equals(rssiRecord.getSensorMacAddress())).findFirst().orElse(null);

            // 减少冗余
            rssiRecord.setSourceMacAddress(null);

            // 设置记录
            rssiRecord.setSensorMacAddress(sensorMacAddress);

            // 如果记录不存在，添加新的记录
            if (currentRecord == null) {
                currentRssiList.add(rssiRecord);
            } else {
                // 如果记录存在，更新记录
                currentRecord.setRssi(rssiRecord.getRssi());
                currentRecord.setTimestamp(rssiRecord.getTimestamp());
            }

            // 更新列表
            signalSource.setRssiRecords(currentRssiList);

            // 更新缓存
            RedisUtil.set(sourceMacAddress + RedisMessageConstant.IOT_DEVICE_WIFI_RSSI, JSON.toJSONString(signalSource));
        }
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
        }

        for (SystemParkingSpace newSpaceStatus : deviceStatus.getParkingSpaces()) {
            // 找到对应的车位

            SystemParkingSpace currentSpaceStatus = systemParkingSpaceService.selectBy(newSpaceStatus.getId());
            if (currentSpaceStatus == null) {
                log.error("车位不存在：" + newSpaceStatus.getId());
                continue;
            }

            // 更新车位状态
            currentSpaceStatus.setOccupied(newSpaceStatus.isOccupied());
            // currentSpaceStatus.setParkedTime(newSpaceStatus.getParkedTime());
            // currentSpaceStatus.setLeftTime(newSpaceStatus.getLeftTime());
            systemParkingSpaceService.updateBy(currentSpaceStatus);

            // 推送状态给前端
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", currentSpaceStatus.getId());
            jsonObject.put("status", currentSpaceStatus.isOccupied() ? SystemConstant.PARKING_SPACE_STATUS_OCCUPIED : SystemConstant.PARKING_SPACE_STATUS_FREE);
            jsonObject.put("fnum", currentSpaceStatus.getFnum());
            WebSocketServer.sendMessageByTopic(WebSocketResult.success(JSON.toJSONString(jsonObject)), WebSocketConstant.TOPIC_SPACE_STATUS);

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

    /**
     * 从REDIS获取设备状态
     *
     * @param deviceId 设备ID
     * @return 设备状态
     */
    @Override
    public IotDeviceStatus getIotStatus(String deviceId) {
        Object status = RedisUtil.get(deviceId + RedisMessageConstant.IOT_DEVICE_STATUS);
        return status == null ? null : JSON.parseObject((String) status, IotDeviceStatus.class);
    }

    /**
     * 从REDIS获取设备状态列表
     *
     * @param deviceId 设备ID列表
     * @return 设备状态列表
     */
    @Override
    public List<IotDeviceStatus> getIotStatusList(List<String> deviceId) {
        return deviceId.stream().map(this::getIotStatus).toList();
    }

    /**
     * 从持久化存储获取设备信息
     *
     * @param deviceId 设备ID
     * @return 设备状态
     */
    @Override
    @Cacheable(value = "iotDevice", key = "#root.methodName")
    public IotDevice getIotDevice(String deviceId) {
        return this.baseMapper.selectByMacAddressWithSpaces(deviceId);
    }

    /**
     * 从持久化存储获取设备列表
     *
     * @param query 查询条件
     * @return 分页数据
     */
    @Override
    public PageInfoRespQuery getPageList(IotDeviceQuery query) {
        // 赋值页码
        PageInfoUtil.pageReq(query);
        // 统计总数
        Long total = this.baseMapper.countTotal(query);
        // 查询列表
        List<IotDevice> list = this.baseMapper.getAllWithPage(query);
        // 返回分页数据
        return PageInfoUtil.pageResp(list, query, total);
    }

    /**
     * 新增设备
     *
     * @param newDevice 设备信息
     * @return 新的设备信息
     */
    public IotDevice addDevice(IotDevice newDevice) {
        // 根据MAC地址查询设备
        IotDevice deviceFromDataBase = this.baseMapper.selectByMacAddress(newDevice.getMacAddress());
        // 判断设备是否存在
        if (deviceFromDataBase != null) throw new BizException(RespEnum.FAILURE.getCode(), "设备已存在");

        // 设置状态
        newDevice.setIsDeleted(Byte.valueOf("0"));
        Date currentTime = new Date();
        newDevice.setCreateTime(currentTime);
        newDevice.setUpdateTime(currentTime);

        this.save(newDevice);
        return this.baseMapper.selectByMacAddress(newDevice.getMacAddress());
    }

    /**
     * 更新设备信息
     *
     * @param editedDevice 设备信息
     * @return 更新后的设备信息
     */
    public IotDevice updateDevice(IotDevice editedDevice) {
        // 根据MAC地址查询设备
        IotDevice deviceFromDataBase = this.baseMapper.selectByMacAddress(editedDevice.getMacAddress());
        // 判断设备是否存在
        if (deviceFromDataBase == null) throw new BizException(RespEnum.FAILURE.getCode(), "设备不存在");

        // 设置状态
        editedDevice.setUpdateTime(new Date());

        this.updateById(editedDevice);
        return this.baseMapper.selectByMacAddress(editedDevice.getMacAddress());
    }

    /**
     * 删除设备
     *
     * @param deviceIds 设备ID列表
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDevice(List<String> deviceIds) {
        for (String deviceId : deviceIds) {
            deleteDevice(deviceId);
        }
    }

    /**
     * 删除设备
     *
     * @param deviceId 设备ID
     */
    public void deleteDevice(String deviceId) {
        // 根据ID查询设备
        IotDevice deviceFromDataBase = this.baseMapper.selectById(deviceId);
        // 判断设备是否存在
        if (deviceFromDataBase == null) throw new BizException(RespEnum.FAILURE.getCode(), "设备不存在");

        this.removeById(deviceId);
    }

    /**
     * 新增或编辑设备
     *
     * @param device 设备信息
     * @return 新的设备信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public IotDevice addOrUpdateDevice(IotDevice device) {
        // TODO: 此处应不允许关联车位，后续需要处理
        device.setUpdateTime(new Date());
        if (device.getId() == null) {
            return addDevice(device);
        } else {
            return updateDevice(device);
        }
    }

    /**
     * 发送消息到指定设备
     *
     * @param macAddress 设备MAC地址
     * @param payload    消息内容
     */
    public void sendToSpaceSensor(String macAddress, String payload) {
        // 发送消息到指定设备
        sendToMqtt(subscribedTopic + spaceSensorPrefix + macAddress, 1, payload);
    }

    public void sendSuccessToSpaceSensor(String macAddress, String data) {
        JSONObject result = new JSONObject();
        result.put(IotConstant.JSON_KEY_CODE, IotConstant.MESSAGE_CODE_SUCCESS);
        result.put(IotConstant.JSON_KEY_DATA, JSONObject.parseObject(data));
        sendToSpaceSensor(macAddress, result.toJSONString());
    }

    public void sendErrorToSpaceSensor(String macAddress, String data, String code, String message) {
        JSONObject result = new JSONObject();
        result.put(IotConstant.JSON_KEY_CODE, code);
        result.put(IotConstant.JSON_KEY_MESSAGE, message);
        result.put(IotConstant.JSON_KEY_DATA, JSONObject.parseObject(data));

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
                    // 处理单个车位状态
                    SystemParkingSpace[] parkingSpaceArr = new SystemParkingSpace[]{jsonObject.toJavaObject(SystemParkingSpace.class)};
                    List<SystemParkingSpace> parkingSpaces = List.of(parkingSpaceArr);
                    IotDeviceStatus deviceStatus = new IotDeviceStatus(jsonObject.getString(IotConstant.JSON_KEY_DEVICE_ID), parkingSpaces);
                    updateSpaceOccupiedStatus(deviceStatus);
                }
                case IotConstant.MESSAGE_TYPE_SPACE_STATUS_LIST -> {
                    // 处理多个车位状态
                    List<SystemParkingSpace> parkingSpaces = jsonObject.getJSONArray(IotConstant.JSON_KEY_PARKING_SPACES).toJavaList(SystemParkingSpace.class);
                    IotDeviceStatus deviceStatus = new IotDeviceStatus(jsonObject.getString(IotConstant.JSON_KEY_DEVICE_ID), parkingSpaces);
                    updateSpaceOccupiedStatus(deviceStatus);
                }
                case IotConstant.MESSAGE_TYPE_WIFI_LIST -> {
                    // 处理Wi-Fi信号列表
                    List<RssiRecord> rssiList = jsonObject.getJSONArray(IotConstant.JSON_KEY_RSSI_LIST).toJavaList(RssiRecord.class);
                    SensorData sensorData = new SensorData();
                    sensorData.setMacAddress(jsonObject.getString(deviceId));
                    sensorData.setRssiList(rssiList);
                    updateWifiList(sensorData);
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
