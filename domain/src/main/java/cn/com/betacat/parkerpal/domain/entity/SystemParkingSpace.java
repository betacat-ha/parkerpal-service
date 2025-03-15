package cn.com.betacat.parkerpal.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import lombok.Data;

/**
    * 系统管理-车位表
    */
@Data
@TableName(value = "system_parking_space")
public class SystemParkingSpace {
    /**
     * ID
     */
    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    /**
     * 所属的设备ID，0为未设置
     */
    @TableField(value = "device_id")
    private String deviceId;

    /**
     * 车位名称
     */
    @TableField(value = "`name`")
    private String name;

    /**
     * 车位类型
     */
    @TableField(value = "`type`")
    private String type;

    /**
     * 车位父ID
     */
    @TableField(value = "parent_id")
    private Integer parentId;

    /**
     * 是否预留车位，0|否；1|是
     */
    @TableField(value = "is_reserved")
    private Integer isReserved;

    /**
     * 车位状态，0|空闲；1|已分配；2|已占用
     */
    @TableField(value = "`status`")
    private Integer status;

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
     * 传感器槽位
     */
    @TableField(value = "sensor_slot")
    private String sensorSlot;

    /**
     * 是否被占用
     */
    @TableField(exist = false)
    private boolean isOccupied;

    /**
     * 激光传感器距离
     */
    @TableField(exist = false)
    private Double distance;

    /**
     * 备注
     */
    @TableField(value = "comment")
    private String comment;

    /**
     * 3D室内地图楼层编码
     */
    @TableField(value = "fnum")
    private String fnum;

    /**
     * 3D室内地图模型名称
     */
    @TableField(value = "model_name")
    private String modelName;

}