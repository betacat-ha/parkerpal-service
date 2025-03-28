package cn.com.betacat.parkerpal.core.service;

import cn.com.betacat.parkerpal.apicontracts.mapper.SystemParkingSpaceMapper;
import cn.com.betacat.parkerpal.apicontracts.mapper.SystemParkingSpaceReservationRecordMapper;
import cn.com.betacat.parkerpal.apicontracts.mapper.SystemUsersMapper;
import cn.com.betacat.parkerpal.apicontracts.service.SystemParkingSpaceService;
import cn.com.betacat.parkerpal.common.constants.SystemConstant;
import cn.com.betacat.parkerpal.common.utils.JwtUtil;
import cn.com.betacat.parkerpal.core.exception.BizException;
import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import cn.com.betacat.parkerpal.domain.base.PageInfoUtil;
import cn.com.betacat.parkerpal.domain.entity.SystemParkingSpace;
import cn.com.betacat.parkerpal.domain.entity.SystemParkingSpaceReservationRecord;
import cn.com.betacat.parkerpal.domain.entity.SystemUsers;
import cn.com.betacat.parkerpal.domain.enums.RespEnum;
import cn.com.betacat.parkerpal.domain.enums.RoleEnum;
import cn.com.betacat.parkerpal.domain.query.SystemParkingSpaceReservationRecordQuery;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class SystemParkingSpaceServiceImpl extends ServiceImpl<SystemParkingSpaceMapper, SystemParkingSpace> implements SystemParkingSpaceService {
    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private SystemParkingSpaceReservationRecordMapper reservationRecordMapper;

    @Autowired
    private SystemUsersMapper systemUsersMapper;

    /**
     * 根据查询条件查询所有相关的停车位
     * @param query 查询条件
     * @return 停车位列表
     */
    // @Cacheable(value = "SystemParkingSpaceQuery", key = "#query.toString()", unless = "#result == null or #result.isEmpty()")
    public List<SystemParkingSpace> selectAll(SystemParkingSpace query) {
        return this.baseMapper.selectAll(query);
    }

    /**
     * 根据车位ID查询所有相关的停车位
     * @param id 车位ID
     * @return 停车位
     */
    @Cacheable(value = "SystemParkingSpaceById", key = "#id", unless = "#result == null")
    public SystemParkingSpace selectBy(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 根据车位ID更新车位信息
     * @param parkingSpace 车位信息
     * @return 更新数量
     */
    @Transactional
    public int updateBy(SystemParkingSpace parkingSpace) {
        int count = this.baseMapper.updateById(parkingSpace);
        cacheManager.getCache("SystemParkingSpaceById").put(parkingSpace.getId(), parkingSpace);
        return count;
    }

    /**
     * 预约车位
     * @param reservation 车位预约信息
     * @param user 用户信息
     * @return 预约结果
     */
    @Override
    @Transactional
    public SystemParkingSpace newReservation(SystemParkingSpaceReservationRecord reservation, SystemUsers user) {
        SystemParkingSpace parkingSpace = checkAvailable(reservation.getParkingSpaceId());

        parkingSpace.setIsReserved(true);
        updateBy(parkingSpace);
        LocalDateTime date = LocalDateTime.now();

        if (user == null) {
            throw new BizException(RespEnum.FAILURE.getCode(), "用户不存在");
        }

        // 如果是车主，只能用自己的ID预约车位，时间不能自定义
        if (user.getRoleId().equals(RoleEnum.CZ.getRoleId())) {
            reservation.setReservationUserId(user.getId());
            reservation.setReservationStart(date);
            // 预留15分钟
            reservation.setReservationEnd(date.plusMinutes(SystemConstant.PARKING_SPACE_RESERVATION_TIME));
            reservation.setReservationStatus(SystemConstant.PARKING_SPACE_RESERVATION_STATUS_RESERVED);
        } else {
            // 如果是管理员，可以用任何ID预约车位，时间可以自定义
            if (reservation.getReservationStart() == null) {
                reservation.setReservationStart(date);
            }

            if (reservation.getReservationEnd() == null) {
                // 默认预留15分钟
                reservation.setReservationEnd(reservation.getReservationStart().plusMinutes(SystemConstant.PARKING_SPACE_RESERVATION_TIME));
            }

            if (reservation.getReservationEnd().isBefore(reservation.getReservationStart())) {
                throw new BizException(RespEnum.FAILURE.getCode(), "预约结束时间不能早于开始时间");
            }

            if (reservation.getReservationStatus() == null || reservation.getReservationStatus().isEmpty()) {
                reservation.setReservationStatus(SystemConstant.PARKING_SPACE_RESERVATION_STATUS_RESERVED);
            }

            if (reservation.getReservationUserId() == null || reservation.getReservationUserId().isEmpty()) {
                reservation.setReservationUserId(user.getId());
            }
        }

        reservation.setCreatedAt(date);


        reservationRecordMapper.insert(reservation);

        // 返回预约结果
        SystemParkingSpace result = new SystemParkingSpace();
        result.setId(parkingSpace.getId());
        result.setName(parkingSpace.getName());
        return result;
    }

    /**
     * 编辑车位预约信息
     * @param reservation 预约信息
     * @param user 用户信息
     * @return 预约结果
     */
    @Override
    @Transactional
    public SystemParkingSpace editReservation(SystemParkingSpaceReservationRecord reservation, SystemUsers user) {
        SystemParkingSpaceReservationRecord current = reservationRecordMapper.selectById(reservation.getId());
        if (current == null) {
            throw new BizException(RespEnum.FAILURE.getCode(), "预约记录不存在");
        }

        if (user == null) {
            throw new BizException(RespEnum.FAILURE.getCode(), "用户不存在");
        }

        // 如果不是管理员，只能修改自己的预约记录
        if (!current.getReservationUserId().equals(user.getId()) && user.getRoleId().equals(RoleEnum.CZ.getRoleId())) {
            throw new BizException(RespEnum.FAILURE.getCode(), "无权修改他人预约记录");
        }

        SystemParkingSpace result = new SystemParkingSpace();

        if (!current.getParkingSpaceId().equals(reservation.getParkingSpaceId())) {
            // 如果车位ID不一致，需要检查新车位是否可用
            SystemParkingSpace newParkingSpace = checkAvailable(reservation.getParkingSpaceId());
            newParkingSpace.setIsReserved(true);
            updateBy(newParkingSpace);

            SystemParkingSpace oldParkingSpace = this.baseMapper.selectById(current.getParkingSpaceId());
            oldParkingSpace.setIsReserved(false);
            updateBy(oldParkingSpace);

            // 预约结果
            result.setId(newParkingSpace.getId());
            result.setName(newParkingSpace.getName());

            current.setParkingSpaceId(reservation.getParkingSpaceId());
        }

        LocalDateTime date = LocalDateTime.now();

        // 如果是车主，只能用自己的ID预约车位，时间不能自定义
        if (user.getRoleId().equals(RoleEnum.CZ.getRoleId())) {
            reservation.setReservationUserId(user.getId());
            reservation.setReservationStart(date);
            // 预留15分钟
            reservation.setReservationEnd(date.plusMinutes(SystemConstant.PARKING_SPACE_RESERVATION_TIME));
            reservation.setReservationStatus(SystemConstant.PARKING_SPACE_RESERVATION_STATUS_RESERVED);
        } else {
            // 如果是管理员，可以用任何ID预约车位，时间可以自定义
            if (reservation.getReservationStart() == null || reservation.getReservationEnd() == null) {
                throw new BizException(RespEnum.FAILURE.getCode(), "预约时间不能为空");
            }

            if (reservation.getReservationEnd().isBefore(reservation.getReservationStart())) {
                throw new BizException(RespEnum.FAILURE.getCode(), "预约结束时间不能早于开始时间");
            }

            if (reservation.getReservationStatus() == null || reservation.getReservationStatus().isEmpty()) {
                if (reservation.getReservationEnd().isBefore(date)) {
                    reservation.setReservationStatus(SystemConstant.PARKING_SPACE_RESERVATION_STATUS_EXPIRED);
                } else {
                    reservation.setReservationStatus(SystemConstant.PARKING_SPACE_RESERVATION_STATUS_RESERVED);
                }
            }
        }

        // 更新日期
        current.setUpdatedAt(date);

        reservationRecordMapper.updateById(current);

        return result;
    }

    /**
     * 获取车位预约信息
     * @param reservationId 预约ID
     */
    public SystemParkingSpaceReservationRecord getReservation(String reservationId) {
        return reservationRecordMapper.selectById(reservationId);
    }

    /**
     * 查询车位预约信息
     * @param query 查询条件
     * @return 预约信息列表
     */
    // public List<SystemParkingSpaceReservationRecord> getAllReservation(SystemParkingSpaceReservationRecord query) {
    //     return reservationRecordMapper.selectAll(query);
    // }

    /**
     * 分页查询车位预约信息
     * @param query 查询条件
     * @return 预约信息列表
     */
    public PageInfoRespQuery getPageReservation(SystemParkingSpaceReservationRecordQuery query) {
        // 通过车牌号查询用户信息
        if (query.getLicensePlate() != null) {
            SystemUsers user = systemUsersMapper.getEntityByAccount(query.getLicensePlate());
            if (user == null) {
                query.setLicensePlate(null);
            } else {
                query.setReservationUserId(user.getId());
            }
        }

        PageInfoUtil.pageReq(query);
        // 统计总数
        Long total = reservationRecordMapper.countTotal(query);
        List<SystemParkingSpaceReservationRecord> list = reservationRecordMapper.selectWithSpacesByPage(query);
        return PageInfoUtil.pageResp(list, query, total);
    }

    /**
     * 检查车位是否可用
     * @param spaceId 车位ID
     * @return 车位信息
     */
    private SystemParkingSpace checkAvailable(String spaceId) {
        SystemParkingSpace parkingSpace = this.baseMapper.selectById(spaceId);
        if (parkingSpace == null) {
            throw new BizException(RespEnum.FAILURE.getCode(), "车位不存在");
        }

        if (parkingSpace.isOccupied()) {
            throw new BizException(RespEnum.FAILURE.getCode(), "车位"+ parkingSpace.getName() + "已被占用");
        }

        if (parkingSpace.getIsReserved()) {
            throw new BizException(RespEnum.FAILURE.getCode(), "车位"+ parkingSpace.getName() + "已被预约");
        }

        return parkingSpace;
    }

    /** 定时任务，每分钟检查一次，如果预约时间到了，就释放车位
     *
     */
    @Transactional
    // @Scheduled(cron = "0 0/1 * * * ?")
    public void checkReservation() {
        List<SystemParkingSpaceReservationRecord> records = reservationRecordMapper.selectAllByReservationStatus(SystemConstant.PARKING_SPACE_RESERVATION_STATUS_RESERVED);

        if (records == null || records.isEmpty()) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        // 遍历所有预约记录
        for (SystemParkingSpaceReservationRecord record : records) {
            // 如果预约结束时间在当前时间之后，就跳过
            if (record.getReservationEnd().isAfter(now)) {
                continue;
            }

            // 如果预约结束时间在当前时间之前，就释放车位
            record.setReservationStatus(SystemConstant.PARKING_SPACE_RESERVATION_STATUS_EXPIRED);
            reservationRecordMapper.updateById(record);

            SystemParkingSpace parkingSpace = this.baseMapper.selectById(record.getParkingSpaceId());
            parkingSpace.setIsReserved(false);
            updateBy(parkingSpace);
        }
    }
}
