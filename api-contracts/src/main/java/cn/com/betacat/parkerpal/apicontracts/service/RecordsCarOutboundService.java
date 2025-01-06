package cn.com.betacat.parkerpal.apicontracts.service;

import cn.com.betacat.parkerpal.domain.entity.RecordsCarOutbound;
import cn.com.betacat.parkerpal.domain.query.RecordsCarOutboundQuery;
import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 记录-车辆出场记录表(RecordsCarOutbound)表服务接口
 *
 * @author
 * @since 2024-08-14 13:50:45
 */
public interface RecordsCarOutboundService extends IService<RecordsCarOutbound> {
    
    /**
     * 记录-车辆出场记录表-分页查询列表
     *
     * @param query
     * @return
     */
     PageInfoRespQuery getPageList(RecordsCarOutboundQuery query);
}
