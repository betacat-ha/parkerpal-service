package cn.com.betacat.parkerpal.apicontracts.converter;

import cn.com.betacat.parkerpal.apicontracts.dto.req.SystemParkingDetailReq;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.SystemParkingDetailResp;
import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import cn.com.betacat.parkerpal.domain.entity.SystemParkingDetail;
import cn.com.betacat.parkerpal.domain.query.SystemParkingDetailQuery;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 系统管理-车位数量明细表(SystemParkingDetail) 转换工具类
 *
 * @author 
 * @since 2024-08-15 16:24:56
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SystemParkingDetailConverter {

    SystemParkingDetailConverter INSTANCE = Mappers.getMapper(SystemParkingDetailConverter.class);

    /**
     * 系统管理-车位数量明细表 数据新增或更新入参转换成 SystemParkingDetail 对象
     *
     * @param dto
     * @return
     */
    default SystemParkingDetail toEntity(SystemParkingDetailReq.CreateOrUpdateDTO dto) {
        SystemParkingDetail spVisitorVans = new SystemParkingDetail();
        // 定义日期时间的格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        spVisitorVans.setStartTime(LocalDateTime.parse(dto.getStartTime(), formatter));
        spVisitorVans.setEndTime(LocalDateTime.parse(dto.getEndTime(), formatter));
        spVisitorVans.setUserId(dto.getUserId());
        spVisitorVans.setAssignedNumber(dto.getAssignedNumber());
        if (StringUtils.isNotBlank(dto.getId())) spVisitorVans.setId(dto.getId());
        return spVisitorVans;
    }

    /**
     * 系统管理-车位数量明细表 查询条件入参转换成 SystemParkingDetailQuery 对象
     *
     * @param queryDTO
     * @return
     */
    SystemParkingDetailQuery toQuery(SystemParkingDetailReq.QueryDTO queryDTO);

    /**
     * 系统管理-车位数量明细表 对象响应数据转换
     *
     * @param entity
     * @return
     */
    SystemParkingDetailResp.SystemParkingDetailDTO toDTO(SystemParkingDetail entity);

    /**
     * 系统管理-车位数量明细表 列表响应数据转换
     *
     * @param list
     * @return
     */
    List<SystemParkingDetailResp.SystemParkingDetailDTO> toListDTO(List<SystemParkingDetail> list);

    /**
     * 分页查询 系统管理-车位数量明细表 列表响应数据转换
     *
     * @param query
     * @return
     */
    SystemParkingDetailResp.ListDTO toPage(PageInfoRespQuery query);
}
