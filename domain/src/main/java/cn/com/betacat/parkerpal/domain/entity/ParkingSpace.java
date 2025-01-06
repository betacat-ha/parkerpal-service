package cn.com.betacat.parkerpal.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(of = "id")
@TableName("iot_parking_space")
public class ParkingSpace {
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private String id;
    @TableField(value = "status")
    private String status;
    @TableField(value = "name")
    private String name;
    @TableField(value = "type")
    private String type;
    @TableField(value = "parent_id")
    private String parentId;
    @TableField(value = "is_reserved")
    private boolean isReserved;
    @TableField(value = "is_occupied")
    private boolean isOccupied;
    @TableField(value = "is_disabled")
    private boolean isDisabled;
    @TableField(value = "is_deleted")
    private boolean isDeleted;
    @TableField(value = "create_time")
    private LocalDateTime createTime;
    @TableField(value = "update_time")
    private LocalDateTime updateTime;
}
