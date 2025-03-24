package cn.com.betacat.parkerpal.core.service;

import cn.com.betacat.parkerpal.apicontracts.mapper.SystemParkingMapper;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemParkingService;
import cn.com.betacat.parkerpal.domain.entity.SystemParking;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * 系统管理-车位数量表(SystemParking)表服务实现类
 *
 * @author 
 * @since 2024-08-15 16:24:52
 */
@Service
public class SystemParkingServiceImpl extends ServiceImpl<SystemParkingMapper, SystemParking> implements
    SystemParkingService {

    /**
     * 系统管理-车位数量表-查询对象
     *
     * @return
     */
    @Override
    public SystemParking getEntity() {
        return this.baseMapper.getEntity();
    }

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    @Override
    public SystemParking updateEntity(SystemParking entity) {
        // 查询数据
        SystemParking oldEntity = this.baseMapper.getEntity();
        if (Objects.nonNull(oldEntity)) {
            if (null != entity.getParkingNumber() && entity.getParkingNumber() > 0) {
                oldEntity.setParkingNumber(entity.getParkingNumber());
            }
            if (null != entity.getDisposableNumber() && entity.getDisposableNumber() > 0) {
                // 判断可分配车位数量是否大于车位总数量
                if (entity.getDisposableNumber() > oldEntity.getParkingNumber()) {
                    throw new RuntimeException("可分配车位数量不能大于车位总数量");
                } else {
                    oldEntity.setDisposableNumber(entity.getDisposableNumber());
                }
            }
            this.baseMapper.updateById(oldEntity);
            return oldEntity;
        } else {
            if (null != entity.getDisposableNumber() && entity.getDisposableNumber() > 0) {
                // 判断可分配车位数量是否大于车位总数量
                if (entity.getDisposableNumber() > entity.getParkingNumber())
                    throw new RuntimeException("可分配车位数量不能大于车位总数量");
            }
            this.save(entity);
            return entity;
        }
    }
}
