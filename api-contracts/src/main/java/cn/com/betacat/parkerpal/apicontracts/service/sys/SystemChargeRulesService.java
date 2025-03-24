package cn.com.betacat.parkerpal.apicontracts.service.sys;

import cn.com.betacat.parkerpal.domain.entity.SystemChargeRules;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 系统管理-收费规则表(SystemChargeRules)表服务接口
 *
 * @author
 * @since 2024-08-15 14:32:03
 */
public interface SystemChargeRulesService extends IService<SystemChargeRules> {

    SystemChargeRules getEntity();

    SystemChargeRules updateEntity(SystemChargeRules entity);
}
