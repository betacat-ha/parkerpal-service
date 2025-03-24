package cn.com.betacat.parkerpal.domain.entity;

import lombok.Data;

import java.util.List;

@Data
public class SignalSource {
    private String id;
    private String macAddress;
    private List<RssiRecord> rssiRecords;

    /**
     * 构造函数
     * @param macAddress 信号源MAC地址
     */
    public SignalSource(String macAddress) {
        this.macAddress = macAddress;
    }
}
