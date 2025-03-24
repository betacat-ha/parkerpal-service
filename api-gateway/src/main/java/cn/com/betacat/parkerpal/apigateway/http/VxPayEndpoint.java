package cn.com.betacat.parkerpal.apigateway.http;


import cn.com.betacat.parkerpal.apicontracts.dto.req.WxPayReq;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.WxPayResp.RespDTO;
import cn.com.betacat.parkerpal.apicontracts.service.wxpay.WxPayService;
import cn.com.betacat.parkerpal.domain.base.ResResult;
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
 * @Author:
 * @Since: 2024-09-05 13:59:01
 * @Description:
 */


@Api(tags = "微信支付")
@RestController
@RequestMapping("vxPay")
public class VxPayEndpoint {

    @Resource
    private WxPayService vxPayService;


    @ApiOperation(value = "微信支付")
    @PostMapping(value = "/vxv3pay", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<RespDTO> nativePay (@RequestBody WxPayReq.QueryDTO dto) {
        RespDTO respDTO = vxPayService.wxPay(dto);
        return ResResult.success(respDTO);
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
