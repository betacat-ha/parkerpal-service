package cn.com.betacat.parkerpal.iot.mapper;

import cn.com.betacat.parkerpal.iot.domain.IotDevice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface IotDeviceMapper extends BaseMapper<IotDevice> {
    /**
     * 根据设备ID查询设备信息
     *
     * @param deviceId 设备ID
     * @return 设备信息
     */
    @Select("select * from iot_device where device_id = #{deviceId}")
    IotDevice getByDeviceId(@Param("deviceId") String deviceId);

    /**
     * 根据设备MAC地址查询设备信息
     *
     * @param macAddress 设备MAC地址
     * @return 设备信息
     */
    @Select("select * from iot_device where mac_address = #{macAddress}")
    IotDevice getByMacAddress(@Param("macAddress") String macAddress);

    /**
     * 增加设备
     *
     * @param iotDevice 设备信息
     * @return 是否成功
     */
    @Select("")
    Boolean addDevice(IotDevice iotDevice);
}