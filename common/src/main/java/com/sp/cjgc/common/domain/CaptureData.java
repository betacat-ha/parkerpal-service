package com.sp.cjgc.common.domain;

import java.io.Serializable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author: Zoey
 * @Since: 2024-08-15 09:16:17
 * @Description:
 */
@Getter
@Setter
@NoArgsConstructor
public class CaptureData implements Serializable {
    private static final long serialVersionUID = -40856612465238347L;

    private String deviceIp;     //设备IP
    private String devicePort;   //设备端口
    private String licensePlate; //内地车牌号
    private String time;         //抓怕时间，时间格式: yyyy-MM-dd HH:mm:ss
}
