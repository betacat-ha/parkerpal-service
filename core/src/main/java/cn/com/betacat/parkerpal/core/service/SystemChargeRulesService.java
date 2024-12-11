package cn.com.betacat.parkerpal.core.service;

import com.baomidou.mybatisplus.extension.service.IService;
import cn.com.betacat.parkerpal.common.domain.SystemChargeRules;

/**
 * 系统管理-收费规则表(SystemChargeRules)表服务接口
 *
 * @author zoey
 * @since 2024-08-15 14:32:03
 */
public interface SystemChargeRulesService extends IService<SystemChargeRules> {

    SystemChargeRules getEntity();

    SystemChargeRules updateEntity(SystemChargeRules entity);
}
