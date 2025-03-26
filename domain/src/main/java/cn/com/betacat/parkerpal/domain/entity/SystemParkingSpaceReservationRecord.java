package cn.com.betacat.parkerpal.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
@TableName(value = "system_parking_space_reservation_record")
public class SystemParkingSpaceReservationRecord {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "parking_space_id")
    private String parkingSpaceId;

    @TableField(value = "reservation_start")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationStart;

    @TableField(value = "reservation_end")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime reservationEnd;

    @TableField(value = "reservation_status")
    private String reservationStatus;

    @TableField(value = "reservation_user_id")
    private String reservationUserId;

    @TableField(value = "created_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;

    @TableField(value = "updated_at")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;

    @TableField(exist = false)
    private SystemParkingSpace parkingSpace;
}