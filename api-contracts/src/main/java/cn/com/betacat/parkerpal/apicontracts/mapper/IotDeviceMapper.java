package cn.com.betacat.parkerpal.apicontracts.mapper;
import java.util.List;

import cn.com.betacat.parkerpal.domain.query.IotDeviceQuery;
import org.apache.ibatis.annotations.Param;

import cn.com.betacat.parkerpal.domain.entity.IotDevice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

public interface IotDeviceMapper extends BaseMapper<IotDevice> {
    /**
     * 根据设备ID查询设备信息
     *
     * @param id 设备ID
     * @return 设备信息
     */
    @Select("select * from iot_device where id = #{id}")
    IotDevice selectById(@Param("id") String id);

    /**
     * 根据设备MAC地址查询设备信息
     *
     * @param macAddress 设备MAC地址
     * @return 设备信息
     */
    @Select("select * from iot_device where mac_address = #{macAddress}")
    IotDevice selectByMacAddress(@Param("macAddress") String macAddress);

    /**
     * 根据设备MAC地址查询设备信息，包含停车位信息
     *
     * @param macAddress 设备MAC地址
     * @return 设备信息
     */
    IotDevice selectByMacAddressWithSpaces(@Param("macAddress") String macAddress);

    /**
     * 根据设备ID查询设备信息，包含停车位信息
     *
     * @param id 设备ID
     * @return 设备信息
     */
    IotDevice selectByIdWithSpaces(@Param("id")String id);

    List<IotDevice> getAllWithPage(@Param("query") IotDeviceQuery query);

    /**
     * 统计设备总数
     *
     * @param query
     * @return
     */
    Long countTotal(@Param("query") IotDeviceQuery query);


}