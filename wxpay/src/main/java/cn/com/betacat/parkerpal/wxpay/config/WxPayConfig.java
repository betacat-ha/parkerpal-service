package cn.com.betacat.parkerpal.wxpay.config;

import cn.com.betacat.parkerpal.common.domain.SystemWeChatJsapiPay;
import cn.com.betacat.parkerpal.common.exception.BizException;
import cn.com.betacat.parkerpal.common.utils.UploadUtils;
import cn.com.betacat.parkerpal.wxpay.service.WeChatJsapiPayService;
import com.wechat.pay.contrib.apache.httpclient.WechatPayHttpClientBuilder;
import com.wechat.pay.contrib.apache.httpclient.auth.PrivateKeySigner;
import com.wechat.pay.contrib.apache.httpclient.auth.ScheduledUpdateCertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Credentials;
import com.wechat.pay.contrib.apache.httpclient.auth.WechatPay2Validator;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.util.Objects;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

/**
 * @Author: Zoey
 * @Since: 2024-09-05 13:19:17
 * @Description:
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "wxpay.enabled", havingValue = "true", matchIfMissing = false)
@ComponentScan(basePackages = "cn.com.betacat.parkerpal.wxpay")
public class WxPayConfig {


    @Value("${file.thirdParty}")
    private Boolean thirdParty;

    @Resource
    private WeChatJsapiPayService weChatJsapiPayService;

    /**
     * 获取商户的私钥文件
     *
     * @return
     */
    private PrivateKey getPrivateKey(String privateKeyPath) {
        try {
            if (thirdParty) {
                //从第三方服务器中读取私钥
                return PemUtil.loadPrivateKey(UploadUtils.readPrivateKeyFromServer(privateKeyPath));
            } else {
                FileSystemResource resource = new FileSystemResource(privateKeyPath);
                InputStream fis = resource.getInputStream();
                return PemUtil.loadPrivateKey(fis);
            }
        } catch (Exception e) {
            throw new RuntimeException("私钥文件不存在", e);
        }
    }

    /**
     * 获取签名验证器
     *
     * @return
     */
    @Bean(name = "getVerifier")
    public ScheduledUpdateCertificatesVerifier getVerifier() {
        log.info("获取签名验证器");
        SystemWeChatJsapiPay entity = weChatJsapiPayService.getWeChatJsapiPay();
        if (Objects.isNull(entity)) throw new BizException("微信JSAPI支付配置信息不存在");
        //获取商户私钥
        PrivateKey privateKey = getPrivateKey(entity.getPrivateKeyPath());
        //私钥签名对象
        PrivateKeySigner privateKeySigner = new PrivateKeySigner(entity.getMacSerialNo(), privateKey);
        //身份认证对象
        WechatPay2Credentials wechatPay2Credentials = new WechatPay2Credentials(entity.getMacId(), privateKeySigner);
        // 使用定时更新的签名验证器，不需要传入证书
        return new ScheduledUpdateCertificatesVerifier(
                wechatPay2Credentials, entity.getApiV3Key().getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 获取http请求对象
     *
     * @param verifier
     * @return
     */
    @Bean(name = "wxPayClient")
    public CloseableHttpClient getWxPayClient(ScheduledUpdateCertificatesVerifier verifier) {
        log.info("获取http请求对象");
        SystemWeChatJsapiPay entity = weChatJsapiPayService.getWeChatJsapiPay();
        if (Objects.isNull(entity)) throw new BizException("微信JSAPI支付配置信息不存在");
        //获取商户私钥
        PrivateKey privateKey = getPrivateKey(entity.getPrivateKeyPath());
        WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                .withMerchant(entity.getMacId(), entity.getMacSerialNo(), privateKey)
                .withValidator(new WechatPay2Validator(verifier));
        // ... 接下来，可以通过builder设置各种参数，来配置HttpClient
        // 通过WechatPayHttpClientBuilder构造的HttpClient，会自动的处理签名和验签，并进行证书自动更新
        return builder.build();
    }

    /**
     * 获取HttpClient，无需进行应答签名验证，跳过验签的流程
     */
    @Bean(name = "wxPayNoSignClient")
    public CloseableHttpClient getWxPayNoSignClient() {
        log.info("获取HttpClient，无需进行应答签名验证，跳过验签的流程");
        SystemWeChatJsapiPay entity = weChatJsapiPayService.getWeChatJsapiPay();
        if (Objects.isNull(entity)) throw new BizException("微信JSAPI支付配置信息不存在");
        //获取商户私钥
        PrivateKey privateKey = getPrivateKey(entity.getPrivateKeyPath());
        //用于构造HttpClient
        WechatPayHttpClientBuilder builder = WechatPayHttpClientBuilder.create()
                //设置商户信息
                .withMerchant(entity.getMacId(), entity.getMacSerialNo(), privateKey)
                //无需进行签名验证、通过withValidator((response) -> true)实现
                .withValidator((response) -> true);
        // 通过WechatPayHttpClientBuilder构造的HttpClient，会自动的处理签名和验签，并进行证书自动更新
        return builder.build();
    }
}
