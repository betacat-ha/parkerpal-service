package cn.com.betacat.parkerpal.core.endpoint.converter;

import cn.com.betacat.parkerpal.common.domain.MerchantReconciliation;
import cn.com.betacat.parkerpal.common.domain.query.MerchantReconciliationQuery;
import cn.com.betacat.parkerpal.core.endpoint.request.MerchantReconciliationReq;
import cn.com.betacat.parkerpal.core.endpoint.response.MerchantReconciliationResp;
import cn.com.betacat.parkerpal.common.pageutil.PageInfoRespQuery;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 商户-对账记录(MerchantReconciliation) 转换工具类
 *
 * @author
 * @since 2024-08-20 13:13:44
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface MerchantReconciliationConverter {

    MerchantReconciliationConverter INSTANCE = Mappers.getMapper(MerchantReconciliationConverter.class);

    /**
     * 商户-对账记录 查询条件入参转换成 MerchantReconciliationQuery 对象
     *
     * @param queryDTO
     * @return
     */
    MerchantReconciliationQuery toQuery(MerchantReconciliationReq.QueryDTO queryDTO);

    /**
     * 商户-对账记录 对象响应数据转换
     *
     * @param entity
     * @return
     */
    MerchantReconciliationResp.MerchantReconciliationDTO toDTO(MerchantReconciliation entity);

    /**
     * 商户-对账记录 列表响应数据转换
     *
     * @param list
     * @return
     */
    List<MerchantReconciliationResp.MerchantReconciliationDTO> toListDTO(List<MerchantReconciliation> list);

    /**
     * 分页查询 商户-对账记录 列表响应数据转换
     *
     * @param query
     * @return
     */
    MerchantReconciliationResp.ListDTO toPage(PageInfoRespQuery query);
}
