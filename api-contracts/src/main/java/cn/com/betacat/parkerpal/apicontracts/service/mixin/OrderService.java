package cn.com.betacat.parkerpal.apicontracts.service.mixin;

import cn.com.betacat.parkerpal.domain.entity.OrderPaidCatOutbound;
import cn.com.betacat.parkerpal.domain.entity.ParkCollectCoupons;
import cn.com.betacat.parkerpal.domain.entity.RecordsCarOutbound;
import java.time.LocalDateTime;

public interface OrderService {

    OrderPaidCatOutbound createOrder(String license, LocalDateTime startTime);

    OrderPaidCatOutbound createOrder(RecordsCarOutbound recordsCarOutbound);

    OrderPaidCatOutbound createOrder(ParkCollectCoupons parkCollectCoupons);

    void countFee(OrderPaidCatOutbound order, LocalDateTime startTime);

}
