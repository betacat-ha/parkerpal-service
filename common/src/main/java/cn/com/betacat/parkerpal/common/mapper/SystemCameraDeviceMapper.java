package cn.com.betacat.parkerpal.common.mapper;

import cn.com.betacat.parkerpal.common.domain.query.SystemCameraDeviceQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.com.betacat.parkerpal.common.domain.SystemCameraDevice;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统管理-摄像头设备管理表(SystemCameraDevice)表数据库访问层
 *
 * @author 
 * @since 2024-08-14 10:25:42
 */
@Repository
public interface SystemCameraDeviceMapper extends BaseMapper<SystemCameraDevice> {

    /**
     * 查询 系统管理-摄像头设备管理表 列表
     *
     * @param query
     * @return
     */
    List<SystemCameraDevice> getList(@Param("query") SystemCameraDeviceQuery query);

    /**
     * 查询 系统管理-摄像头设备管理表
     *
     * @param deviceIp
     * @param devicePort
     * @return
     */
    SystemCameraDevice getEntity(@Param("deviceIp") String deviceIp, @Param("devicePort") String devicePort);
}
