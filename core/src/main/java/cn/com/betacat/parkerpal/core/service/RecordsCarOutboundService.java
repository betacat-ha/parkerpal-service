package cn.com.betacat.parkerpal.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.betacat.parkerpal.common.domain.RecordsCarOutbound;
import cn.com.betacat.parkerpal.common.domain.query.RecordsCarOutboundQuery;
import cn.com.betacat.parkerpal.common.pageutil.PageInfoRespQuery;

/**
 * 记录-车辆出场记录表(RecordsCarOutbound)表服务接口
 *
 * @author zoey
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
