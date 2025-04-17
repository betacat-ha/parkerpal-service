package cn.com.betacat.parkerpal.apicontracts.service.sys;

import cn.com.betacat.parkerpal.domain.entity.ParkingSpaceStatistics;

/**
 * 系统管理-车位统计服务接口
 *
 * @author
 */
public interface SystemParkingStatisticService {
    /**
     * 首页-车位统计
     *
     * @param userId 商户ID
     * @return
     */
    ParkingSpaceStatistics parkingSpaceStatistics(String userId);
}
