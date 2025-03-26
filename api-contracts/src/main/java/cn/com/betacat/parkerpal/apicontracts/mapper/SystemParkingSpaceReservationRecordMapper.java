package cn.com.betacat.parkerpal.apicontracts.mapper;
import cn.com.betacat.parkerpal.domain.query.SystemParkingSpaceReservationRecordQuery;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import cn.com.betacat.parkerpal.domain.entity.SystemParkingSpaceReservationRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface SystemParkingSpaceReservationRecordMapper extends BaseMapper<SystemParkingSpaceReservationRecord> {
    List<SystemParkingSpaceReservationRecord> selectAllByReservationStatus(@Param("reservationStatus")String reservationStatus);

    List<SystemParkingSpaceReservationRecord> selectByPage(@Param("query") SystemParkingSpaceReservationRecordQuery query);

    /**
     * 统计预约总数
     * @param query
     * @return
     */
    Long countTotal(@Param("query") SystemParkingSpaceReservationRecordQuery query);
}