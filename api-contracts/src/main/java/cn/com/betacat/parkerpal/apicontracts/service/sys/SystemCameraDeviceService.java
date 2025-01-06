package cn.com.betacat.parkerpal.apicontracts.service.sys;

import cn.com.betacat.parkerpal.domain.entity.SystemCameraDevice;
import cn.com.betacat.parkerpal.domain.query.SystemCameraDeviceQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * 系统管理-摄像头设备管理表(SystemCameraDevice)表服务接口
 *
 * @author
 * @since 2024-08-14 10:25:42
 */
public interface SystemCameraDeviceService extends IService<SystemCameraDevice> {

    List<SystemCameraDevice> getList(SystemCameraDeviceQuery query);

    void createOrUpdate(SystemCameraDevice entity);

    SystemCameraDevice getEntity(String deviceIp, String devicePort);
}
