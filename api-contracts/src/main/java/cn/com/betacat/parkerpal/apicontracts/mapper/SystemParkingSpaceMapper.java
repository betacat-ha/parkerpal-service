package cn.com.betacat.parkerpal.apicontracts.mapper;
import java.util.List;

import cn.com.betacat.parkerpal.domain.query.IotDeviceQuery;
import cn.com.betacat.parkerpal.domain.query.SystemParkingSpaceQuery;
import org.apache.ibatis.annotations.Param;
import cn.com.betacat.parkerpal.domain.entity.SystemParkingSpace;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

public interface SystemParkingSpaceMapper extends BaseMapper<SystemParkingSpace> {
    SystemParkingSpace selectById(@Param("id")String id);

    List<SystemParkingSpace> selectByDeviceId(@Param("deviceId")String deviceId);

	int insertList(@Param("list")List<SystemParkingSpace> list);

    List<SystemParkingSpace> selectAll(@Param("query")SystemParkingSpace query);

    /**
     * 统计车位总数
     *
     * @param query
     * @return
     */
    Long countTotal(@Param("query") SystemParkingSpaceQuery query);

    /**
     * 分页查询车位
     *
     */
    List<SystemParkingSpace> selectPage(@Param("query") SystemParkingSpaceQuery query);
}