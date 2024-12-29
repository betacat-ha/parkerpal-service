package cn.com.betacat.parkerpal.wxpay.endpoint.converter;

import cn.com.betacat.parkerpal.wxpay.endpoint.response.WxPayResp;
import cn.com.betacat.parkerpal.wxpay.domain.WxChatPayDto;
import cn.com.betacat.parkerpal.wxpay.domain.WxPayReqParam;
import cn.com.betacat.parkerpal.wxpay.endpoint.request.WxPayReq;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author:
 * @Since: 2024-09-05 14:09:09
 * @Description:
 */
@Mapper
public interface VxPayConverter {

    VxPayConverter INSTANCE = Mappers.getMapper(VxPayConverter.class);

    WxPayReqParam toEntity(WxPayReq.QueryDTO dto);

    WxPayResp.RespDTO toDto(WxChatPayDto dto);
}
