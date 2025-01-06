package cn.com.betacat.parkerpal.apicontracts.service.mixin;

import cn.com.betacat.parkerpal.domain.entity.SystemCameraDevice;
import java.util.Map;

public interface EnterParkingService {

    void createRecordCarEnter(String license, SystemCameraDevice cameraDevice);

    void createRecordCarEnterByPhoneNumber(String phoneNumber, SystemCameraDevice cameraDevice);

    Map<String, Object> setCarGroup(String licensePlate);

}
