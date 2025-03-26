package cn.com.betacat.parkerpal.apicontracts.service;


import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import cn.com.betacat.parkerpal.domain.entity.SystemParkingSpace;
import cn.com.betacat.parkerpal.domain.entity.SystemParkingSpaceReservationRecord;
import cn.com.betacat.parkerpal.domain.entity.SystemUsers;
import cn.com.betacat.parkerpal.domain.query.SystemParkingSpaceReservationRecordQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SystemParkingSpaceService extends IService<SystemParkingSpace> {
    /**
     * 查询所有停车位
     * @param query
     * @return
     */
    List<SystemParkingSpace> selectAll(SystemParkingSpace query);

    /**
     * 根据车位ID查询所有相关的停车位
     * @param id 车位ID
     * @return 停车位
     */
    SystemParkingSpace selectBy(String id);

    /**
     * 根据车位ID更新车位信息
     * @param parkingSpace 车位信息
     * @return 更新数量
     */
    int updateBy(SystemParkingSpace parkingSpace);

    /**
     * 预约车位
     * @param reservation 车位预约信息
     * @param user 用户信息
     * @return 预约结果
     */
    @Transactional
    SystemParkingSpace newReservation(SystemParkingSpaceReservationRecord reservation, SystemUsers user);

    /**
     * 编辑车位预约信息
     * @param reservation 预约信息
     * @param user 用户信息
     * @return 预约结果
     */
    @Transactional
    SystemParkingSpace editReservation(SystemParkingSpaceReservationRecord reservation, SystemUsers user);

    /**
     * 分页查询车位预约信息
     * @param query 查询条件
     * @return 预约信息列表
     */
    PageInfoRespQuery getPageReservation(SystemParkingSpaceReservationRecordQuery query);
}
