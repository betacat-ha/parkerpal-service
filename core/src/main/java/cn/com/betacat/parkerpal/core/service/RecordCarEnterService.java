package cn.com.betacat.parkerpal.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.betacat.parkerpal.common.domain.RecordCarEnter;
import cn.com.betacat.parkerpal.common.domain.query.RecordCarEnterQuery;
import cn.com.betacat.parkerpal.common.pageutil.PageInfoRespQuery;

/**
 * 记录-车辆进场记录表(RecordCarEnter)表服务接口
 *
 * @author 
 * @since 2024-08-14 13:50:45
 */
public interface RecordCarEnterService extends IService<RecordCarEnter> {

    /**
     * 记录-车辆进场记录表-分页查询列表
     *
     * @param query
     * @return
     */
    PageInfoRespQuery getPageList(RecordCarEnterQuery query);

    /**
     * 查询未结束订单中进场时间为最早的记录
     *
     * @return
     */
    RecordCarEnter getByPlates(Integer groupId, String plates);

    /**
     * 查询未结束订单中进场时间为最早的记录
     *
     * @return
     */
    RecordCarEnter getByDeviceIp(String license, String deviceIp);
}
