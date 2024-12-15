package cn.com.betacat.parkerpal.iot.controller;

import cn.com.betacat.parkerpal.iot.domain.MqttMessage;
import cn.com.betacat.parkerpal.iot.gateway.MqttGateway;
import cn.com.betacat.parkerpal.iot.service.MqttService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MqttController {
 
    @Autowired
    private MqttService mqttService;
 
    @PostMapping("/send")
    public String send(@RequestBody MqttMessage mqttMessage) {
        // 发送消息到指定主题
        mqttService.sendToMqtt(mqttMessage.getTopic(), 1, mqttMessage.getContent());
        return "send topic: " + mqttMessage.getTopic()+ ", message : " + mqttMessage.getContent();
    }
}
