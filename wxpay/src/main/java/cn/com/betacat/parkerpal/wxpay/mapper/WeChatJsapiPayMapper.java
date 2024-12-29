package cn.com.betacat.parkerpal.wxpay.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.com.betacat.parkerpal.common.domain.SystemWeChatJsapiPay;
import org.springframework.stereotype.Repository;

/**
 * 系统管理-微信JSAPI支付配置(SystemWeChatJsapiPay)表数据库访问层
 *
 * @author
 * @since 2024-09-06 14:05:26
 */
@Repository
public interface WeChatJsapiPayMapper extends BaseMapper<SystemWeChatJsapiPay> {

}
