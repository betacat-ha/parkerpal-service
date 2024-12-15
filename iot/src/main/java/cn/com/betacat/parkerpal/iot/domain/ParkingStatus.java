package cn.com.betacat.parkerpal.iot.domain;

import lombok.Data;

@Data
public class ParkingStatus {
    private String spaceId;
    private String status;
    private boolean isReserved;
    private boolean isParked;
}
