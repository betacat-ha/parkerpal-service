package cn.com.betacat.parkerpal.core.endpoint.response;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class IotDeviceManagementResp implements Serializable {
    private static final long serialVersionUID = -74178363975029545L;

    @Getter
    @Setter
    @ApiModel(value = "IotDeviceManagementResp.SendMessageToDevice", description = "发送-数据至设备")
    public static class SendMessageToDevice implements Serializable {
        private static final long serialVersionUID = -43355946137091835L;
        //@formatter:off
        //@formatter:on
    }
}
