package cn.com.betacat.parkerpal.iot.service.impl;

import cn.com.betacat.parkerpal.common.constants.IotConstant;
import cn.com.betacat.parkerpal.common.constants.RedisMessageConstant;
import cn.com.betacat.parkerpal.iot.domain.IotDevice;
import cn.com.betacat.parkerpal.iot.domain.IotDeviceStatus;
import cn.com.betacat.parkerpal.iot.domain.ParkingSpace;
import cn.com.betacat.parkerpal.iot.mapper.IotDeviceMapper;
import cn.com.betacat.parkerpal.iot.service.DeviceService;
import cn.com.betacat.parkerpal.iot.service.MqttService;
import cn.com.betacat.parkerpal.iot.service.RedisService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Slf4j
@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    private RedisService redisService;

    @Autowired
    private IotDeviceMapper iotDeviceMapper;

    @Autowired
    private MqttService mqttService;

    @Override
    public void setDeviceStatus(IotDeviceStatus deviceStatus) {
        String deviceStatusStr = JSONObject.toJSONString(deviceStatus);
        redisService.set(deviceStatus.getDeviceId() + RedisMessageConstant.IOT_DEVICE_STATUS, deviceStatusStr);
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
        IotDevice iotDevice = iotDeviceMapper.getByMacAddress(macAddress);
        if (iotDevice == null) {
            log.warn("设备配置下发失败，设备不存在：" + macAddress);
            JSONObject error = new JSONObject();
            error.put("type", IotConstant.MESSAGE_TYPE_CONFIGURATION_MAC_ADDRESS_NOT_FOUND);
            error.put("msg", IotConstant.MESSAGE_DETAIL_CONFIGURATION_MAC_ADDRESS_NOT_FOUND);
            mqttService.sendToSpaceSensor(macAddress, JSONObject.toJSONString(error));
            return;
        }

        JSONObject deviceConfig = (JSONObject) JSONObject.toJSON(iotDevice);

        // 添加消息类型
        deviceConfig.put("type", IotConstant.MESSAGE_TYPE_CONFIGURATION_REPLY);


        mqttService.sendToSpaceSensor(macAddress, JSONObject.toJSONString(deviceConfig));

    }


    @Override
    public IotDeviceStatus getIotStatus(String deviceId) {
        Object status = redisService.get(deviceId + RedisMessageConstant.IOT_DEVICE_STATUS);
        return status == null ? null : (IotDeviceStatus) status;
    }

}
