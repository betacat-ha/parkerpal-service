package cn.com.betacat.parkerpal.apicontracts.service;


public interface MqttService {
    void sendToMqtt(String topic, int qos, String payload);
    void handleMessage(String topic, String payload);
}
