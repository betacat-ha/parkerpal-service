package cn.com.betacat.parkerpal.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 物联网-设备表
 */
@Data
@TableName(value = "iot_device")
public class IotDevice {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 设备ID
     */
    @TableField(value = "device_id")
    private String deviceId;

    /**
     * 设备MAC地址
     */
    @TableField(value = "mac_address")
    private String macAddress;

    /**
     * 设备名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 设备所在位置
     */
    @TableField(value = "`location`")
    private String location;

    /**
     * 设备作用
     */
    @TableField(value = "`role`")
    private Integer role;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(value = "update_time")
    private Date updateTime;

    /**
     * 组号
     */
    @TableField(value = "group_id")
    private Integer groupId;

    /**
     * 创建用户ID
     */
    @TableField(value = "create_user_id")
    private Integer createUserId;

    /**
     * 是否禁用，0|否；1|是
     */
    @TableField(value = "is_disabled")
    private Byte isDisabled;

    /**
     * 是否删除，0|否；1|是
     */
    @TableField(value = "is_deleted")
    private Byte isDeleted;
    @TableField(exist = false)
    private ParkingSpace[] parkingSpace;
}