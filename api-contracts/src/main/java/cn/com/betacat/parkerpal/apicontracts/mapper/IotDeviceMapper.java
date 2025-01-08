package cn.com.betacat.parkerpal.apicontracts.mapper;

import cn.com.betacat.parkerpal.domain.entity.IotDevice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface IotDeviceMapper extends BaseMapper<IotDevice> {
    /**
     * 根据设备ID查询设备信息
     *
     * @param id 设备ID
     * @return 设备信息
     */
    @Select("select * from iot_device where id = #{id}")
    IotDevice getById(@Param("id") String id);

    /**
     * 根据设备MAC地址查询设备信息
     *
     * @param macAddress 设备MAC地址
     * @return 设备信息
     */
    @Select("select * from iot_device where mac_address = #{macAddress}")
    IotDevice getByMacAddress(@Param("macAddress") String macAddress);

    IotDevice getByMacAddressWithSpaces(@Param("macAddress") String macAddress);
}