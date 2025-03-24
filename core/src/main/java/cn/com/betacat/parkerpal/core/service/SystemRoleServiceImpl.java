package cn.com.betacat.parkerpal.core.service;

import cn.com.betacat.parkerpal.apicontracts.mapper.SystemRoleMapper;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemRoleService;
import cn.com.betacat.parkerpal.domain.entity.SystemRole;
import cn.com.betacat.parkerpal.domain.query.SystemRoleQuery;
import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import cn.com.betacat.parkerpal.domain.base.PageInfoUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 系统管理-角色表(SystemRole)表服务实现类
 *
 * @author
 * @since 2024-08-13 15:25:27
 */
@Service
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements
    SystemRoleService {

    /**
     * 系统管理-角色表-分页查询列表
     *
     * @param query
     * @return
     */
    @Override
    public PageInfoRespQuery getPageList(SystemRoleQuery query) {
        // 赋值页码
        PageInfoUtil.pageReq(query);
        // 统计总数
        Long total = this.baseMapper.countTotal(query);
        // 查询列表
        List<SystemRole> list = this.baseMapper.getPageList(query);
        // 返回分页数据
        return PageInfoUtil.pageResp(list, query, total);
    }
}
