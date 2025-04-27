package cn.com.betacat.parkerpal.core.service.mixin;

import cn.com.betacat.parkerpal.apicontracts.service.OrderPaidCatOutboundService;
import cn.com.betacat.parkerpal.apicontracts.service.ParkCollectCouponsService;
import cn.com.betacat.parkerpal.apicontracts.service.mixin.ChargeRulesService;
import cn.com.betacat.parkerpal.apicontracts.service.mixin.OrderService;
import cn.com.betacat.parkerpal.apicontracts.service.mixin.TemporaryService;
import cn.com.betacat.parkerpal.domain.entity.OrderPaidCatOutbound;
import cn.com.betacat.parkerpal.domain.entity.ParkCollectCoupons;
import cn.com.betacat.parkerpal.domain.entity.RecordsCarOutbound;
import cn.com.betacat.parkerpal.domain.enums.CarGroupEnum;
import cn.com.betacat.parkerpal.domain.enums.OrderStatus;
import cn.hutool.core.util.IdUtil;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ChargeRulesService chargeRulesService;

    @Autowired
    private TemporaryService temporaryService;

    @Autowired
    private ParkCollectCouponsService parkCollectCouponsService;

    @Autowired
    private OrderPaidCatOutboundService orderPaidCatOutboundService;

    /**
     * 非领劵用户提前支付生成需要支付的订单记录
     */
    @Override
    public OrderPaidCatOutbound createOrder(String license, LocalDateTime startTime) {
        // 假设对于非领劵用户的userId获取有特定逻辑
        String userId = chargeRulesService.getUserId(); // 这里应该有一个更具体的实现来获得userId
        return createOrderHelper(userId, license, startTime, LocalDateTime.now());
    }

    /**
     * 用户在商户领劵后生成需要支付的订单记录
     */
    @Override
    public OrderPaidCatOutbound createOrder(ParkCollectCoupons parkCollectCoupons) {
        // 使用已有信息创建订单
        OrderPaidCatOutbound order = createOrderHelper(parkCollectCoupons.getUserId(),
            parkCollectCoupons.getMainlandLicensePlates(),
            parkCollectCoupons.getStartTime(),
            parkCollectCoupons.getEndTime());

        // 设置领劵特有的字段
        order.setTotalDiscountAmount(parkCollectCoupons.getTotalDiscountAmount());
        order.setDiscount(parkCollectCoupons.getDiscount());
        order.setTotalIncomeAmount(parkCollectCoupons.getTotalIncomeAmount());
        order.setAdvancePayment(0); // 领劵用户默认为0

        // 更新订单
        orderPaidCatOutboundService.save(order);
        return order;
    }

    /**
     * 在出场的时候生成需要支付的订单记录
     */
    @Override
    public OrderPaidCatOutbound createOrder(RecordsCarOutbound recordsCarOutbound) {
        // 假设可以从此处获得userId
        String userId = recordsCarOutbound.getId(); // 如果没有这个字段，需要从其他地方获取
        return createOrderHelper(userId,
            recordsCarOutbound.getMainlandLicensePlates(),
            recordsCarOutbound.getStartTime(),
            recordsCarOutbound.getEndTime());
    }

    /**
     * 领劵用户，超时出场，提前支付时，重新计算费用
     */
    @Override
    public void countFee(OrderPaidCatOutbound order, LocalDateTime startTime) {
        LocalDateTime endTime = LocalDateTime.now();
        order.setStartTime(startTime);
        order.setEndTime(endTime);
        order.setTotalDuration(temporaryService.getParkingDuration(startTime, endTime));
        order.setTotalAmount(temporaryService.temporaryFee(false, startTime, endTime));
        order.setTotalIncomeAmount(temporaryService.temporaryFee(true, startTime, endTime));
        order.setTotalDiscountAmount(new BigDecimal(order.getTotalAmount())
            .subtract(new BigDecimal(order.getTotalIncomeAmount())).toString());
        order.setAdvancePayment(1);
        order.setCreateTime(endTime);

        // 更新订单
        orderPaidCatOutboundService.save(order);
    }

    /**
     * 创建订单的辅助方法
     */
    private OrderPaidCatOutbound createOrderHelper(String userId, String license, LocalDateTime startTime, LocalDateTime endTime) {
        // 获取系统当前时间
        LocalDateTime now = LocalDateTime.now();
        // 创建订单对象
        OrderPaidCatOutbound order = new OrderPaidCatOutbound();
        order.setUserId(userId);
        order.setMainlandLicensePlates(license);
        order.setStartTime(startTime);
        order.setEndTime(endTime);
        order.setPayTime(now);
        order.setOrderNumber(getOrderNo());

        // 计算停车时长
        order.setTotalDuration(temporaryService.getParkingDuration(startTime, endTime));

        // 实际收入和总收入
        String realityAmount = temporaryService.temporaryFee(true, startTime, endTime);
        String totalAmount = temporaryService.temporaryFee(false, startTime, endTime);

        order.setTotalAmount(totalAmount);
        order.setTotalIncomeAmount(realityAmount);

        // 优惠金额
        order.setTotalDiscountAmount(new BigDecimal(totalAmount)
            .subtract(new BigDecimal(realityAmount)).toString());

        order.setDiscount("免费停车" + chargeRulesService.getFreeDuration() + "分钟");
        order.setTypeCode(CarGroupEnum.LIN_BAN.getCarGroupId().toString());
        order.setTypeName(CarGroupEnum.LIN_BAN.getCarGroupName());
        order.setPayStatus(OrderStatus.NOTPAY.getType());
        order.setAdvancePayment(0); // 默认为0，非提前支付
        order.setCreateTime(now);

        // 保存订单
        orderPaidCatOutboundService.save(order);
        return order;
    }

    /**
     * 获取订单编号
     */
    private String getOrderNo() {
        return "ORDER_" + getNo();
    }

    /**
     * 获取退款单编号
     */
    private String getRefundNo() {
        return "REFUND_" + getNo();
    }

    /**
     * 获取编号
     */
    private String getNo() {
        return IdUtil.getSnowflake().nextIdStr();
    }
}