package cn.com.betacat.parkerpal.core.service;

import cn.com.betacat.parkerpal.apicontracts.mapper.SystemParkingSpaceMapper;
import cn.com.betacat.parkerpal.apicontracts.service.SystemParkingSpaceService;
import cn.com.betacat.parkerpal.domain.entity.SystemParkingSpace;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemParkingSpaceServiceImpl extends ServiceImpl<SystemParkingSpaceMapper, SystemParkingSpace> implements SystemParkingSpaceService {
    public List<SystemParkingSpace> selectAll(SystemParkingSpace query) {
        return this.baseMapper.selectAll(query);
    }
}
