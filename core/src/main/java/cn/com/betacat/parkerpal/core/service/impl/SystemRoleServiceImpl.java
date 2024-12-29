package cn.com.betacat.parkerpal.core.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.com.betacat.parkerpal.common.domain.SystemRole;
import cn.com.betacat.parkerpal.common.domain.query.SystemRoleQuery;
import cn.com.betacat.parkerpal.common.mapper.SystemRoleMapper;
import cn.com.betacat.parkerpal.common.pageutil.PageInfoRespQuery;
import cn.com.betacat.parkerpal.common.pageutil.PageInfoUtil;
import cn.com.betacat.parkerpal.core.service.SystemRoleService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * 系统管理-角色表(SystemRole)表服务实现类
 *
 * @author
 * @since 2024-08-13 15:25:27
 */
@Service
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements SystemRoleService {

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
