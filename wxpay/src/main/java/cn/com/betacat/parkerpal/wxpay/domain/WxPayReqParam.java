package cn.com.betacat.parkerpal.wxpay.domain;

import lombok.Data;

/**
 * @Author:
 * @Since: 2024-09-05 13:31:05
 * @Description:
 */
@Data
public class WxPayReqParam {

    /**
     * 总金额
     */
    private String totalPrice;

    /**
     * 用户code
     */
    private String code;

    /**
     * 订单号
     */
    private String orderNumber;
}
