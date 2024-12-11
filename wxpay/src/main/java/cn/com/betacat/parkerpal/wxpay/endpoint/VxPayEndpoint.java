package cn.com.betacat.parkerpal.wxpay.endpoint;

import cn.com.betacat.parkerpal.common.exception.RequestObject;
import cn.com.betacat.parkerpal.common.exception.ResponseObject;
import cn.com.betacat.parkerpal.wxpay.domain.WxChatPayDto;
import cn.com.betacat.parkerpal.wxpay.endpoint.converter.VxPayConverter;
import cn.com.betacat.parkerpal.wxpay.endpoint.request.WxPayReq;
import cn.com.betacat.parkerpal.wxpay.endpoint.response.WxPayResp;
import cn.com.betacat.parkerpal.wxpay.service.WxPayService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Zoey
 * @Since: 2024-09-05 13:59:01
 * @Description:
 */

@RestController
@Api(tags = "微信支付")
@RequestMapping("vxPay")
public class VxPayEndpoint {

    @Resource
    private WxPayService vxPayService;


    @ApiOperation(value = "微信支付")
    @PostMapping(value = "/vxv3pay", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<WxPayResp.RespDTO> nativePay (@RequestBody RequestObject<WxPayReq.QueryDTO> ro) {
        WxChatPayDto wxChatPayDto = vxPayService.wxPay(VxPayConverter.INSTANCE.toEntity(ro.getBody()));
        return ResponseObject.success(VxPayConverter.INSTANCE.toDto(wxChatPayDto));
    }

    /**
     * 微信支付通过支付通知接口将用户支付成功消息通知给商户
     * 该接口是给微信回调用的
     *
     * @param request
     * @param response
     * @return
     */
    @CrossOrigin
    @PostMapping("/payNotify")
    public Map<String, String> nativeNotify(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("微信支付成功回调进来了");
        return vxPayService.wxOrderCallBack(request, response);
    }
}
