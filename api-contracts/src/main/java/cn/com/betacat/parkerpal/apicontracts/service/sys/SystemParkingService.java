package cn.com.betacat.parkerpal.apicontracts.service.sys;

import cn.com.betacat.parkerpal.domain.entity.SystemParking;
import com.baomidou.mybatisplus.extension.service.IService;

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
