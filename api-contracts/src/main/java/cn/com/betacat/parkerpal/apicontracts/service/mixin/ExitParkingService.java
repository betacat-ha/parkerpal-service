package cn.com.betacat.parkerpal.apicontracts.service.mixin;

import cn.com.betacat.parkerpal.domain.entity.SystemCameraDevice;

public interface ExitParkingService {

    void handleCarExit(SystemCameraDevice cameraDevice, String license);

}
