package cn.com.betacat.parkerpal.apicontracts.service;


import cn.com.betacat.parkerpal.domain.entity.SystemParkingSpace;
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
}
