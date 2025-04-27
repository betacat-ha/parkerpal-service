package cn.com.betacat.parkerpal.apicontracts.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author  
 * @since 2024-09-05 14:03:55
 */
public class WxPayReq implements Serializable {
    private static final long serialVersionUID = -24599605854870981L;

    @Getter
    @Setter
    @ApiModel(value = "WxPayReq.QueryDTO", description = "微信支付输入DTO")
    public static class QueryDTO implements Serializable {
        private static final long serialVersionUID = 303840512429333818L;
        //@formatter:off
        @ApiModelProperty(value = "总金额", position = 1, required = true)
        private String totalPrice;
        @ApiModelProperty(value = "用户code", position = 2, required = true)
        private String code;
        @ApiModelProperty(value = "订单号", position = 3, required = true)
        private String orderNumber;
        //@formatter:on
    }

    // 支付结果回调
    @Getter
    @Setter
    @ApiModel(value = "WxPayReq.PayActionDTO", description = "微信支付结果回调")
    public static class PayActionDTO implements Serializable {
        private static final long serialVersionUID = 303840512429333818L;
        //@formatter:off
        @ApiModelProperty(value = "订单id", position = 1, required = true)
        private String parking_id;
        @ApiModelProperty(value = "支付状态", position = 2, required = true)
        private String trade_state;
        //@formatter:on
    }
}
