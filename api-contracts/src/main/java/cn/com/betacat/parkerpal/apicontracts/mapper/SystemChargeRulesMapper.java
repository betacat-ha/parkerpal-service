package cn.com.betacat.parkerpal.apicontracts.mapper;

import cn.com.betacat.parkerpal.domain.entity.SystemChargeRules;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * 系统管理-收费规则表(SystemChargeRules)表数据库访问层
 *
 * @author 
 * @since 2024-08-15 14:32:03
 */
@Repository
public interface SystemChargeRulesMapper extends BaseMapper<SystemChargeRules> {

    /**
     * 查询 系统管理-收费规则表 对象
     *
     * @return
     */
    SystemChargeRules getEntity();
}
