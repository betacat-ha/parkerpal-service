package cn.com.betacat.parkerpal.apicontracts.service;


import cn.com.betacat.parkerpal.domain.entity.IotDevice;
import cn.com.betacat.parkerpal.domain.entity.IotDeviceStatus;
import com.baomidou.mybatisplus.extension.service.IService;

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
    void updateSpaceStatus(IotDeviceStatus deviceStatus);

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
}
