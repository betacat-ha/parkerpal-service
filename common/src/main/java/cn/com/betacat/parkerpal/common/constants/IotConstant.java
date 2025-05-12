package cn.com.betacat.parkerpal.common.constants;

import java.util.HashMap;
import java.util.Map;

public class IotConstant {
    public static final String MESSAGE_TYPE_SPACE_STATUS = "space_status";
    public static final String MESSAGE_TYPE_SPACE_STATUS_LIST = "space_status_list";
    public static final String MESSAGE_TYPE_DEVICE_CONTROL = "device_control";
    public static final String MESSAGE_TYPE_WIFI_LIST = "Wi-Fi";
    public static final String MESSAGE_TYPE_DEVICE_STATUS = "device_status";
    public static final String MESSAGE_TYPE_SNIFFER_INFO = "SI";
    public static final String MESSAGE_TYPE_CONFIGURATION_REQUEST = "configuration_request";
    public static final String MESSAGE_TYPE_CONFIGURATION_REPLY = "configuration_reply";
    public static final String MESSAGE_TYPE_CONFIGURATION_COMPLETE = "configuration_complete";
    public static final String MESSAGE_TYPE_CONFIGURATION_MAC_ADDRESS_NOT_FOUND = "configuration_mac_address_not_found";
    public static final String MESSAGE_TYPE_OFFLINE = "offline";
    public static final String MESSAGE_TYPE_HEAR = "online";
    public static final String MESSAGE_DETAIL_CONFIGURATION_MAC_ADDRESS_NOT_FOUND = "未找到该MAC地址的配置信息";

    public static final int MESSAGE_CODE_SUCCESS = 200;

    public static final String MESSAGE_OPERATION_CONFIGURATION = "configuration";
    public static final String MESSAGE_OPERATION_REBOOT = "reboot";
    public static final String MESSAGE_OPERATION_RESET = "reset";
    public static final String MESSAGE_OPERATION_CHECK_PARKING_STATUS = "check_parking_status";
    public static final String MESSAGE_OPERATION_CHECK_WIFI_LIST = "check_wifi_list";
    public static final String MESSAGE_OPERATION_CHECK_DEVICE_STATUS = "check_device_status";
    public static final String MESSAGE_OPERATION_CHECK_DEVICE_CONFIG = "check_device_config";
    public static final String MESSAGE_OPERATION_UPDATE_RESERVATION_STATUS = "update_reservation_status";

    public static final int MESSAGE_CODE_ERROR_DEFAULT = 500;
    public static final int MESSAGE_CODE_NOT_FOUND = 404;
    public static final String JSON_KEY_TYPE = "t";
    public static final String JSON_KEY_CODE = "code";
    public static final String JSON_KEY_MESSAGE = "message";
    public static final String JSON_KEY_DATA = "d";
    public static final String JSON_KEY_OPERATION = "o";
    public static final String JSON_KEY_DEVICE_ID = "deviceId";
    public static final String JSON_KEY_MAC_ADDRESS = "deviceMacAddress";
    public static final String JSON_KEY_PARKING_SPACES = "parkingSpaces";
    public static final String JSON_KEY_RSSI_LIST = "rssiList";
    public static final String JSON_KEY_SPACE_ID = "spaceId";

    // 嗅探数据
    public static final String JSON_KEY_MAC = "ma";
    public static final String JSON_KEY_RSSI = "rs";
    public static final String JSON_KEY_SSID = "ss";
    public static final String JSON_KEY_TIMESTAMP = "ts";


    // 错误信息合集
    public static final Map<Integer, String> MESSAGE_CODE_MAP = new HashMap<>() {{
        put(MESSAGE_CODE_ERROR_DEFAULT, "未知错误");
        put(MESSAGE_CODE_NOT_FOUND, "未找到");
    }};

}