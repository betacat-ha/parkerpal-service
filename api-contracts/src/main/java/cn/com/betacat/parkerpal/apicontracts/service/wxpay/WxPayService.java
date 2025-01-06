package cn.com.betacat.parkerpal.apicontracts.service.wxpay;

import cn.com.betacat.parkerpal.apicontracts.dto.req.WxPayReq;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.WxPayResp;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface WxPayService {

    WxPayResp.RespDTO wxPay(WxPayReq.QueryDTO dto);

    Map<String, String> wxOrderCallBack(HttpServletRequest request, HttpServletResponse response);
}
