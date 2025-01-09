package cn.com.betacat.parkerpal.apicontracts.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import cn.com.betacat.parkerpal.domain.entity.SystemParkingSpace;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface SystemParkingSpaceMapper extends BaseMapper<SystemParkingSpace> {
    SystemParkingSpace selectById(@Param("id")String id);

    List<SystemParkingSpace> selectByDeviceId(@Param("deviceId")String deviceId);

	int insertList(@Param("list")List<SystemParkingSpace> list);
}