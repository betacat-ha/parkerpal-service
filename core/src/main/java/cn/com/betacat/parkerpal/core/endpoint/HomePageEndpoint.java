package cn.com.betacat.parkerpal.core.endpoint;

import cn.com.betacat.parkerpal.common.annotation.PassToken;
import cn.com.betacat.parkerpal.common.domain.MonthlyStatistics;
import cn.com.betacat.parkerpal.common.domain.RevenueStatistics;
import cn.com.betacat.parkerpal.common.exception.RequestObject;
import cn.com.betacat.parkerpal.common.exception.ResponseObject;
import cn.com.betacat.parkerpal.core.endpoint.converter.HomePageConverter;
import cn.com.betacat.parkerpal.core.endpoint.request.HomePageReq;
import cn.com.betacat.parkerpal.core.endpoint.response.HomePageResp;
import cn.com.betacat.parkerpal.core.service.MonthlyInsurancePaymentService;
import cn.com.betacat.parkerpal.core.service.OrderPaidCatOutboundService;
import cn.com.betacat.parkerpal.core.utils.StatisticUtils;
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
