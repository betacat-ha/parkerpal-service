package cn.com.betacat.parkerpal.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IotDeviceStatus extends IotDevice {
    private String connectTime;
    private String disconnectTime;
    private LocalDateTime lastSeen;
    private String status;
}
