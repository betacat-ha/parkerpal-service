package cn.com.betacat.parkerpal.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class SensorData {
    private String sensorId;
    private String macAddress;
    private List<RssiRecord> rssiList;
}
