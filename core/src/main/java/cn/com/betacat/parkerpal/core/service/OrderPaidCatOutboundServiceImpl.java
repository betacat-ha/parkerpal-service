package cn.com.betacat.parkerpal.core.service;

import cn.com.betacat.parkerpal.apicontracts.mapper.OrderPaidCatOutboundMapper;
import cn.com.betacat.parkerpal.apicontracts.service.OrderPaidCatOutboundService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemUsersService;
import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import cn.com.betacat.parkerpal.domain.base.PageInfoUtil;
import cn.com.betacat.parkerpal.domain.entity.MonthlyStatistics;
import cn.com.betacat.parkerpal.domain.entity.OrderPaidCatOutbound;
import cn.com.betacat.parkerpal.domain.entity.RevenueStatistics;
import cn.com.betacat.parkerpal.domain.entity.SystemUsers;
import cn.com.betacat.parkerpal.domain.enums.OrderStatus;
import cn.com.betacat.parkerpal.domain.enums.RoleEnum;
import cn.com.betacat.parkerpal.domain.query.OrderPaidCatOutboundQuery;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单-车辆出库已支付订单记录(OrderPaidCatOutbound)表服务实现类
 *
 * @author
 * @since 2024-08-20 13:50:20
 */
@Slf4j
@Service
public class OrderPaidCatOutboundServiceImpl
        extends ServiceImpl<OrderPaidCatOutboundMapper, OrderPaidCatOutbound>
        implements OrderPaidCatOutboundService {

    // 该类只保留状态的获取，创建订单以及更新订单状态迁移到更上层，即OrderServiceImpl和ExitParkingServiceImpl

    @Autowired
    private SystemUsersService systemUsersService;

    /**
     * 订单-车辆出库已支付订单记录-分页查询列表
     *
     * @param query
     * @return
     */
    @Override
    public PageInfoRespQuery getPageList(OrderPaidCatOutboundQuery query) {
        // 判断是否存在用户ID
        if (StringUtils.isNotBlank(query.getUserId())) {
            // 查询用户信息
            SystemUsers users = systemUsersService.getEntityByAccountOrId(query.getUserId());
            // 判断用户角色是否是非管理员
            if (RoleEnum.GL.getRoleId().equals(users.getRoleId())
                    || RoleEnum.CJ.getRoleId().equals(users.getRoleId())
            ) {
                // 设置用户ID
                query.setUserId("");
            }
        }
        // 赋值页码
        PageInfoUtil.pageReq(query);
        // 统计总数
        Long total = this.baseMapper.countTotal(query);
        // 查询列表
        List<OrderPaidCatOutbound> list = this.baseMapper.getPageList(query);
        // 返回分页数据
        return PageInfoUtil.pageResp(list, query, total);
    }

    /**
     * 根据车牌号查询当天支付时间为最新的提前支付订单
     *
     * @param playStatus 支付状态
     * @param plates     车牌号
     * @return
     */
    @Override
    public OrderPaidCatOutbound getEntityByPlates(String playStatus, String plates) {
        return this.baseMapper.getEntityByPlates(playStatus, plates);
    }


    /**
     * 订单统计
     *
     * @param year
     * @param userId
     * @return
     */
    @Override
    public List<MonthlyStatistics> getMonthlyStatistics(String year, String userId) {
        // 判断是否有年份值，如果没有，则默认查当前年
        if (StringUtils.isBlank(year)) year = LocalDateTime.now().getYear() + "";
        // 判断是否存在用户ID
        if (StringUtils.isNotBlank(userId)) {
            // 查询用户信息
            SystemUsers users = systemUsersService.getEntityByAccountOrId(userId);
            // 判断用户角色是否是非管理员
            if (RoleEnum.GL.getRoleId().equals(users.getRoleId())
                    || RoleEnum.CJ.getRoleId().equals(users.getRoleId())
            ) userId = "";
        }
        return this.baseMapper.getMonthlyStatistics(year, userId);
    }

    /**
     * 临保收益统计
     *
     * @param userId
     * @return
     */
    @Override
    public RevenueStatistics getStatistics(String userId) {
        // 创建对象
        RevenueStatistics statistics = new RevenueStatistics();
        // 判断是否存在用户ID
        if (StringUtils.isNotBlank(userId)) {
            // 查询用户信息
            SystemUsers users = systemUsersService.getEntityByAccountOrId(userId);
            // 判断用户角色是否是非管理员
            if (RoleEnum.GL.getRoleId().equals(users.getRoleId())
                    || RoleEnum.CJ.getRoleId().equals(users.getRoleId())
            ) userId = "";
        }
        // 今日临时金额
        statistics.setTodayTemporaryAmount(
                this.baseMapper.getTodayPayments(OrderStatus.SUCCESS.getType(), userId) == null
                        ? 0.0
                        : this.baseMapper.getTodayPayments(OrderStatus.SUCCESS.getType(), userId)
        );
        // 统计总临时金额
        statistics.setTotalTemporaryAmount(
                this.baseMapper.getTotalPayments(OrderStatus.SUCCESS.getType(), userId) == null
                        ? 0.0
                        : this.baseMapper.getTotalPayments(OrderStatus.SUCCESS.getType(), userId)
        );
        return statistics;
    }


    /**
     * 根据商户id和车牌号查询未支付订单
     * 防止重复创建订单对象
     *
     * @param userId 商户ID
     * @param plates 车牌号或手机号
     * @return
     */
    private OrderPaidCatOutbound getNoPayOrderByProductId(String userId, String plates) {
        LambdaQueryWrapper<OrderPaidCatOutbound> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderPaidCatOutbound::getUserId, userId);
        queryWrapper.eq(OrderPaidCatOutbound::getPlayStatus, OrderStatus.NOTPAY.getType());
        queryWrapper.eq(OrderPaidCatOutbound::getMainlandLicensePlates, plates);
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * 根据订单号获取订单状态
     *
     * @param orderNo
     * @return
     */
    private String getOrderStatus(String orderNo) {
        LambdaQueryWrapper<OrderPaidCatOutbound> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderPaidCatOutbound::getOrderNumber, orderNo);
        OrderPaidCatOutbound orderInfo = baseMapper.selectOne(queryWrapper);
        if (Objects.isNull(orderInfo)) return null;
        return orderInfo.getPlayStatus();
    }

}
