package cn.com.betacat.parkerpal.apicontracts.converter;

import cn.com.betacat.parkerpal.apicontracts.dto.req.WxPayReq;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.WxPayResp;
import cn.com.betacat.parkerpal.domain.entity.WxPay;
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

    WxPay toREq(WxPayReq.QueryDTO dto);

    WxPayResp.RespDTO toDto(WxPay wxPay);
}
