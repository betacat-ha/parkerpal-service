package cn.com.betacat.parkerpal.domain.enums;

import lombok.Getter;

/**
 * 基站角色枚举
 * @author BetaCat
 * @since 2025-4-2 16:30:56
 */
public enum BaseStationTypeEnum {
    /**
     * 蓝牙基站
     */
    BLUETOOTH("1", "bluetooth","蓝牙基站"),
    /**
     * WiFi基站
     */
    WIFI("2", "wifi","WiFi基站"),
    ;

    @Getter
    private final String typeId;
    @Getter
    private final String typeCode;
    @Getter
    private final String name;


    BaseStationTypeEnum(String typeId, String typeCode, String name) {
        this.typeId = typeId;
        this.typeCode = typeCode;
        this.name = name;
    }

}
