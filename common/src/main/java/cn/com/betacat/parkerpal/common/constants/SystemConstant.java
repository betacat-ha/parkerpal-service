package cn.com.betacat.parkerpal.common.constants;

import java.util.Map;

public class SystemConstant {
    public static final String PARKING_SPACE_STATUS_FIXED = "0";
    public static final String PARKING_SPACE_STATUS_OCCUPIED = "1";
    public static final String PARKING_SPACE_STATUS_FREE = "2";
    public static final String PARKING_SPACE_STATUS_RESERVED = "3";
    public static final String PARKING_SPACE_STATUS_FAULT = "-1";
    public static final String PARKING_SPACE_STATUS_DISABLED = "-2";

    public static Map<Integer, String> PARKING_SPACE_STATUS = Map.of(

    );
}
