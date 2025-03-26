package cn.com.betacat.parkerpal.domain.query;

import cn.com.betacat.parkerpal.domain.base.PageInfoQuery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 车位-预约记录(SystemParkingSpaceReservationRecord)查询类
 *
 * @author
 * @since 2024-08-20 13:50:20
 */
@Getter
@Setter
@NoArgsConstructor
public class SystemParkingSpaceReservationRecordQuery extends PageInfoQuery implements Serializable {
    private static final long serialVersionUID = -17165114019670305L;
    // @formatter:off
    private Long id;
    private String parkingSpaceId;
    private LocalDateTime reservationStart;
    private LocalDateTime reservationEnd;
    private String reservationStatus;
    private String reservationUserId;
    private String licensePlate;
    private String parkingSpaceName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    // @formatter:on
}
