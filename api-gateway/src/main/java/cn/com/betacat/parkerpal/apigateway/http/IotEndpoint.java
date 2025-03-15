package cn.com.betacat.parkerpal.apigateway.http;

import cn.com.betacat.parkerpal.apicontracts.dto.req.IotDeviceManagementReq;
import cn.com.betacat.parkerpal.apicontracts.service.IotDeviceService;
import cn.com.betacat.parkerpal.apicontracts.service.SystemParkingSpaceService;
import cn.com.betacat.parkerpal.common.annotation.PassToken;
import cn.com.betacat.parkerpal.domain.base.ResResult;
import cn.com.betacat.parkerpal.domain.entity.IotDevice;
import cn.com.betacat.parkerpal.domain.entity.SystemParkingSpace;
import cn.com.betacat.parkerpal.domain.enums.RespEnum;
import cn.com.betacat.parkerpal.domain.query.IotDeviceQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@Api(tags = "IOT端口")
@RequestMapping("iot")
public class IotEndpoint {
    @Autowired
    private IotDeviceService iotDeviceService;

    @Autowired
    private SystemParkingSpaceService systemParkingSpaceService;

    @PassToken(required = false)
    @ApiOperation(value = "IOT - 发送消息")
    @PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> send(@RequestBody IotDeviceManagementReq.SendMessageToDevice dto) {
         iotDeviceService.sendToMqtt(dto.getDeviceId(), 1, dto.getContent());
        return ResResult.success();
    }

    @PassToken(required = false)
    @ApiOperation(value = "IOT - 发送配置到设备")
    @PostMapping(value = "/sendConfig", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> sendConfig(@RequestBody IotDeviceManagementReq.SendConfigToDevice dto) {
        iotDeviceService.sendConfig(dto.getMacAddress());
        return ResResult.success();
    }

    // @PassToken(required = false)
    // @ApiOperation(value = "IOT - 获取设备状态列表")
    // @PostMapping(value = "/getDeviceStatusList", consumes = MediaType.APPLICATION_JSON_VALUE)
    // public ResResult getDeviceStatusList(@RequestBody IotDevice iotDevice) {
    //     return ResResult.success(iotDeviceService.getIotStatusList());
    // }

    @PassToken(required = false)
    @ApiOperation(value = "IOT - 获取设备列表")
    @PostMapping(value = "/getDeviceList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> getDeviceStatusList(@RequestBody IotDeviceQuery query) {
        return ResResult.success(iotDeviceService.getPageList(query));
    }

    @PassToken(required = false)
    @ApiOperation(value = "IOT - 更新设备信息")
    @PostMapping(value = "/newOrUpdate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> addOrUpdate(@RequestBody IotDevice iotDevice) {
        return ResResult.success(iotDeviceService.addOrUpdateDevice(iotDevice));
    }

    @PassToken(required = false)
    @ApiOperation(value = "IOT - 删除设备")
    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> delete(@RequestBody IotDeviceManagementReq.DeleteDTO dto) {
        List<String> deviceIds = dto.getIds();
        try {
            iotDeviceService.deleteDevice(deviceIds);
        } catch (Exception e) {
            log.error("删除设备失败", e);
            return ResResult.error(RespEnum.FAILURE.getCode(), "删除设备失败，请刷新页面重试");
        }
        return ResResult.success();
    }

    @PassToken(required = false)
    @ApiOperation(value = "IOT - 获取停车位状态信息")
    @PostMapping(value = "/getParkingSpaceStatus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> getParkingSpaceStatus(@RequestBody SystemParkingSpace dto) {
        try {
            return ResResult.success(systemParkingSpaceService.selectAll(dto));
        } catch (Exception e) {
            log.error("获取停车位状态信息失败", e);
            return ResResult.error(RespEnum.FAILURE.getCode(), "获取停车位状态信息失败，请刷新页面重试");
        }
    }



    // @PassToken(required = false)
    // @ApiOperation(value = "IOT - 分页查询列表")
    // @PostMapping(value = "/pageDeviceList", consumes = MediaType.APPLICATION_JSON_VALUE)
    // public ResResult pageDeviceList(@RequestBody IotDeviceManagementReq.GetDeviceStatusList dto) {
    //     // 请求数据转换
    //     SystemUsersQuery query = SystemUsersConverter.INSTANCE.toQuery(dto);
    //     // 调用服务
    //     PageInfoRespQuery resp = iotDeviceService.getPageList(query);
    //     // 响应数据转换
    //     return ResResult.success(SystemUsersConverter.INSTANCE.toPage(resp));
    // }

}
