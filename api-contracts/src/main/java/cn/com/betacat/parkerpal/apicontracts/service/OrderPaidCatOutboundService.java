package cn.com.betacat.parkerpal.apicontracts.service;

import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import cn.com.betacat.parkerpal.domain.entity.MonthlyStatistics;
import cn.com.betacat.parkerpal.domain.entity.OrderPaidCatOutbound;
import cn.com.betacat.parkerpal.domain.entity.RevenueStatistics;
import cn.com.betacat.parkerpal.domain.query.OrderPaidCatOutboundQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * 订单-车辆出库已支付订单记录(OrderPaidCatOutbound)表服务接口
 *
 * @author 
 * @since 2024-08-20 13:50:20
 */
public interface OrderPaidCatOutboundService extends IService<OrderPaidCatOutbound> {

    /**
     * 订单-车辆出库已支付订单记录-分页查询列表
     *
     * @param query
     * @return
     */
    PageInfoRespQuery getPageList(OrderPaidCatOutboundQuery query);

    /**
     * 根据车牌号查询当天支付时间为最新的提前支付订单
     *
     * @param playStatus 支付状态
     * @param plates     车牌号
     * @return
     */
    OrderPaidCatOutbound getEntityByPlates(String playStatus, String plates);

    /**
     * 订单统计
     *
     * @param year
     * @param userId
     * @return
     */
    List<MonthlyStatistics> getMonthlyStatistics(String year, String userId);

    /**
     * 临保收益统计
     *
     * @param userId
     * @return
     */
    RevenueStatistics getStatistics(String userId);
}
