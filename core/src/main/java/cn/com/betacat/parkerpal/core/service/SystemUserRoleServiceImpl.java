package cn.com.betacat.parkerpal.core.service;

import cn.com.betacat.parkerpal.apicontracts.mapper.SystemUserRoleMapper;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemUserRoleService;
import cn.com.betacat.parkerpal.domain.entity.SystemUserRole;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 系统管理-用户和角色关联表(SystemUserRole)表服务实现类
 *
 * @author 
 * @since 2024-08-14 09:20:53
 */
@Service
public class SystemUserRoleServiceImpl extends ServiceImpl<SystemUserRoleMapper, SystemUserRole> implements
    SystemUserRoleService {

}
