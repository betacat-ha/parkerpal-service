package cn.com.betacat.parkerpal.apigateway.http;

import cn.com.betacat.parkerpal.apicontracts.converter.OrderPaidCatOutboundConverter;
import cn.com.betacat.parkerpal.apicontracts.converter.ParkCollectCouponsConverter;
import cn.com.betacat.parkerpal.apicontracts.converter.SystemUsersConverter;
import cn.com.betacat.parkerpal.apicontracts.dto.req.ApiReq;
import cn.com.betacat.parkerpal.apicontracts.dto.req.OrderPaidCatOutboundReq;
import cn.com.betacat.parkerpal.apicontracts.dto.req.SystemUsersReq;
import cn.com.betacat.parkerpal.apicontracts.dto.req.WxPayReq;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.OrderPaidCatOutboundResp;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.ParkCollectCouponsResp;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.SystemUsersResp;
import cn.com.betacat.parkerpal.apicontracts.service.OrderPaidCatOutboundService;
import cn.com.betacat.parkerpal.apicontracts.service.ParkCollectCouponsService;
import cn.com.betacat.parkerpal.apicontracts.service.mixin.EnterParkingService;
import cn.com.betacat.parkerpal.apicontracts.service.mixin.ExitParkingService;
import cn.com.betacat.parkerpal.apicontracts.service.mixin.OrderService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemCameraDeviceService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemUsersService;
import cn.com.betacat.parkerpal.domain.base.ResResult;
import cn.com.betacat.parkerpal.domain.entity.OrderPaidCatOutbound;
import cn.com.betacat.parkerpal.domain.entity.ParkCollectCoupons;
import cn.com.betacat.parkerpal.domain.entity.SystemCameraDevice;
import cn.com.betacat.parkerpal.domain.entity.SystemUsers;
import cn.com.betacat.parkerpal.domain.enums.OrderStatus;
import cn.com.betacat.parkerpal.domain.enums.RespEnum;
import cn.com.betacat.parkerpal.domain.query.SystemUsersQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@Api(tags = "对外API接口")
@RequestMapping("api")
public class ApiEndpoint {

    @Autowired
    private SystemCameraDeviceService systemCameraDeviceService;

    @Autowired
    private OrderPaidCatOutboundService orderPaidCatOutboundService;

    @Autowired
    private ParkCollectCouponsService parkCollectCouponsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ExitParkingService exitParkingService;

    @Autowired
    private EnterParkingService enterParkingService;

    @Autowired
    private SystemUsersService systemUsersService;

