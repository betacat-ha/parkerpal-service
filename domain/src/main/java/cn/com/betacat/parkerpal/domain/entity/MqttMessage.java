package cn.com.betacat.parkerpal.domain.entity;

import lombok.Data;
import java.io.Serializable;

@Data
public class MqttMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String topic;
    private String content;
    private int qos;
}