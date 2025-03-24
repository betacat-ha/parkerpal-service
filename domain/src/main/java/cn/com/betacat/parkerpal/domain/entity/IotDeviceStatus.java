package cn.com.betacat.parkerpal.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class IotDeviceStatus extends IotDevice {
    private String connectTime;
    private String disconnectTime;
    private LocalDateTime lastSeen;
    private String status;

    public IotDeviceStatus() {
    }

    public IotDeviceStatus(String id, List<SystemParkingSpace> parkingSpace) {
        this.setId(id);
        this.setParkingSpaces(parkingSpace);
    }
}
