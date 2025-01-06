package cn.com.betacat.parkerpal.apicontracts.converter;

import cn.com.betacat.parkerpal.apicontracts.dto.req.SystemRoleReq;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.SystemRoleResp;
import cn.com.betacat.parkerpal.domain.entity.SystemRole;
import cn.com.betacat.parkerpal.domain.query.SystemRoleQuery;
import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import java.util.List;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 系统管理-角色表(SystemRole) 转换工具类
 *
 * @author 
 * @since 2024-08-14 09:23:50
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface SystemRoleConverter {

    SystemRoleConverter INSTANCE = Mappers.getMapper(SystemRoleConverter.class);

    /**
     * 系统管理-角色表 数据新增或修改入参转换成 SystemRole 对象
     *
     * @param dto
     * @return
     */
    SystemRole toEntity(SystemRoleReq.CreateOrUpdateDTO dto);

    /**
     * 系统管理-角色表 查询条件入参转换成 SystemRoleQuery 对象
     *
     * @param queryDTO
     * @return
     */
    SystemRoleQuery toQuery(SystemRoleReq.QueryDTO queryDTO);

    /**
     * 系统管理-角色表 对象响应数据转换
     *
     * @param entity
     * @return
     */
    SystemRoleResp.SystemRoleDTO toDTO(SystemRole entity);

    /**
     * 系统管理-角色表 列表响应数据转换
     *
     * @param list
     * @return
     */
    List<SystemRoleResp.SystemRoleDTO> toListDTO(List<SystemRole> list);

    /**
     * 分页查询 系统管理-角色表 列表响应数据转换
     *
     * @param query
     * @return
     */
    SystemRoleResp.ListDTO toPage(PageInfoRespQuery query);
}
