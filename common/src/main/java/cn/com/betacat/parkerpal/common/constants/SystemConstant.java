package cn.com.betacat.parkerpal.common.constants;

import java.util.Map;

public class SystemConstant {
    // 车位传感器状态
    public static final String PARKING_SPACE_STATUS_FIXED = "0";
    public static final String PARKING_SPACE_STATUS_OCCUPIED = "1";
    public static final String PARKING_SPACE_STATUS_FREE = "2";
    public static final String PARKING_SPACE_STATUS_RESERVED = "3";
    public static final String PARKING_SPACE_STATUS_FAULT = "-1";
    public static final String PARKING_SPACE_STATUS_DISABLED = "-2";

    public static final int PARKING_SPACE_RESERVATION_TIME = 15;

    // 车位预约状态
    public static final String PARKING_SPACE_RESERVATION_STATUS_RESERVED = "1";
    public static final String PARKING_SPACE_RESERVATION_STATUS_CANCELED = "2";
    public static final String PARKING_SPACE_RESERVATION_STATUS_EXPIRED = "3";


    public static Map<Integer, String> PARKING_SPACE_STATUS = Map.of(

    );
}
