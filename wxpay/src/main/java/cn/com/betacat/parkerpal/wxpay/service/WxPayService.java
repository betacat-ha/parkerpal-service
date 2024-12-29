package cn.com.betacat.parkerpal.wxpay.service;

import cn.com.betacat.parkerpal.wxpay.domain.WxChatPayDto;
import cn.com.betacat.parkerpal.wxpay.domain.WxPayReqParam;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author:
 * @Since: 2024-09-05 13:58:20
 * @Description:
 */
public interface WxPayService {
    WxChatPayDto wxPay(WxPayReqParam param);

    Map<String, String> wxOrderCallBack(HttpServletRequest request, HttpServletResponse response);
}
