package cn.com.betacat.parkerpal.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.betacat.parkerpal.common.domain.SystemUserRole;
import cn.com.betacat.parkerpal.common.mapper.SystemUserRoleMapper;
import cn.com.betacat.parkerpal.core.service.SystemUserRoleService;
import org.springframework.stereotype.Service;

/**
 * 系统管理-用户和角色关联表(SystemUserRole)表服务实现类
 *
 * @author zoey
 * @since 2024-08-14 09:20:53
 */
@Service
public class SystemUserRoleServiceImpl extends ServiceImpl<SystemUserRoleMapper, SystemUserRole> implements SystemUserRoleService {

}
