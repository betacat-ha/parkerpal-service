package cn.com.betacat.parkerpal.core.service;

import cn.com.betacat.parkerpal.apicontracts.mapper.SystemChargeRulesMapper;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemChargeRulesService;
import cn.com.betacat.parkerpal.domain.entity.SystemChargeRules;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * 系统管理-收费规则表(SystemChargeRules)表服务实现类
 *
 * @author
 * @since 2024-08-15 14:32:03
 */
@Service
public class SystemChargeRulesServiceImpl extends ServiceImpl<SystemChargeRulesMapper, SystemChargeRules> implements
    SystemChargeRulesService {

    /**
     * 查询对象
     *
     * @return
     */
    @Override
    public SystemChargeRules getEntity() {
        return this.baseMapper.getEntity();
    }

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    @Override
    public SystemChargeRules updateEntity(SystemChargeRules entity) {
        // 查询数据是否已经存在
        SystemChargeRules oldEntity = this.baseMapper.getEntity();
        if (Objects.nonNull(oldEntity)) {
            //月保收费标准-所属企业公车，单位：元/月
            oldEntity.setMonthlyEnterpriseCar(entity.getMonthlyEnterpriseCar());
            // 临保收费上限。单位：元/24小时
            oldEntity.setMonthlyEnterpriseCar(entity.getFeeCap());
            //月保收费标准-外部车辆(机械车位)，单位：元/月
            oldEntity.setMonthlyExternalCarMachinery(entity.getMonthlyExternalCarMachinery());
            //月保收费标准-内部车辆，单位：元/月
            oldEntity.setMonthlyInternalCar(entity.getMonthlyInternalCar());
            //月保收费标准-外部车辆(非机械车位)，单位：元/月
            oldEntity.setMonthlyInternalCarNoMachinery(entity.getMonthlyInternalCarNoMachinery());
            //临保收费标准，单位：元/小时
            oldEntity.setTollStandard(entity.getTollStandard());
            //进场免费时长，单位：分钟
            oldEntity.setFreeDuration(entity.getFreeDuration());
            this.baseMapper.updateById(oldEntity);
        } else {
            this.baseMapper.insert(entity);
        }
        return this.baseMapper.selectById(entity.getId());
    }
}
