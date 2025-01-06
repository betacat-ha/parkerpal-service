package cn.com.betacat.parkerpal.apicontracts.service.sys;

import cn.com.betacat.parkerpal.domain.entity.SystemParkingDetail;
import cn.com.betacat.parkerpal.domain.query.SystemParkingDetailQuery;
import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 系统管理-车位数量明细表(SystemParkingDetail)表服务接口
 *
 * @author 
 * @since 2024-08-15 16:24:56
 */
public interface SystemParkingDetailService extends IService<SystemParkingDetail> {

    /**
     * 系统管理-车位数量明细表-分页查询列表
     *
     * @param query
     * @return
     */
    PageInfoRespQuery getPageList(SystemParkingDetailQuery query);

    /**
     * 统计已分配车位总数量
     *
     * @return
     */
    Integer sumAssignedNumber();

    /**
     * 新增或更新
     *
     * @param entity
     * @return
     */
    SystemParkingDetail createOrUpdate(SystemParkingDetail entity);
}
