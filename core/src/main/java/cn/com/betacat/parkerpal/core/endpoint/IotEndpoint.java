package cn.com.betacat.parkerpal.core.endpoint;

import cn.com.betacat.parkerpal.common.annotation.PassToken;
import cn.com.betacat.parkerpal.common.exception.RequestObject;
import cn.com.betacat.parkerpal.common.exception.ResponseObject;
import cn.com.betacat.parkerpal.core.endpoint.request.IotDeviceManagementReq;
import cn.com.betacat.parkerpal.iot.service.MqttService;
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
// @Api(tags = "IOT端口")
@RequestMapping("iot")
public class IotEndpoint {
    @Autowired
    private MqttService mqttService;

    @PassToken(required = false)
    @ApiOperation(value = "IOT - 发送消息")
    @PostMapping(value = "/send", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject send(@RequestBody RequestObject<IotDeviceManagementReq.SendMessageToDevice> ro) {
        // mqttService.sendToMqtt(, 1, content);
        return ResponseObject.success();
    }


}
