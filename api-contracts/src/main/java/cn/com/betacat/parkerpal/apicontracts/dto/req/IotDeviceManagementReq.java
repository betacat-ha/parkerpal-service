package cn.com.betacat.parkerpal.apicontracts.dto.req;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class IotDeviceManagementReq implements Serializable {
    private static final long serialVersionUID = -74178363975029545L;

    @Getter
    @Setter
    public static class CreateOrUpdateDTO implements Serializable {
        private static final long serialVersionUID = -43355946137091835L;
        //@formatter:off
        private String id;
        private String macId;
        private String appId;
        private String secret;
        private String currency;
        private String privateKeyPath;
        private String apiV3Key;
        private String macSerialNo;
        //@formatter:on
    }

    @Getter
    @Setter
    @ApiModel(value = "IotDeviceManagementResp.SendMessageToDevice", description = "发送-数据至设备")
    public static class SendMessageToDevice implements Serializable {
        private static final long serialVersionUID = -43355946137091835L;
        //@formatter:off
        private String deviceId;
        private String content;
        //@formatter:on
    }

    @Getter
    @Setter
    @ApiModel(value = "IotDeviceManagementResp.SendConfigToDevice", description = "发送-配置至设备")
    public static class SendConfigToDevice implements Serializable {
        private static final long serialVersionUID = -43355946137091835L;
        //@formatter:off
        private String macAddress;
        //@formatter:on
    }
}
