package cn.com.betacat.parkerpal.apicontracts.service;


import cn.com.betacat.parkerpal.domain.entity.SystemParkingSpace;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SystemParkingSpaceService extends IService<SystemParkingSpace> {
    /**
     * 查询所有停车位
     * @param query
     * @return
     */
    List<SystemParkingSpace> selectAll(SystemParkingSpace query);
}
