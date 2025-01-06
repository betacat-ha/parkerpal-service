package cn.com.betacat.parkerpal.core.config;

import cn.com.betacat.parkerpal.apicontracts.service.MqttService;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@EnableIntegration
@IntegrationComponentScan
public class MqttConfig {
 
    /**
     * 出站-生产者
     */
    public static final String CHANNEL_NAME_OUT = "mqttOutboundChannel";
 
    /**
     * 入站-消费者
     */
    public static final String CHANNEL_NAME_IN = "mqttInputChannel";
 
    @Value("${mqtt.username}")
    private String username;
 
    @Value("${mqtt.password}")
    private String password;
 
    @Value("${mqtt.brokerUrl}")
    private String hostUrl;
 
    @Value("${mqtt.client.id}")
    private String clientId;
 
    @Value("${mqtt.topic.default}")
    private String defaultTopic;
 
    @Value("${mqtt.qos}")
    private int qos;
 
 
    // 客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息
    private static final byte[] WILL_DATA;
 
    static {
        WILL_DATA = "offline".getBytes();
    }
 
    // 消费消息
 
    /**
     * 创建MqttPahoClientFactory，设置MQTT Broker连接属性，如果使用SSL验证，也在这里设置。
     * @return factory
     */
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        // 设置连接的用户名
        options.setUserName(username);
        // 设置连接的密码
        options.setPassword(password.toCharArray());
        // 设置代理端的URL地址，可以是多个
        options.setServerURIs(new String[]{hostUrl});
        // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送心跳判断客户端是否在线
        options.setKeepAliveInterval(20);
        // 设置连接超时时间
        options.setConnectionTimeout(30);
        // 设置断开是否自动重联
        options.setAutomaticReconnect(true);
        // 设置“遗嘱”消息的话题，若客户端与服务器之间的连接意外中断，服务器将发布客户端的“遗嘱”消息。
//        options.setWill("willTopic", WILL_DATA, 2, false);
        factory.setConnectionOptions(options);
        return factory;
    }
 
    /**
     * 入站通道
     */
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }
 
    /**
     * 入站
     */
    @Bean
    public MessageProducer inbound() {
        // Paho客户端消息驱动通道适配器，主要用来订阅主题
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(clientId + "-consume",
                mqttClientFactory(), defaultTopic);
        adapter.setCompletionTimeout(5000);
 
        // Paho消息转换器
        DefaultPahoMessageConverter defaultPahoMessageConverter = new DefaultPahoMessageConverter();
        // 按字节接收消息
//        defaultPahoMessageConverter.setPayloadAsBytes(true);
        adapter.setConverter(defaultPahoMessageConverter);
        adapter.setQos(qos); // 设置QoS
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }
 
    @Bean
    // ServiceActivator注解表明：当前方法用于处理MQTT消息，inputChannel参数指定了用于消费消息的channel。
    @ServiceActivator(inputChannel = CHANNEL_NAME_IN)
    public MessageHandler handler(MqttService mqttService) {
        return message -> {
            String payload = message.getPayload().toString();
            // byte[] bytes = (byte[]) message.getPayload(); // 收到的消息是字节格式
            String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
            mqttService.handleMessage(topic, payload);
        };
    }
 
    // 发送消息
    /**
     * 出站通道
     */
    @Bean
    public MessageChannel mqttOutboundChannel() {
        return new DirectChannel();
    }
 
    /**
     * 出站
     */
    @Bean
    @ServiceActivator(inputChannel = CHANNEL_NAME_OUT)
    public MessageHandler outbound() {
 
        // 发送消息和消费消息Channel可以使用相同MqttPahoClientFactory
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(clientId + "-produce", mqttClientFactory());
        // 如果设置成true，即异步，发送消息时将不会阻塞。
        messageHandler.setAsync(true);
        messageHandler.setDefaultTopic(defaultTopic);
        // 设置默认QoS
        messageHandler.setDefaultQos(qos);
 
        // Paho消息转换器
        DefaultPahoMessageConverter defaultPahoMessageConverter = new DefaultPahoMessageConverter();
 
        // defaultPahoMessageConverter.setPayloadAsBytes(true); // 发送默认按字节类型发送消息
        messageHandler.setConverter(defaultPahoMessageConverter);
        return messageHandler;
    }
 
}
