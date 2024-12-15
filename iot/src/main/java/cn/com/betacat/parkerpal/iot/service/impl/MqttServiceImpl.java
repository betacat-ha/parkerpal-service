package cn.com.betacat.parkerpal.iot.service.impl;

import cn.com.betacat.parkerpal.iot.gateway.MqttGateway;
import cn.com.betacat.parkerpal.iot.service.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MqttServiceImpl implements MqttService {
    @Resource
    private MqttGateway mqttGateway;

    @Value("${mqtt.topic.subscribe}")
    private String subscribedTopic;

    @Override
    public void sendToMqtt(String topic, int qos, String payload) {
        // 发送消息到指定主题
        mqttGateway.sendToMqtt(topic, qos, payload);
    }

    @Override
    public void handleMessage(String topic, String payload) {
        try {
            // 处理接收到的消息
            if (topic.matches(subscribedTopic + "/(.*)")) {
                String deviceCode = topic.split("/")[2];
                System.out.println("获取设备编码为：" + deviceCode);
                System.out.println("负载为：" + payload);
            } else {
                System.out.println("丢弃消息：主题[" + topic + "]，负载：" + payload);
            }
        } catch (Exception e) {
            System.out.println("丢弃消息：主题[" + topic + "]，负载：" + payload);
            e.printStackTrace();
        }
    }
}
