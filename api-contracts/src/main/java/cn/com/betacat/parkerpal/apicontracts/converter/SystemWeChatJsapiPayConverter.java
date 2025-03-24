package cn.com.betacat.parkerpal.apicontracts.converter;

import cn.com.betacat.parkerpal.apicontracts.dto.req.SystemWeChatJsapiPayReq;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.SystemWeChatJsapiPayResp;
import cn.com.betacat.parkerpal.domain.entity.SystemWeChatJsapiPay;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 系统管理-微信JSAPI支付配置(SystemWeChatJsapiPay) 转换工具类
 *
 * @author 
 * @since 2024-09-06 14:05:29
 */
@Mapper
public interface SystemWeChatJsapiPayConverter {

    SystemWeChatJsapiPayConverter INSTANCE = Mappers.getMapper(SystemWeChatJsapiPayConverter.class);
    
    /**
     * 系统管理-微信JSAPI支付配置 数据新增或更新入参转换成 SystemWeChatJsapiPay 对象
     *
     * @param dto
     * @return
     */
    SystemWeChatJsapiPay toEntity(SystemWeChatJsapiPayReq.CreateOrUpdateDTO dto);

    /**
     * 系统管理-微信JSAPI支付配置 对象响应数据转换
     *
     * @param entity
     * @return
     */
    SystemWeChatJsapiPayResp.SystemWeChatJsapiPayDTO toDTO(SystemWeChatJsapiPay entity);
}
