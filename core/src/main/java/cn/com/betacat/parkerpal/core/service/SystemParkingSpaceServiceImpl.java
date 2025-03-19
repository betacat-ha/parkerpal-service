package cn.com.betacat.parkerpal.core.service;

import cn.com.betacat.parkerpal.apicontracts.mapper.SystemParkingSpaceMapper;
import cn.com.betacat.parkerpal.apicontracts.service.SystemParkingSpaceService;
import cn.com.betacat.parkerpal.domain.entity.SystemParkingSpace;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SystemParkingSpaceServiceImpl extends ServiceImpl<SystemParkingSpaceMapper, SystemParkingSpace> implements SystemParkingSpaceService {
    @Autowired
    private CacheManager cacheManager;

    /**
     * 根据查询条件查询所有相关的停车位
     * @param query 查询条件
     * @return 停车位列表
     */
    // @Cacheable(value = "SystemParkingSpaceQuery", key = "#query.toString()", unless = "#result == null or #result.isEmpty()")
    public List<SystemParkingSpace> selectAll(SystemParkingSpace query) {
        return this.baseMapper.selectAll(query);
    }

    /**
     * 根据车位ID查询所有相关的停车位
     * @param id 车位ID
     * @return 停车位
     */
    @Cacheable(value = "SystemParkingSpaceById", key = "#id", unless = "#result == null")
    public SystemParkingSpace selectBy(String id) {
        return this.baseMapper.selectById(id);
    }

    /**
     * 根据车位ID更新车位信息
     * @param parkingSpace 车位信息
     * @return 更新数量
     */
    @Transactional
    public int updateBy(SystemParkingSpace parkingSpace) {
        int count = this.baseMapper.updateById(parkingSpace);
        cacheManager.getCache("SystemParkingSpaceById").put(parkingSpace.getId(), parkingSpace);
        return count;
    }


}
