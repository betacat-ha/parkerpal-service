package cn.com.betacat.parkerpal.apicontracts.converter;

import cn.com.betacat.parkerpal.apicontracts.dto.req.RecordsCarOutboundReq;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.RecordsCarOutboundResp;
import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import cn.com.betacat.parkerpal.domain.entity.RecordsCarOutbound;
import cn.com.betacat.parkerpal.domain.query.RecordsCarOutboundQuery;
import java.util.List;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 记录-车辆出场记录表(RecordsCarOutbound) 转换工具类
 *
 * @author
 * @since 2024-08-14 13:50:45
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface RecordsCarOutboundConverter {

    RecordsCarOutboundConverter INSTANCE = Mappers.getMapper(RecordsCarOutboundConverter.class);

    /**
     * 记录-车辆出场记录表 查询条件入参转换成 RecordsCarOutboundQuery 对象
     *
     * @param queryDTO
     * @return
     */
    RecordsCarOutboundQuery toQuery(RecordsCarOutboundReq.QueryDTO queryDTO);

    /**
     * 记录-车辆出场记录表 对象响应数据转换
     *
     * @param entity
     * @return
     */
    RecordsCarOutboundResp.RecordsCarOutboundDTO toDTO(RecordsCarOutbound entity);

    /**
     * 记录-车辆出场记录表 列表响应数据转换
     *
     * @param list
     * @return
     */
    List<RecordsCarOutboundResp.RecordsCarOutboundDTO> toListDTO(List<RecordsCarOutbound> list);

    /**
     * 分页查询 记录-车辆出场记录表 列表响应数据转换
     *
     * @param query
     * @return
     */
    RecordsCarOutboundResp.ListDTO toPage(PageInfoRespQuery query);
}
