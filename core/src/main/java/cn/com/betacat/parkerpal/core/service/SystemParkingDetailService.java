package cn.com.betacat.parkerpal.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.betacat.parkerpal.common.domain.SystemParkingDetail;
import cn.com.betacat.parkerpal.common.domain.query.SystemParkingDetailQuery;
import cn.com.betacat.parkerpal.common.pageutil.PageInfoRespQuery;

/**
 * 系统管理-车位数量明细表(SystemParkingDetail)表服务接口
 *
 * @author zoey
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
