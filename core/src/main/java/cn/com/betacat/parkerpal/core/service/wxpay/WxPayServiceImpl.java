package cn.com.betacat.parkerpal.core.service.wxpay;

import cn.com.betacat.parkerpal.apicontracts.dto.req.WxPayReq;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.WxPayResp;
import cn.com.betacat.parkerpal.apicontracts.service.wxpay.WeChatJsapiPayService;
import cn.com.betacat.parkerpal.apicontracts.service.wxpay.WxPayService;
import cn.com.betacat.parkerpal.common.constants.AppConstants;
import cn.com.betacat.parkerpal.common.utils.UploadUtils;
import cn.com.betacat.parkerpal.core.exception.BizException;
import cn.com.betacat.parkerpal.domain.entity.SystemWeChatJsapiPay;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import java.io.IOException;
import java.io.InputStream;
import java.security.Signature;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

/**
 * @author  
 * @since 2024-09-05 13:26:18
 * @Description: 微信支付服务实现类
 */
@Slf4j
@Service
public class WxPayServiceImpl implements WxPayService {

    @Value("${file.thirdParty}")
    private Boolean thirdParty;

    @Resource
    private WxPayCallbackServiceImpl wxChatPayCallbackUtil;

    @Resource
    private WeChatJsapiPayService weChatJsapiPayService;


    /**
     * 微信用户调用微信支付
     */
    @Override
    public WxPayResp.RespDTO wxPay(WxPayReq.QueryDTO dto) {
        return null;
    }

    /**
     * 微信支付成功回调
     *
     * @param request  request
     * @param response response
     * @return map
     */
    @Override
    public Map<String, String> wxOrderCallBack(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    /**
     * 获取用户的openid
     *
     * @param code
     */
    public String getWxOpenId(String code) {
        return "TODO";
    }

    /**
     * 获取签名
     *
     * @param nonceStr  随机数
     * @param appId     微信公众号或者小程序等的appid
     * @param prepay_id 预支付交易会话ID
     * @param timestamp 时间戳 10位
     * @return String 新签名
     */
    String getSign(String nonceStr, String appId, String prepay_id, long timestamp) {
        //从下往上依次生成
        String message = buildMessage(appId, timestamp, nonceStr, prepay_id);
        //签名
        try {
            return sign(message.getBytes(AppConstants.CHARSET));
        } catch (IOException e) {
            throw new BizException("签名异常,请检查参数或商户私钥");
        }
    }

    String sign(byte[] message) {
        try {
            SystemWeChatJsapiPay entity = weChatJsapiPayService.getWeChatJsapiPay();
            if (Objects.isNull(entity)) throw new BizException("微信JSAPI支付配置信息不存在");
            //签名方式
            Signature sign = Signature.getInstance("SHA256withRSA");
            // 获取商户私钥文件地址
            String privateKeyPath = entity.getPrivateKeyPath();
            if (thirdParty) {
                //从第三方服务器中读取私钥
                sign.initSign(PemUtil.loadPrivateKey(UploadUtils.readPrivateKeyFromServer(privateKeyPath)));
            } else {
                FileSystemResource resource = new FileSystemResource(privateKeyPath);
                InputStream fis = resource.getInputStream();
                //私钥，通过MyPrivateKey来获取，这是个静态类可以接调用方法 ，需要的是_key.pem文件的绝对路径配上文件名
                sign.initSign(PemUtil.loadPrivateKey(fis));
            }
            sign.update(message);
            return Base64.getEncoder().encodeToString(sign.sign());
        } catch (Exception e) {
            throw new BizException("签名异常,请检查参数或商户私钥");
        }
    }

    /**
     * 按照前端签名文档规范进行排序，\n是换行
     *
     * @param nonceStr  随机数
     * @param appId     微信公众号或者小程序等的appid
     * @param prepay_id 预支付交易会话ID
     * @param timestamp 时间戳 10位
     * @return String 新签名
     */
    String buildMessage(String appId, long timestamp, String nonceStr, String prepay_id) {
        return appId + "\n"
                + timestamp + "\n"
                + nonceStr + "\n"
                + prepay_id + "\n";
    }
}
