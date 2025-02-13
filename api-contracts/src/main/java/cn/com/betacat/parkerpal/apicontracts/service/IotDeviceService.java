package cn.com.betacat.parkerpal.apicontracts.service;


import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import cn.com.betacat.parkerpal.domain.entity.IotDevice;
import cn.com.betacat.parkerpal.domain.entity.IotDeviceStatus;
import cn.com.betacat.parkerpal.domain.entity.SystemParkingSpace;
import cn.com.betacat.parkerpal.domain.query.IotDeviceQuery;
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

    /**
     * 通过设备ID获取设备状态
     * @param deviceId 设备ID
     * @return 设备状态
     */
    // void setWifiList(String deviceId, String wifiList);

    /**
     * 下发设备配置
     * @param macAddress 设备MAC地址
     */
    void sendConfig(String macAddress);

    /**
     * 从REDIS获取设备状态
     * @param deviceId 设备ID
     * @return 设备状态
     */
    IotDeviceStatus getIotStatus(String deviceId);

    /**
     * 通过deviceId列表从REDIS获取设备状态列表
     *
     * @param deviceId 设备ID列表
     * @return 设备状态列表
     */
    List<IotDeviceStatus> getIotStatusList(List<String> deviceId);

    /**
     * 从持久化存储获取设备信息
     * @param deviceId 设备ID
     * @return 设备状态
     */
    IotDevice getIotDevice(String deviceId);

    /**
     * 从持久化存储获取设备列表
     *
     * @param query 查询条件
     * @return 分页数据
     */
    PageInfoRespQuery getPageList(IotDeviceQuery query);

    /**
     * 新增设备
     *
     * @param newDevice 设备信息
     * @return 新的设备信息
     */
    IotDevice addDevice(IotDevice newDevice);

    /**
     * 更新设备信息
     *
     * @param editedDevice 设备信息
     * @return 更新后的设备信息
     */
    IotDevice updateDevice(IotDevice editedDevice);

    /**
     * 删除设备
     *
     * @param deviceIds 设备ID列表
     */
    void deleteDevice(List<String> deviceIds);

    /**
     * 新增或编辑设备
     *
     * @param device 设备信息
     * @return 新的设备信息
     */
    IotDevice addOrUpdateDevice(IotDevice device);

    /**
     * 发送MQTT消息
     * @param topic 主题
     * @param qos QoS
     * @param payload 消息内容
     */
    void sendToMqtt(String topic, int qos, String payload);

    /**
     * 处理MQTT消息
     * @param topic 主题
     * @param payload 消息内容
     */
    void handleMessage(String topic, String payload);
}
