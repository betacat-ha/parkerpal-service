package cn.com.betacat.parkerpal.iot.service.impl;

import cn.com.betacat.parkerpal.common.constants.IotConstant;
import cn.com.betacat.parkerpal.iot.domain.IotDeviceStatus;
import cn.com.betacat.parkerpal.iot.domain.ParkingSpace;
import cn.com.betacat.parkerpal.iot.gateway.MqttGateway;
import cn.com.betacat.parkerpal.iot.service.DeviceService;
import cn.com.betacat.parkerpal.iot.service.MqttService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Slf4j
@Service
public class MqttServiceImpl implements MqttService {
    @Resource
    private MqttGateway mqttGateway;

    @Autowired
    private DeviceService deviceService;

    @Value("${mqtt.topic.subscribe}")
    private String subscribedTopic;

    @Value("${mqtt.topic.space-sensor.prefix}")
    private String spaceSensorPrefix;

    @Override
    public void sendToMqtt(String topic, int qos, String payload) {
        // 发送消息到指定主题
        mqttGateway.sendToMqtt(topic, qos, payload);
    }

    @Override
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
                    ParkingSpace[] parkingSpace = new ParkingSpace[]{jsonObject.toJavaObject(ParkingSpace.class)};
                    IotDeviceStatus deviceStatus = new IotDeviceStatus();
                    deviceStatus.setParkingSpace(parkingSpace);
                    deviceStatus.setDeviceId(jsonObject.getString(IotConstant.JSON_KEY_DEVICE_ID));
                    // deviceStatus.setMacAddress(jsonObject.getString(IotConstant.JSON_KEY_MAC_ADDRESS));
                    deviceService.updateSpaceStatus(deviceStatus);
                }
                case IotConstant.MESSAGE_TYPE_WIFI_LIST -> {
                    // 处理Wi-Fi列表

                }
                case IotConstant.MESSAGE_TYPE_CONFIGURATION_REQUEST -> {
                    // 从负载中获取mac地址
                    String macAddress = (String) jsonObject.get(IotConstant.JSON_KEY_MAC_ADDRESS);
                    // 处理设备的配置请求
                    deviceService.sendConfig(macAddress);
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
        // redisService.setIotStatus(deviceId, payload);

    }
}
