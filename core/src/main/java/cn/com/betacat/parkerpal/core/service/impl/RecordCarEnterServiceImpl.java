package cn.com.betacat.parkerpal.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.betacat.parkerpal.common.domain.RecordCarEnter;
import cn.com.betacat.parkerpal.common.domain.query.RecordCarEnterQuery;
import cn.com.betacat.parkerpal.common.mapper.RecordCarEnterMapper;
import cn.com.betacat.parkerpal.common.pageutil.PageInfoRespQuery;
import cn.com.betacat.parkerpal.common.pageutil.PageInfoUtil;
import cn.com.betacat.parkerpal.core.service.RecordCarEnterService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 记录-车辆进场记录表(RecordCarEnter)表服务实现类
 *
 * @author zoey
 * @since 2024-08-14 13:50:45
 */
@Service
public class RecordCarEnterServiceImpl extends ServiceImpl<RecordCarEnterMapper, RecordCarEnter> implements RecordCarEnterService {

    /**
     * 记录-车辆进场记录表-分页查询列表
     *
     * @param query
     * @return
     */
    @Override
    public PageInfoRespQuery getPageList(RecordCarEnterQuery query) {
        // 赋值页码
        PageInfoUtil.pageReq(query);
        // 统计总数
        Long total = this.baseMapper.countTotal(query);
        // 查询列表
        List<RecordCarEnter> list = this.baseMapper.getPageList(query);
        // 返回分页数据
        return PageInfoUtil.pageResp(list, query, total);
    }

    /**
     * 查询未结束订单中进场时间为最早的记录
     *
     * @return
     */
    @Override
    public RecordCarEnter getByPlates(Integer groupId, String plates) {
        return this.baseMapper.getByPlates(groupId, plates);
    }

    /**
     * 查询未结束订单中进场时间为最早的记录
     *
     * @return
     */
    @Override
    public RecordCarEnter getByDeviceIp(String license, String deviceIp) {
        return this.baseMapper.getByDeviceIp(license, deviceIp);
    }
}
