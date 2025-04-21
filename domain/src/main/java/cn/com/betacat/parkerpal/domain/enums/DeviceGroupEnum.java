package cn.com.betacat.parkerpal.domain.enums;

import lombok.Getter;

/**
 * @Author: 
 * @Since: 2024-08-16 09:32:59
 * @Description: 设备组号枚举
 */
@Getter
public enum DeviceGroupEnum {

    B1("1", "负一层"),
    B2("2", "负二层"),
    F1("3", "地面"),
    ;

    private final String deviceGroupId;
    private final String deviceGroupName;

    DeviceGroupEnum(String deviceGroupId, String deviceGroupName) {
        this.deviceGroupId = deviceGroupId;
        this.deviceGroupName = deviceGroupName;
    }

    public static DeviceGroupEnum getById(String deviceGroupId) {
        for (DeviceGroupEnum deviceGroupEnum : DeviceGroupEnum.values()) {
            if (deviceGroupEnum.getDeviceGroupId().equals(deviceGroupId)) {
                return deviceGroupEnum;
            }
        }
        return null;
    }
}
