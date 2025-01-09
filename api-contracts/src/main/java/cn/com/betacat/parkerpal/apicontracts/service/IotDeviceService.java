package cn.com.betacat.parkerpal.apicontracts.service;


import cn.com.betacat.parkerpal.domain.entity.IotDevice;
import cn.com.betacat.parkerpal.domain.entity.IotDeviceStatus;
import cn.com.betacat.parkerpal.domain.entity.SystemParkingSpace;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IotDeviceService extends IService<IotDevice> {
    /**
     * 设置设备状态
     * @param deviceStatus 设备状态
     */
    void setDeviceStatus(IotDeviceStatus deviceStatus);

    /**
     * 更新设备状态
     * @param deviceStatus 设备状态
     */
    void updateSpaceOccupiedStatus(IotDeviceStatus deviceStatus);

    /**
     * 通过设备ID和车位更新车位状态
     */
    void updateSpaceOccupiedStatus(String deviceId, List<SystemParkingSpace> parkingSpace);

    // void setWifiList(String deviceId, String wifiList);

    /**
     * 下发设备配置
     * @param macAddress 设备MAC地址
     */
    void sendConfig(String macAddress);

    /**
     * 获取设备状态
     * @param deviceId 设备ID
     * @return 设备状态
     */
    IotDeviceStatus getIotStatus(String deviceId);

    void sendToMqtt(String topic, int qos, String payload);
    void handleMessage(String topic, String payload);
}
