package cn.com.betacat.parkerpal.apigateway.http;

import cn.com.betacat.parkerpal.apicontracts.converter.CarVisitorConverter;
import cn.com.betacat.parkerpal.apicontracts.converter.CarVpiMonthlyRentConverter;
import cn.com.betacat.parkerpal.apicontracts.converter.RecordCarEnterConverter;
import cn.com.betacat.parkerpal.apicontracts.converter.RecordsCarOutboundConverter;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.CarVisitorResp.ListDTO;
import cn.com.betacat.parkerpal.apicontracts.service.CarVisitorService;
import cn.com.betacat.parkerpal.apicontracts.service.CarVpiMonthlyRentService;
import cn.com.betacat.parkerpal.apicontracts.service.RecordCarEnterService;
import cn.com.betacat.parkerpal.apicontracts.service.RecordsCarOutboundService;
import cn.com.betacat.parkerpal.common.annotation.PassToken;
import cn.com.betacat.parkerpal.apicontracts.dto.req.CarVisitorReq;
import cn.com.betacat.parkerpal.apicontracts.dto.req.CarVpiMonthlyRentReq;
import cn.com.betacat.parkerpal.apicontracts.dto.req.RecordCarEnterReq;
import cn.com.betacat.parkerpal.apicontracts.dto.req.RecordsCarOutboundReq;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.CarVpiMonthlyRentResp;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.RecordCarEnterResp;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.RecordsCarOutboundResp;
import cn.com.betacat.parkerpal.common.utils.AuthorityType;
import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import cn.com.betacat.parkerpal.domain.base.ResResult;
import cn.com.betacat.parkerpal.domain.entity.CarVisitor;
import cn.com.betacat.parkerpal.domain.entity.CarVpiMonthlyRent;
import cn.com.betacat.parkerpal.domain.query.CarVisitorQuery;
import cn.com.betacat.parkerpal.domain.query.CarVpiMonthlyRentQuery;
import cn.com.betacat.parkerpal.domain.query.RecordCarEnterQuery;
import cn.com.betacat.parkerpal.domain.query.RecordsCarOutboundQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:
 * @Since: 2024-08-14 13:26:32
 * @Description:
 */
@Api(tags = "车辆管理")
@RestController
@RequestMapping("vehicleManagement")
public class VehicleEndpoint {

    @Autowired
    private CarVisitorService carVisitorService;
    @Autowired
    private RecordCarEnterService recordCarEnterService;
    @Autowired
    private CarVpiMonthlyRentService carVpiMonthlyRentService;
    @Autowired
    private RecordsCarOutboundService recordsCarOutboundService;

    @PassToken(required = false)
    @ApiOperation(value = "访客车登记-分页查询管列表")
    @PostMapping(value = "/visitorVansPageList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<ListDTO> visitorVansPageList(@RequestBody CarVisitorReq.QueryDTO dto) {
        // 请求数据转换
        CarVisitorQuery query = CarVisitorConverter.INSTANCE.toQuery(dto);
        // 调用服务
        PageInfoRespQuery resp = carVisitorService.getPageList(query);
        // 响应数据转换
        return ResResult.success(CarVisitorConverter.INSTANCE.toPage(resp));
    }

    @ApiOperation(value = "访客车登记-新增或修改")
    @PassToken(required = false, authority = AuthorityType.CREATE)
    @PostMapping(value = "/newOrUpdatedVisitorVans", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> newOrUpdatedVisitorVans(@RequestBody CarVisitorReq.CreateOrUpdateDTO dto) {
        // 请求数据转换
        CarVisitor entity = CarVisitorConverter.INSTANCE.toEntity(dto);
        // 新增或更新
        carVisitorService.createOrUpdate(entity);
        // 响应数据转换
        return ResResult.success();
    }

    @ApiOperation(value = "访客车登记-删除")
    @PassToken(required = false, authority = AuthorityType.DELETE)
    @PostMapping(value = "/deleteVisitorVans", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> deleteVisitorVans(@RequestBody CarVisitorReq.DeleteDTO dto) {
        // 删除
        carVisitorService.removeBatchByIds(dto.getIds());
        // 响应数据转换
        return ResResult.success();
    }

    @PassToken(required = false)
    @ApiOperation(value = "vip月租车登记-分页查询列表")
    @PostMapping(value = "/monthlyCarPageList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<CarVpiMonthlyRentResp.ListDTO> monthlyCarPageList(@RequestBody CarVpiMonthlyRentReq.QueryDTO dto) {
        // 请求数据转换
        CarVpiMonthlyRentQuery query = CarVpiMonthlyRentConverter.INSTANCE.toQuery(dto);
        // 调用服务
        PageInfoRespQuery resp = carVpiMonthlyRentService.getPageList(query);
        // 响应数据转换
        return ResResult.success(CarVpiMonthlyRentConverter.INSTANCE.toPage(resp));
    }

    @ApiOperation(value = "vip月租车登记-新增或修改")
    @PassToken(required = false, authority = AuthorityType.CREATE)
    @PostMapping(value = "/newOrUpdatedMonthlyCar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> newOrUpdatedMonthlyCar(@RequestBody CarVpiMonthlyRentReq.CreateOrUpdateDTO dto) {
        // 请求数据转换
        CarVpiMonthlyRent entity = CarVpiMonthlyRentConverter.INSTANCE.toEntity(dto);
        // 新增或更新
        carVpiMonthlyRentService.createOrUpdate(entity);
        // 响应数据转换
        return ResResult.success();
    }

    @ApiOperation(value = "vip月租车登记-删除")
    @PassToken(required = false, authority = AuthorityType.DELETE)
    @PostMapping(value = "/deleteMonthlyCar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> deleteMonthlyCar(@RequestBody CarVpiMonthlyRentReq.DeleteDTO dto) {
        // 删除
        carVpiMonthlyRentService.removeBatchByIds(dto.getIds());
        // 响应数据转换
        return ResResult.success();
    }

    @PassToken(required = false)
    @ApiOperation(value = "车辆进场记录-分页查询列表")
    @PostMapping(value = "/getInventoryPageList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<RecordCarEnterResp.ListDTO> getInventoryPageList(@RequestBody RecordCarEnterReq.QueryDTO dto) {
        // 请求数据转换
        RecordCarEnterQuery query = RecordCarEnterConverter.INSTANCE.toQuery(dto);
        // 调用服务
        PageInfoRespQuery resp = recordCarEnterService.getPageList(query);
        // 响应数据转换
        return ResResult.success(RecordCarEnterConverter.INSTANCE.toPage(resp));
    }

    @PassToken(required = false)
    @ApiOperation(value = "车辆出场记录-分页查询列表")
    @PostMapping(value = "/getOutboundPageList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<RecordsCarOutboundResp.ListDTO> getOutboundPageList(@RequestBody RecordsCarOutboundReq.QueryDTO dto) {
        // 请求数据转换
        RecordsCarOutboundQuery query = RecordsCarOutboundConverter.INSTANCE.toQuery(dto);
        // 调用服务
        PageInfoRespQuery resp = recordsCarOutboundService.getPageList(query);
        // 响应数据转换
        return ResResult.success(RecordsCarOutboundConverter.INSTANCE.toPage(resp));
    }
}
