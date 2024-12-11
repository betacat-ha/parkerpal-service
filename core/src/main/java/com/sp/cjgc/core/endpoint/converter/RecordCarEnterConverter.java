package com.sp.cjgc.core.endpoint.converter;

import com.sp.cjgc.common.domain.RecordCarEnter;
import com.sp.cjgc.common.domain.query.RecordCarEnterQuery;
import com.sp.cjgc.core.endpoint.request.RecordCarEnterReq;
import com.sp.cjgc.core.endpoint.response.RecordCarEnterResp;
import com.sp.cjgc.common.pageutil.PageInfoRespQuery;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 记录-车辆进场记录表(RecordCarEnter) 转换工具类
 *
 * @author zoey
 * @since 2024-08-14 13:50:45
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface RecordCarEnterConverter {

    RecordCarEnterConverter INSTANCE = Mappers.getMapper(RecordCarEnterConverter.class);

    /**
     * 记录-车辆进场记录表 查询条件入参转换成 RecordCarEnterQuery 对象
     *
     * @param queryDTO
     * @return
     */
    RecordCarEnterQuery toQuery(RecordCarEnterReq.QueryDTO queryDTO);

    /**
     * 记录-车辆进场记录表 对象响应数据转换
     *
     * @param entity
     * @return
     */
    RecordCarEnterResp.RecordCarEnterDTO toDTO(RecordCarEnter entity);

    /**
     * 记录-车辆进场记录表 列表响应数据转换
     *
     * @param list
     * @return
     */
    List<RecordCarEnterResp.RecordCarEnterDTO> toListDTO(List<RecordCarEnter> list);

    /**
     * 分页查询 记录-车辆进场记录表 列表响应数据转换
     *
     * @param query
     * @return
     */
    RecordCarEnterResp.ListDTO toPage(PageInfoRespQuery query);
}
