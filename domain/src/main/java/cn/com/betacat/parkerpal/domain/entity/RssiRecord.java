package cn.com.betacat.parkerpal.domain.entity;

import lombok.Data;

@Data
public class RssiRecord {
    private String sourceMacAddress;
    private String sensorMacAddress;
    private long timestamp;
    private int rssi;
}
