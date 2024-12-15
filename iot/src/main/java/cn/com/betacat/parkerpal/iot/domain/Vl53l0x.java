package cn.com.betacat.parkerpal.iot.domain;

import lombok.Data;

@Data
public class Vl53l0x {
    private String distance;
    private String status;
    private String time;
}
