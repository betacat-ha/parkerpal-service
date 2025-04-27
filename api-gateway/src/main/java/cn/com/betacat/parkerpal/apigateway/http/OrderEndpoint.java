package cn.com.betacat.parkerpal.apigateway.http;

import cn.com.betacat.parkerpal.apicontracts.converter.OrderPaidCatOutboundConverter;
import cn.com.betacat.parkerpal.apicontracts.dto.req.OrderPaidCatOutboundReq;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.OrderPaidCatOutboundResp;
import cn.com.betacat.parkerpal.apicontracts.service.OrderPaidCatOutboundService;
import cn.com.betacat.parkerpal.common.annotation.PassToken;
import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import cn.com.betacat.parkerpal.domain.base.ResResult;
import cn.com.betacat.parkerpal.domain.query.OrderPaidCatOutboundQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  
 * @since 2024-08-20 14:07:03
 */
@RestController
@Api(tags = "订单管理")
@RequestMapping("orderEndpoint")
public class OrderEndpoint {

    /**
     * 服务对象
     */
    @Autowired
    private OrderPaidCatOutboundService orderPaidCatOutboundService;

    @PassToken(required = false)
    @ApiOperation(value = "车辆出库订单记录-分页查询列表")
    @PostMapping(value = "/pageList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<OrderPaidCatOutboundResp.ListDTO> pageList(@RequestBody OrderPaidCatOutboundReq.QueryDTO dto) {
        // 请求数据转换
        OrderPaidCatOutboundQuery query = OrderPaidCatOutboundConverter.INSTANCE.toQuery(dto);
        // 调用服务
        PageInfoRespQuery resp = orderPaidCatOutboundService.getPageList(query);
        // 响应数据转换
        return ResResult.success(OrderPaidCatOutboundConverter.INSTANCE.toPage(resp));
    }
}
