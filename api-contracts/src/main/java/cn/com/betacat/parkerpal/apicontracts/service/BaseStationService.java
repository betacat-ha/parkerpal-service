package cn.com.betacat.parkerpal.apicontracts.service;

import cn.com.betacat.parkerpal.domain.entity.BaseStation;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * 基站服务接口
 * @author BetaCat
 * @since 2025-04-2 16:50:19
 */
public interface BaseStationService extends IService<BaseStation> {
    /**
     * 查询所有基站信息，包含设备信息和车位信息
     *
     * @return 基站信息
     */
    List<BaseStation> selectAllWithDevicesAndSpaces();

    /**
     * 查询所有基站信息
     *
     * @return 基站信息
     */
    List<BaseStation> selectAll();

    /**
     * 根据基站ID查询基站信息
     *
     * @param id 基站ID
     * @return 基站信息
     */
    BaseStation selectById(String id);

    /**
     * 根据基站ID查询基站信息，包含设备信息
     *
     * @param id 基站ID
     * @return 基站信息
     */
    BaseStation selectByIdWithDevices(String id);


    /**
     * 根据基站ID列表删除基站信息
     *
     * @param ids 基站ID列表
     */
    void deleteById(List<String> ids);
}
