package cn.com.betacat.parkerpal.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.betacat.parkerpal.common.domain.SystemRole;
import cn.com.betacat.parkerpal.common.domain.query.SystemRoleQuery;
import cn.com.betacat.parkerpal.common.pageutil.PageInfoRespQuery;

/**
 * 系统管理-角色表(SystemRole)表服务接口
 *
 * @author 
 * @since 2024-08-13 15:25:27
 */
public interface SystemRoleService extends IService<SystemRole> {

    /**
     * 系统管理-角色表-分页查询列表
     *
     * @param query
     * @return
     */
    PageInfoRespQuery getPageList(SystemRoleQuery query);
}
