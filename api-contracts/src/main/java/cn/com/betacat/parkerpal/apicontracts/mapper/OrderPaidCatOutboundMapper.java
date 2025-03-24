package cn.com.betacat.parkerpal.apicontracts.mapper;

import cn.com.betacat.parkerpal.domain.entity.MonthlyStatistics;
import cn.com.betacat.parkerpal.domain.entity.OrderPaidCatOutbound;
import cn.com.betacat.parkerpal.domain.query.OrderPaidCatOutboundQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 订单-车辆出库已支付订单记录(OrderPaidCatOutbound)表数据库访问层
 *
 * @author 
 * @since 2024-08-20 13:50:20
 */
@Repository
public interface OrderPaidCatOutboundMapper extends BaseMapper<OrderPaidCatOutbound> {

    /**
     * 统计 订单-车辆出库已支付订单记录 总数
     *
     * @param query
     * @return
     */
    Long countTotal(@Param("query") OrderPaidCatOutboundQuery query);

    /**
     * 分页查询 订单-车辆出库已支付订单记录 列表
     *
     * @param query
     * @return
     */
    List<OrderPaidCatOutbound> getPageList(@Param("query") OrderPaidCatOutboundQuery query);

    /**
     * 统计订单
     *
     * @param year   年份
     * @param userId 商户ID
     * @return
     */
    List<MonthlyStatistics> getMonthlyStatistics(@Param("year") String year, @Param("userId") String userId);

    /**
     * 统计今日临保缴费记录
     *
     * @return
     */
    Double getTodayPayments(@Param("playStatus") String playStatus, @Param("userId") String userId);

    /**
     * 统计总临保缴费记录
     *
     * @return
     */
    Double getTotalPayments(@Param("playStatus") String playStatus,@Param("userId") String userId);

    /**
     * 查询订单
     *
     * @param playStatus 订单状态
     * @param plates     车牌号
     * @param deviceIp   出口设备IP
     * @return
     */
    OrderPaidCatOutbound getEntity(@Param("playStatus") String playStatus,
                                   @Param("plates") String plates,
                                   @Param("deviceIp") String deviceIp);

    /**
     * 根据车牌号查询当天支付时间为最新的提前支付订单
     *
     * @param playStatus 支付状态
     * @param plates     车牌号
     * @return
     */
    OrderPaidCatOutbound getEntityByPlates(@Param("playStatus") String playStatus, @Param("plates") String plates);
}
