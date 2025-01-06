package cn.com.betacat.parkerpal.apigateway.http;

import cn.com.betacat.parkerpal.apicontracts.dto.req.IotDeviceManagementReq;
import cn.com.betacat.parkerpal.apicontracts.service.MqttService;
import cn.com.betacat.parkerpal.common.annotation.PassToken;
import cn.com.betacat.parkerpal.domain.base.ResResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Api(tags = "IOT端口")
@RequestMapping("iot")
public class IotEndpoint {
    @Autowired
    private MqttService mqttService;

    @PassToken(required = false)
    @ApiOperation(value = "IOT - 发送消息")
    @PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult send(@RequestBody IotDeviceManagementReq.SendMessageToDevice dto) {
         mqttService.sendToMqtt(dto.getDeviceId(), 1, dto.getContent());
        return ResResult.success();
    }


}
