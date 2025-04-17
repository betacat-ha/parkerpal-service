package cn.com.betacat.parkerpal.core.service;

import cn.com.betacat.parkerpal.apicontracts.mapper.ParkCollectCouponsMapper;
import cn.com.betacat.parkerpal.apicontracts.mapper.RecordCarEnterMapper;
import cn.com.betacat.parkerpal.apicontracts.mapper.SystemParkingDetailMapper;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemParkingDetailService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemParkingService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemParkingStatisticService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemUsersService;
import cn.com.betacat.parkerpal.domain.entity.ParkingSpaceStatistics;
import cn.com.betacat.parkerpal.domain.entity.SystemParking;
import cn.com.betacat.parkerpal.domain.entity.SystemParkingDetail;
import cn.com.betacat.parkerpal.domain.entity.SystemUsers;
import cn.com.betacat.parkerpal.domain.enums.RoleEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class SystemParkingStatisticServiceImpl implements SystemParkingStatisticService {
    @Autowired
    private SystemParkingDetailMapper systemParkingDetailMapper;

    @Autowired
    private ParkCollectCouponsMapper parkCollectCouponsMapper;

    @Autowired
    private RecordCarEnterMapper recordCarEnterMapper;

    @Autowired
    private SystemUsersService systemUsersService;

    @Autowired
    private SystemParkingDetailService systemParkingDetailService;

    @Autowired
    private SystemParkingService systemParkingService;

    /**
     * 首页-车位统计
     *
     * @param userId 商户ID
     * @return
     */
    public ParkingSpaceStatistics parkingSpaceStatistics(String userId) {
        // 创建对象
        ParkingSpaceStatistics statistics = new ParkingSpaceStatistics();
        // 定义总车位为0
        Long totalParkingSpaces = 0L;
        // 判断是否存在用户ID
        if (StringUtils.isNotBlank(userId)) {
            // 查询用户信息
            SystemUsers users = systemUsersService.getEntityByAccountOrId(userId);
            // 判断用户角色是否是非管理员
            if (RoleEnum.GL.getRoleId().equals(users.getRoleId())
                    || RoleEnum.CJ.getRoleId().equals(users.getRoleId())
            ) userId = "";
        }

        // 判断是否存在用户ID --- 商户的首页
        if (StringUtils.isNotBlank(userId)) {
            // 查询该用户的租赁车位总数
            SystemParkingDetail entityByUserId = systemParkingDetailMapper.getEntityByUserId(userId);
            // 获取该用户的租赁车位总数
            if (Objects.nonNull(entityByUserId)) totalParkingSpaces = entityByUserId.getAssignedNumber().longValue();
            // 赋值总车位
            statistics.setTotalParkingSpaces(totalParkingSpaces);
            // 未发劵车位
            statistics.setUnlicensedParkingSpaces(0L);
            // 已发劵车位
            statistics.setIssuedParkingSpaces(0L);
            if (totalParkingSpaces > 0L) {
                // 获取已发放车位数量
                Long countTotal = parkCollectCouponsMapper.countTotal(userId) == null ? 0L : parkCollectCouponsMapper.countTotal(userId);
                // 已发劵车位
                statistics.setIssuedParkingSpaces(countTotal);
                // 未发劵车位
                statistics.setUnlicensedParkingSpaces(totalParkingSpaces - countTotal);
            }
            // 已用车位
            statistics.setUsedParkingSpaces(0L);
            // 剩余车位
            statistics.setRemainingParkingSpaces(0L);
            // 普通车位
            statistics.setOrdinaryParkingSpace(0L);
            // 租赁车位
            statistics.setRentalParkingSpace(0L);
        } else {
            // 管理员的首页
            // 查询总车位
            SystemParking parking = systemParkingService.getEntity();
            // 判断是否存在数据 获取总车位
            if (Objects.nonNull(parking)) totalParkingSpaces = parking.getParkingNumber().longValue();
            // 赋值总车位
            statistics.setTotalParkingSpaces(totalParkingSpaces);
            // 未发劵车位
            statistics.setUnlicensedParkingSpaces(0L);
            // 已发劵车位
            statistics.setIssuedParkingSpaces(0L);
            // 判断总车位是否为0
            if (totalParkingSpaces == 0L) {
                // 已用车位
                statistics.setUsedParkingSpaces(0L);
                // 剩余车位
                statistics.setRemainingParkingSpaces(0L);
                // 普通车位
                statistics.setOrdinaryParkingSpace(0L);
                // 租赁车位
                statistics.setRentalParkingSpace(0L);
            } else {
                // 统计已用车位
                Long statusCounts = recordCarEnterMapper.getStatusCounts() == null ? 0L : recordCarEnterMapper.getStatusCounts();
                // 已用车位
                statistics.setUsedParkingSpaces(statusCounts);
                // 剩余车位
                statistics.setRemainingParkingSpaces(totalParkingSpaces - statusCounts);
                // 获取已发放车位数量
                Long countTotal = parkCollectCouponsMapper.countTotal(userId) == null ? 0L : parkCollectCouponsMapper.countTotal(userId);
                statistics.setIssuedParkingSpaces(countTotal);
                // 租赁车位
                Integer assignedNumber = systemParkingDetailService.sumAssignedNumber();
                statistics.setRentalParkingSpace(assignedNumber == null ? 0 : assignedNumber.longValue());
                // 普通车位
                statistics.setOrdinaryParkingSpace(statusCounts - countTotal);
            }
        }
        return statistics;
    }
}
