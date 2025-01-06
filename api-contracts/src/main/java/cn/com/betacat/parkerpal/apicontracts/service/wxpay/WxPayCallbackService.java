package cn.com.betacat.parkerpal.apicontracts.service.wxpay;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WxPayCallbackService {

    Map<String, String> wxChatPayCallback(HttpServletRequest request, HttpServletResponse response);

    Map<String,String> convertWechatPayMsgToMap(String plainBody);

    String decryptBody(String body) throws GeneralSecurityException, UnsupportedEncodingException;

    boolean verifiedSign(String serialNo, String signStr, String signature)
        throws UnsupportedEncodingException;

    String getRequestBody(HttpServletRequest request);



}
