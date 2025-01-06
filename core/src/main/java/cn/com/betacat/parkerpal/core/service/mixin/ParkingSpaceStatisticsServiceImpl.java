package cn.com.betacat.parkerpal.core.service.mixin;

import cn.com.betacat.parkerpal.apicontracts.mapper.ParkCollectCouponsMapper;
import cn.com.betacat.parkerpal.apicontracts.mapper.RecordCarEnterMapper;
import cn.com.betacat.parkerpal.apicontracts.mapper.SystemParkingDetailMapper;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemParkingDetailService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemParkingService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemUsersService;
import cn.com.betacat.parkerpal.apicontracts.service.mixin.ParkingSpaceStatisticsService;
import cn.com.betacat.parkerpal.domain.entity.ParkingSpaceStatistics;
import cn.com.betacat.parkerpal.domain.entity.SystemParking;
import cn.com.betacat.parkerpal.domain.entity.SystemParkingDetail;
import cn.com.betacat.parkerpal.domain.entity.SystemUsers;
import cn.com.betacat.parkerpal.domain.enums.RoleEnum;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ParkingSpaceStatisticsServiceImpl implements ParkingSpaceStatisticsService {

    @Autowired
    private SystemParkingService systemParkingService;
    @Autowired
    private RecordCarEnterMapper recordCarEnterMapper;
    @Autowired
    private SystemUsersService systemUsersService;
    @Autowired
    private ParkCollectCouponsMapper parkCollectCouponsMapper;
    @Autowired
    private SystemParkingDetailMapper systemParkingDetailMapper;
    @Autowired
    private SystemParkingDetailService systemParkingDetailService;

    @Override
    public ParkingSpaceStatistics parkingSpaceStatistics(String userId) {
        ParkingSpaceStatistics statistics = new ParkingSpaceStatistics();
        Long totalParkingSpaces = 0L;

        if (StringUtils.isNotBlank(userId)) {
            // 用户查询逻辑保持不变
            SystemUsers users = systemUsersService.getEntityByAccountOrId(userId);
            if (RoleEnum.GL.getRoleId().equals(users.getRoleId())
                || RoleEnum.CJ.getRoleId().equals(users.getRoleId())) {
                userId = "";
            }
        }

        if (StringUtils.isNotBlank(userId)) {
            // 商户首页逻辑保持不变
            SystemParkingDetail entityByUserId = systemParkingDetailMapper.getEntityByUserId(userId);
            if (Objects.nonNull(entityByUserId)) {
                totalParkingSpaces = entityByUserId.getAssignedNumber().longValue();
                Long countTotal = parkCollectCouponsMapper.countTotal(userId) == null ? 0L : parkCollectCouponsMapper.countTotal(userId);
                statistics.setIssuedParkingSpaces(countTotal);
                statistics.setUnlicensedParkingSpaces(totalParkingSpaces - countTotal);
            }
        } else {
            // 管理员首页逻辑保持不变
            SystemParking parking = systemParkingService.getEntity();
            if (Objects.nonNull(parking)) {
                totalParkingSpaces = parking.getParkingNumber().longValue();
                Long statusCounts = recordCarEnterMapper.getStatusCounts() == null ? 0L : recordCarEnterMapper.getStatusCounts();
                Long countTotal = parkCollectCouponsMapper.countTotal(userId) == null ? 0L : parkCollectCouponsMapper.countTotal(userId);
                Integer assignedNumber = systemParkingDetailService.sumAssignedNumber();
                statistics.setUsedParkingSpaces(statusCounts);
                statistics.setRemainingParkingSpaces(totalParkingSpaces - statusCounts);
                statistics.setIssuedParkingSpaces(countTotal);
                statistics.setRentalParkingSpace(assignedNumber == null ? 0 : assignedNumber.longValue());
                statistics.setOrdinaryParkingSpace(statusCounts - countTotal);
            }
        }

        statistics.setTotalParkingSpaces(totalParkingSpaces);

        return statistics;
    }
}