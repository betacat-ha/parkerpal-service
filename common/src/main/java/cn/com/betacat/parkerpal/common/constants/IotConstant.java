package cn.com.betacat.parkerpal.common.constants;

import java.util.HashMap;
import java.util.Map;

public class IotConstant {
    public static final String MESSAGE_TYPE_SPACE_STATUS = "space_status";
    public static final String MESSAGE_TYPE_DEVICE_CONTROL = "device_control";
    public static final String MESSAGE_TYPE_WIFI_LIST = "Wi-Fi";
    public static final String MESSAGE_TYPE_CONFIGURATION_REQUEST = "configuration_request";
    public static final String MESSAGE_TYPE_CONFIGURATION_REPLY = "configuration_reply";
    public static final String MESSAGE_TYPE_CONFIGURATION_COMPLETE = "configuration_complete";
    public static final String MESSAGE_TYPE_CONFIGURATION_MAC_ADDRESS_NOT_FOUND = "configuration_mac_address_not_found";
    public static final String MESSAGE_DETAIL_CONFIGURATION_MAC_ADDRESS_NOT_FOUND = "未找到该MAC地址的配置信息";
    public static final String MESSAGE_CODE_SUCCESS = "200";

    public static final String MESSAGE_CODE_ERROR_DEFAULT = "500";
    public static final String MESSAGE_CODE_NOT_FOUND = "404";
    public static final String JSON_KEY_TYPE = "type";
    public static final String JSON_KEY_CODE = "code";
    public static final String JSON_KEY_MESSAGE = "message";
    public static final String JSON_KEY_DATA = "data";
    public static final String JSON_KEY_DEVICE_ID = "deviceId";
    public static final String JSON_KEY_MAC_ADDRESS = "deviceMacAddress";
    public static final String JSON_KEY_SPACE_ID = "spaceId";

    // 错误信息合集
    public static final Map<String, String> MESSAGE_CODE_MAP = new HashMap<String, String>() {{
        put(MESSAGE_CODE_ERROR_DEFAULT, "未知错误");
        put(MESSAGE_CODE_NOT_FOUND, "未找到");
    }};

}