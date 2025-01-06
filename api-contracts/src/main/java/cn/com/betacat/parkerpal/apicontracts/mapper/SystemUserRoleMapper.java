package cn.com.betacat.parkerpal.apicontracts.mapper;

import cn.com.betacat.parkerpal.domain.entity.SystemUserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 系统管理-用户和角色关联表(SystemUserRole)表数据库访问层
 *
 * @author 
 * @since 2024-08-14 09:20:50
 */
@Repository
public interface SystemUserRoleMapper extends BaseMapper<SystemUserRole> {
}
