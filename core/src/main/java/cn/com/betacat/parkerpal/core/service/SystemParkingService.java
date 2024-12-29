package cn.com.betacat.parkerpal.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.betacat.parkerpal.common.domain.SystemParking;

/**
 * 系统管理-车位数量表(SystemParking)表服务接口
 *
 * @author
 * @since 2024-08-15 16:24:51
 */
public interface SystemParkingService extends IService<SystemParking> {

    /**
     * 系统管理-车位数量表-查询对象
     *
     * @return
     */
    SystemParking getEntity();

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    SystemParking updateEntity(SystemParking entity);
}
