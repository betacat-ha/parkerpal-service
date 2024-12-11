package cn.com.betacat.parkerpal.core.endpoint;

import cn.com.betacat.parkerpal.common.annotation.PassToken;
import cn.com.betacat.parkerpal.common.domain.CarVisitor;
import cn.com.betacat.parkerpal.common.domain.CarVpiMonthlyRent;
import cn.com.betacat.parkerpal.common.domain.query.CarVisitorQuery;
import cn.com.betacat.parkerpal.common.domain.query.CarVpiMonthlyRentQuery;
import cn.com.betacat.parkerpal.common.domain.query.RecordCarEnterQuery;
import cn.com.betacat.parkerpal.common.domain.query.RecordsCarOutboundQuery;
import cn.com.betacat.parkerpal.common.exception.RequestObject;
import cn.com.betacat.parkerpal.common.exception.ResponseObject;
import cn.com.betacat.parkerpal.common.pageutil.PageInfoRespQuery;
import cn.com.betacat.parkerpal.core.endpoint.converter.CarVisitorConverter;
import cn.com.betacat.parkerpal.core.endpoint.converter.CarVpiMonthlyRentConverter;
import cn.com.betacat.parkerpal.core.endpoint.converter.RecordCarEnterConverter;
import cn.com.betacat.parkerpal.core.endpoint.converter.RecordsCarOutboundConverter;
import cn.com.betacat.parkerpal.core.endpoint.request.CarVisitorReq;
import cn.com.betacat.parkerpal.core.endpoint.request.CarVpiMonthlyRentReq;
import cn.com.betacat.parkerpal.core.endpoint.request.RecordCarEnterReq;
import cn.com.betacat.parkerpal.core.endpoint.request.RecordsCarOutboundReq;
import cn.com.betacat.parkerpal.core.endpoint.response.CarVisitorResp;
import cn.com.betacat.parkerpal.core.endpoint.response.CarVpiMonthlyRentResp;
import cn.com.betacat.parkerpal.core.endpoint.response.RecordCarEnterResp;
import cn.com.betacat.parkerpal.core.endpoint.response.RecordsCarOutboundResp;
import cn.com.betacat.parkerpal.core.service.CarVisitorService;
import cn.com.betacat.parkerpal.core.service.CarVpiMonthlyRentService;
import cn.com.betacat.parkerpal.core.service.RecordCarEnterService;
import cn.com.betacat.parkerpal.core.service.RecordsCarOutboundService;
import cn.com.betacat.parkerpal.common.utils.AuthorityType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Zoey
 * @Since: 2024-08-14 13:26:32
 * @Description:
 */
@RestController
@Api(tags = "车辆管理")
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
    public ResponseObject<CarVisitorResp.ListDTO> visitorVansPageList(@RequestBody RequestObject<CarVisitorReq.QueryDTO> ro) {
        // 请求数据转换
        CarVisitorQuery query = CarVisitorConverter.INSTANCE.toQuery(ro.getBody());
        // 调用服务
        PageInfoRespQuery resp = carVisitorService.getPageList(query);
        // 响应数据转换
        return ResponseObject.success(CarVisitorConverter.INSTANCE.toPage(resp));
    }

    @ApiOperation(value = "访客车登记-新增或修改")
    @PassToken(required = false, authority = AuthorityType.CREATE)
    @PostMapping(value = "/newOrUpdatedVisitorVans", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<?> newOrUpdatedVisitorVans(@RequestBody RequestObject<CarVisitorReq.CreateOrUpdateDTO> ro) {
        // 请求数据转换
        CarVisitor entity = CarVisitorConverter.INSTANCE.toEntity(ro.getBody());
        // 新增或更新
        carVisitorService.createOrUpdate(entity);
        // 响应数据转换
        return ResponseObject.success();
    }

    @ApiOperation(value = "访客车登记-删除")
    @PassToken(required = false, authority = AuthorityType.DELETE)
    @PostMapping(value = "/deleteVisitorVans", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<?> deleteVisitorVans(@RequestBody RequestObject<CarVisitorReq.DeleteDTO> ro) {
        // 删除
        carVisitorService.removeBatchByIds(ro.getBody().getIds());
        // 响应数据转换
        return ResponseObject.success();
    }

    @PassToken(required = false)
    @ApiOperation(value = "vip月租车登记-分页查询列表")
    @PostMapping(value = "/monthlyCarPageList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<CarVpiMonthlyRentResp.ListDTO> monthlyCarPageList(@RequestBody RequestObject<CarVpiMonthlyRentReq.QueryDTO> ro) {
        // 请求数据转换
        CarVpiMonthlyRentQuery query = CarVpiMonthlyRentConverter.INSTANCE.toQuery(ro.getBody());
        // 调用服务
        PageInfoRespQuery resp = carVpiMonthlyRentService.getPageList(query);
        // 响应数据转换
        return ResponseObject.success(CarVpiMonthlyRentConverter.INSTANCE.toPage(resp));
    }

    @ApiOperation(value = "vip月租车登记-新增或修改")
    @PassToken(required = false, authority = AuthorityType.CREATE)
    @PostMapping(value = "/newOrUpdatedMonthlyCar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<?> newOrUpdatedMonthlyCar(@RequestBody RequestObject<CarVpiMonthlyRentReq.CreateOrUpdateDTO> ro) {
        // 请求数据转换
        CarVpiMonthlyRent entity = CarVpiMonthlyRentConverter.INSTANCE.toEntity(ro.getBody());
        // 新增或更新
        carVpiMonthlyRentService.createOrUpdate(entity);
        // 响应数据转换
        return ResponseObject.success();
    }

    @ApiOperation(value = "vip月租车登记-删除")
    @PassToken(required = false, authority = AuthorityType.DELETE)
    @PostMapping(value = "/deleteMonthlyCar", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<?> deleteMonthlyCar(@RequestBody RequestObject<CarVpiMonthlyRentReq.DeleteDTO> ro) {
        // 删除
        carVpiMonthlyRentService.removeBatchByIds(ro.getBody().getIds());
        // 响应数据转换
        return ResponseObject.success();
    }

    @PassToken(required = false)
    @ApiOperation(value = "车辆进场记录-分页查询列表")
    @PostMapping(value = "/getInventoryPageList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<RecordCarEnterResp.ListDTO> getInventoryPageList(@RequestBody RequestObject<RecordCarEnterReq.QueryDTO> ro) {
        // 请求数据转换
        RecordCarEnterQuery query = RecordCarEnterConverter.INSTANCE.toQuery(ro.getBody());
        // 调用服务
        PageInfoRespQuery resp = recordCarEnterService.getPageList(query);
        // 响应数据转换
        return ResponseObject.success(RecordCarEnterConverter.INSTANCE.toPage(resp));
    }

    @PassToken(required = false)
    @ApiOperation(value = "车辆出场记录-分页查询列表")
    @PostMapping(value = "/getOutboundPageList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<RecordsCarOutboundResp.ListDTO> getOutboundPageList(@RequestBody RequestObject<RecordsCarOutboundReq.QueryDTO> ro) {
        // 请求数据转换
        RecordsCarOutboundQuery query = RecordsCarOutboundConverter.INSTANCE.toQuery(ro.getBody());
        // 调用服务
        PageInfoRespQuery resp = recordsCarOutboundService.getPageList(query);
        // 响应数据转换
        return ResponseObject.success(RecordsCarOutboundConverter.INSTANCE.toPage(resp));
    }
}
