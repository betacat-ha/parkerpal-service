package cn.com.betacat.parkerpal.core.service.mixin;

import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemChargeRulesService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemUsersService;
import cn.com.betacat.parkerpal.apicontracts.service.mixin.ChargeRulesService;
import cn.com.betacat.parkerpal.common.constants.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ChargeRulesServiceImpl implements ChargeRulesService {

    @Autowired
    private SystemUsersService systemUsersService;

    @Autowired
    private SystemChargeRulesService systemChargeRulesService;

    /**
     * 获取免费停车时长：单位：分钟
     *
     * @return
     */
    @Override
    public Integer getFreeDuration() {
        // 默认免费停车 15 分钟
        Integer freeDuration = AppConstants.FREE_DURATION;
        if (systemChargeRulesService != null
                && systemChargeRulesService.getEntity() != null
                && systemChargeRulesService.getEntity().getFreeDuration() != null
        ) {
            freeDuration = systemChargeRulesService.getEntity().getFreeDuration();
        }
        return freeDuration;
    }

    /**
     * 获取临保收费标准，单位：元/小时
     *
     * @return
     */
    @Override
    public String getTollStandard() {
        // 默认 15元/小时
        String tollStandard = AppConstants.TOOL_STANDARD;
        if (systemChargeRulesService != null
                && systemChargeRulesService.getEntity() != null
                && systemChargeRulesService.getEntity().getTollStandard() != null
        ) {
            tollStandard = systemChargeRulesService.getEntity().getTollStandard();
        }
        return tollStandard;
    }

    /**
     * 获取临保收费上限。单位：元/24小时
     *
     * @return
     */
    @Override
    public String getFeeCap() {
        // 默认 99元/24小时
        String tollStandard = AppConstants.CAPPING_FEE;
        if (systemChargeRulesService != null
                && systemChargeRulesService.getEntity() != null
                && systemChargeRulesService.getEntity().getFeeCap() != null
        ) {
            tollStandard = systemChargeRulesService.getEntity().getFeeCap();
        }
        return tollStandard;
    }

    /**
     * 获取月保收费标准-内部车辆，单位：元/月
     *
     * @return
     */
    @Override
    public String getMonthlyInternalCar() {
        if (systemChargeRulesService != null) {
            return systemChargeRulesService.getEntity().getMonthlyInternalCar();
        }
        return null;
    }

    /**
     * 获取月保收费标准-所属企业公车，单位：元/月
     *
     * @return
     */
    @Override
    public String getMonthlyEnterpriseCar() {
        if (systemChargeRulesService != null) {
            return systemChargeRulesService.getEntity().getMonthlyEnterpriseCar();
        }
        return null;
    }

    /**
     * 获取月保收费标准-外部车辆(非机械车位)，单位：元/月
     *
     * @return
     */
    @Override
    public String getMonthlyInternalCarNoMachinery() {
        if (systemChargeRulesService != null) {
            return systemChargeRulesService.getEntity().getMonthlyInternalCarNoMachinery();
        }
        return null;
    }

    /**
     * 获取月保收费标准-外部车辆(机械车位)，单位：元/月
     *
     * @return
     */
    @Override
    public String getMonthlyExternalCarMachinery() {
        if (systemChargeRulesService != null) {
            return systemChargeRulesService.getEntity().getMonthlyExternalCarMachinery();
        }
        return null;
    }

    /**
     * 查询管理员用户ID
     *
     * @return
     */
    @Override
    public String getUserId() {
        return systemUsersService.getEntityId();
    }
}
