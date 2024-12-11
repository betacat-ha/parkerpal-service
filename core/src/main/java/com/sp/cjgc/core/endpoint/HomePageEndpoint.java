package com.sp.cjgc.core.endpoint;

import com.sp.cjgc.common.annotation.PassToken;
import com.sp.cjgc.common.domain.MonthlyStatistics;
import com.sp.cjgc.common.domain.RevenueStatistics;
import com.sp.cjgc.common.exception.RequestObject;
import com.sp.cjgc.common.exception.ResponseObject;
import com.sp.cjgc.core.service.MonthlyInsurancePaymentService;
import com.sp.cjgc.core.service.OrderPaidCatOutboundService;
import com.sp.cjgc.core.endpoint.converter.HomePageConverter;
import com.sp.cjgc.core.endpoint.request.HomePageReq;
import com.sp.cjgc.core.endpoint.response.HomePageResp;
import com.sp.cjgc.core.utils.StatisticUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Zoey
 * @Since: 2024-08-29 09:08:24
 * @Description:
 */
@RestController
@Api(tags = "首页")
@RequestMapping("homePageEndpoint")
public class HomePageEndpoint {

    @Autowired
    private StatisticUtils statistics;
    @Autowired
    private OrderPaidCatOutboundService orderPaidCatOutboundService;
    @Autowired
    private MonthlyInsurancePaymentService monthlyInsurancePaymentService;

    @PassToken(required = false)
    @ApiOperation(value = "月收费统计")
    @PostMapping(value = "/countOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<List<HomePageResp.CountOrderDTO>> pageList(@RequestBody RequestObject<HomePageReq.QueryDTO> ro) {
        // 调用服务
        List<MonthlyStatistics> list = orderPaidCatOutboundService.getMonthlyStatistics(
                ro.getBody().getYear(),
                ro.getBody().getUserId());
        // 响应数据转换
        return ResponseObject.success(HomePageConverter.INSTANCE.toListDto(list));
    }

    @PassToken(required = false)
    @ApiOperation(value = "收益统计")
    @PostMapping(value = "/getStatistics", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<HomePageResp.RevenueStatisticsDTO> getStatistics(@RequestBody RequestObject<HomePageReq.Query1DTO> ro) {
        // 统计临保收益
        RevenueStatistics statistics = orderPaidCatOutboundService.getStatistics(ro.getBody().getUserId());
        // 统计月保收益
        RevenueStatistics entity = monthlyInsurancePaymentService.getStatistics(ro.getBody().getUserId());
        statistics.setTodayPaymentAmount(entity.getTodayPaymentAmount());
        statistics.setTotalAccumulatePaymentAmount(entity.getTotalAccumulatePaymentAmount());
        // 响应数据转换
        return ResponseObject.success(HomePageConverter.INSTANCE.toDto(statistics));
    }

    @PassToken(required = false)
    @ApiOperation(value = "车位统计")
    @PostMapping(value = "/parkingSpaceStatistics", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<HomePageResp.ParkingSpaceStatisticsDTO> parkingSpaceStatistics(@RequestBody RequestObject<HomePageReq.Query1DTO> ro) {
        // 响应数据转换
        return ResponseObject.success(HomePageConverter.INSTANCE.toDto(statistics.parkingSpaceStatistics(ro.getBody().getUserId())));
    }
}