    @ApiOperation(value = "车辆订单查询-出口支付-提前支付")
    @PostMapping(value = "/queryOrder", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<OrderPaidCatOutboundResp.OrderPaidCatOutboundDTO> queryOrder(
            @RequestBody OrderPaidCatOutboundReq.QueryOrderDTO dto
    ) {
        // 响应数据转换
        return ResResult.success(
                OrderPaidCatOutboundConverter.INSTANCE.toDTO(
                        orderPaidCatOutboundService.getEntityByPlates(
                                dto.getPlates(),
                                dto.getDeviceIp())
                )
        );
    }

    @ApiOperation(value = "统计剩余抵用券")
    @PostMapping(value = "/assignedNumber", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<ParkCollectCouponsResp.ParkCollectDTO> assignedNumber(@RequestBody ApiReq.QueryDTO dto
    ) {
        LocalDateTime now = LocalDateTime.now();
        long timestamp = now.toInstant(ZoneOffset.UTC).toEpochMilli();
        if (dto.getTimestamp() != null && dto.getTimestamp() > 0 && dto.getTimestamp() < timestamp) {
            // 调用服务
            Map<String, Object> params = parkCollectCouponsService.assignedNumber(dto.getUserId());
            // 响应数据转换
            return ResResult.success(ParkCollectCouponsConverter.INSTANCE.toDTO(params));
        }
        return ResResult.error(300, "时间戳异常"); // TODO
    }

    @ApiOperation(value = "停车领劵")
    @PostMapping(value = "/createParkCollect", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<ParkCollectCouponsResp.ParkCollectCouponsDTO> createParkCollect(
            @RequestBody ApiReq.CreateDTO dto
    ) {
        // 调用服务
        ParkCollectCoupons entity = parkCollectCouponsService.userCollectCoupons(
                dto.getUserId()
                , dto.getPlates()
                , dto.getTimestamp()
        );
        // 响应数据转换
        return ResResult.success(ParkCollectCouponsConverter.INSTANCE.toDTO(entity));
    }

    @ApiOperation(value = "无牌车扫码进场")
    @PostMapping(value = "/qrCodeCarEnter")
    public ResResult<?> qrCodeCarEnter(@RequestBody OrderPaidCatOutboundReq.Query1DTO dto) {
        if (StringUtils.isBlank(dto.getPhoneNumber())) return ResResult.error(RespEnum.INPUT_ERROR_1);
        if (StringUtils.isBlank(dto.getDeviceIp())) return ResResult.error(RespEnum.INPUT_ERROR_2);
        if (StringUtils.isBlank(dto.getDevicePort())) return ResResult.error(RespEnum.INPUT_ERROR_3);
        // 根据设备的IP和端口号，查询该设备信息
        SystemCameraDevice cameraDevice = systemCameraDeviceService.getEntity(
                dto.getDeviceIp(),
                dto.getDevicePort()
        );
        // 创建车辆进场记录
        enterParkingService.createRecordCarEnterByPhoneNumber(dto.getPhoneNumber(), cameraDevice);
        // 响应数据转换
        return ResResult.success();
    }

    @ApiOperation(value = "无牌车扫码出场")
    @PostMapping(value = "/qrCodeCarOut")
    public ResResult<OrderPaidCatOutboundResp.OrderPaidCatOutboundDTO> qrCodeCarOut(
            @RequestBody OrderPaidCatOutboundReq.QueryDTO dto
    ) {
        // TODO
        return null;
    }

    @ApiOperation(value = "车辆进场")
    @PostMapping(value = "/carEnter")
    public ResResult<?> carEnter(@RequestBody OrderPaidCatOutboundReq.Query1DTO dto) {
        if (StringUtils.isBlank(dto.getPhoneNumber())) return ResResult.error(RespEnum.INPUT_ERROR_1);
        if (StringUtils.isBlank(dto.getDeviceIp())) return ResResult.error(RespEnum.INPUT_ERROR_2);
        if (StringUtils.isBlank(dto.getDevicePort())) return ResResult.error(RespEnum.INPUT_ERROR_3);
        // 根据设备的IP和端口号，查询该设备信息
        SystemCameraDevice cameraDevice = systemCameraDeviceService.getEntity(
                dto.getDeviceIp(),
                dto.getDevicePort()
        );
        // 创建车辆进场记录
        enterParkingService.createRecordCarEnter(dto.getPhoneNumber(), cameraDevice);
        // 响应数据转换
        return ResResult.success();
    }

    @ApiOperation(value = "车辆出场")
    @PostMapping(value = "/carOut")
    public ResResult<?> carOut(@RequestBody OrderPaidCatOutboundReq.Query1DTO dto) {
        if (StringUtils.isBlank(dto.getPhoneNumber())) return ResResult.error(RespEnum.INPUT_ERROR_1);
        if (StringUtils.isBlank(dto.getDeviceIp())) return ResResult.error(RespEnum.INPUT_ERROR_2);
        if (StringUtils.isBlank(dto.getDevicePort())) return ResResult.error(RespEnum.INPUT_ERROR_3);
        // 根据设备的IP和端口号，查询该设备信息
        SystemCameraDevice cameraDevice = systemCameraDeviceService.getEntity(
                dto.getDeviceIp(),
                dto.getDevicePort()
        );
        // 创建车辆出场记录
        exitParkingService.handleCarExit(cameraDevice, dto.getPhoneNumber());
        // 响应数据转换
        return ResResult.success();
    }

    @ApiOperation(value = "注册车主账号")
    @PostMapping(value = "/registerCustomer")
    public ResResult<SystemUsersResp.SystemUsersDTO> registerCustomer(@RequestBody SystemUsersReq.CreateOrUpdateDTO dto,  HttpServletRequest request) {
        dto.setId(null);
        dto.setRoleId("0");
        dto.setStatus(1);
        // 获取用户信息
        SystemUsers user = (SystemUsers) request.getAttribute("user");
        if (user == null) {
            return ResResult.error(RespEnum.FAILURE.getCode(), "用户不存在");
        }
        // 生成UUID
        dto.setAccount(user.getAccount() + "_" + UUID.randomUUID());;

        // 请求数据转换
        SystemUsers newEntity = SystemUsersConverter.INSTANCE.toEntity(dto);
        // 用户登录
        return ResResult.success(SystemUsersConverter.INSTANCE.toDTO(systemUsersService.createOrUpdateUser(newEntity)));
    }

    @ApiOperation(value = "登录车主账号")
    @PostMapping(value = "/loginCustomer")
    public ResResult<SystemUsersResp.SystemUsersDTO> loginCustomer(@RequestBody SystemUsersReq.LoginDTO dto) {
        // 请求数据转换
        SystemUsersQuery query = SystemUsersConverter.INSTANCE.toQuery(dto);
        // 用户登录
        return ResResult.success(SystemUsersConverter.INSTANCE.toDTO(systemUsersService.login(query)));
    }

    @ApiOperation(value = "获取室内地图数据")
    @PostMapping(value = "/getIndoorMapData")
    public ResResult<?> getIndoorMapData(@RequestBody ApiReq.InnerDoorMap dto) {
        if (StringUtils.isBlank(dto.getPoi()) || !Objects.equals(dto.getPoi(), "1005"))
            return ResResult.error(RespEnum.NOT_FOUND_ERROR_PARKING_SPACE);

        // 响应数据转换
        return ResResult.success("https://cdn.betacat.com.cn/indoorMap/" + dto.getPoi() + ".data");
    }

    @ApiOperation(value = "聚合支付成功回调")
    @PostMapping(value = "/paySuccess")
    public ResResult<?> paySuccess(@RequestBody WxPayReq.PayActionDTO dto) {
        if (StringUtils.isBlank(dto.getParking_id())) return ResResult.error(RespEnum.FAILURE);
        if (StringUtils.isBlank(dto.getTrade_state())) return ResResult.error(RespEnum.FAILURE);
        // 根据订单号查询订单
        OrderPaidCatOutbound order = orderPaidCatOutboundService.getByOrderId(dto.getParking_id());
        if (order == null) return ResResult.error(RespEnum.FAILURE);
        // 判断支付状态
        if (dto.getTrade_state().equals("SUCCESS")) {
            // 支付成功
            order.setPayStatus(OrderStatus.SUCCESS.getType());
            order.setPayTime(LocalDateTime.now());
            orderPaidCatOutboundService.updateById(order);
        }
        // 响应数据转换
        return ResResult.success();
    }
}
