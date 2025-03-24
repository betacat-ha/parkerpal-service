package cn.com.betacat.parkerpal.apicontracts.mapper;

import cn.com.betacat.parkerpal.domain.entity.SystemRole;
import cn.com.betacat.parkerpal.domain.query.SystemRoleQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 系统管理-角色表(SystemRole)表数据库访问层
 *
 * @author
 * @since 2024-08-13 15:25:27
 */
@Repository
public interface SystemRoleMapper extends BaseMapper<SystemRole> {

    /**
     * 统计 系统管理-角色表 总数
     *
     * @param query
     * @return
     */
    Long countTotal(@Param("query") SystemRoleQuery query);
    
    /**
     * 分页查询 系统管理-角色表 列表
     *
     * @param query
     * @return
     */
    List<SystemRole> getPageList(@Param("query") SystemRoleQuery query);
}
