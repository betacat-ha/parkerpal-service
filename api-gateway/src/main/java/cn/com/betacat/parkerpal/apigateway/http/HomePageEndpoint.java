package cn.com.betacat.parkerpal.apigateway.http;

import cn.com.betacat.parkerpal.apicontracts.converter.HomePageConverter;
import cn.com.betacat.parkerpal.apicontracts.dto.req.HomePageReq;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.HomePageResp;
import cn.com.betacat.parkerpal.apicontracts.service.MonthlyInsurancePaymentService;
import cn.com.betacat.parkerpal.apicontracts.service.OrderPaidCatOutboundService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemParkingStatisticService;
import cn.com.betacat.parkerpal.common.annotation.PassToken;
import cn.com.betacat.parkerpal.domain.base.ResResult;
import cn.com.betacat.parkerpal.domain.entity.MonthlyStatistics;
import cn.com.betacat.parkerpal.domain.entity.RevenueStatistics;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 
 * @Since: 2024-08-29 09:08:24
 * @Description:
 */

@Api(tags = "首页")
@RestController
@RequestMapping("homePageEndpoint")
public class HomePageEndpoint {
    @Autowired
    private OrderPaidCatOutboundService orderPaidCatOutboundService;
    @Autowired
    private MonthlyInsurancePaymentService monthlyInsurancePaymentService;
    @Autowired
    private SystemParkingStatisticService systemParkingStatisticService;

    @PassToken(required = false)
    @ApiOperation(value = "月收费统计")
    @PostMapping(value = "/countOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<List<HomePageResp.CountOrderDTO>> pageList(@RequestBody HomePageReq.QueryDTO dto) {
        // 调用服务
        List<MonthlyStatistics> list = orderPaidCatOutboundService.getMonthlyStatistics(
                dto.getYear(),
                dto.getUserId());
        // 响应数据转换
        return ResResult.success(HomePageConverter.INSTANCE.toListDto(list));
    }

    @PassToken(required = false)
    @ApiOperation(value = "收益统计")
    @PostMapping(value = "/getStatistics", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<HomePageResp.RevenueStatisticsDTO> getStatistics(@RequestBody HomePageReq.Query1DTO dto, HttpServletRequest request ) {
        // 统计临保收益
        RevenueStatistics statistics = orderPaidCatOutboundService.getStatistics(dto.getUserId());
        // 统计月保收益
        RevenueStatistics entity = monthlyInsurancePaymentService.getStatistics(dto.getUserId());
        statistics.setTodayPaymentAmount(entity.getTodayPaymentAmount());
        statistics.setTotalAccumulatePaymentAmount(entity.getTotalAccumulatePaymentAmount());
        // 响应数据转换
        return ResResult.success(HomePageConverter.INSTANCE.toDto(statistics));
    }

    @PassToken(required = false)
    @ApiOperation(value = "车位统计")
    @PostMapping(value = "/parkingSpaceStatistics", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<HomePageResp.ParkingSpaceStatisticsDTO> parkingSpaceStatistics(@RequestBody HomePageReq.Query1DTO dto) {
        // 响应数据转换
       return ResResult.success(HomePageConverter.INSTANCE.toDto(systemParkingStatisticService.parkingSpaceStatistics(dto.getUserId())));
    }
}
