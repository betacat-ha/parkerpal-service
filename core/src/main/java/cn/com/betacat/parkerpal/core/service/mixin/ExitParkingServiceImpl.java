package cn.com.betacat.parkerpal.core.service.mixin;

import cn.com.betacat.parkerpal.apicontracts.service.OrderPaidCatOutboundService;
import cn.com.betacat.parkerpal.apicontracts.service.RecordCarEnterService;
import cn.com.betacat.parkerpal.apicontracts.service.RecordsCarOutboundService;
import cn.com.betacat.parkerpal.apicontracts.service.mixin.ExitParkingService;
import cn.com.betacat.parkerpal.apicontracts.service.mixin.OrderService;
import cn.com.betacat.parkerpal.apicontracts.service.mixin.TemporaryService;
import cn.com.betacat.parkerpal.core.exception.BizException;
import cn.com.betacat.parkerpal.domain.entity.OrderPaidCatOutbound;
import cn.com.betacat.parkerpal.domain.entity.RecordCarEnter;
import cn.com.betacat.parkerpal.domain.entity.RecordsCarOutbound;
import cn.com.betacat.parkerpal.domain.entity.SystemCameraDevice;
import java.time.LocalDateTime;
import java.util.Objects;

import cn.com.betacat.parkerpal.domain.enums.DeviceGroupEnum;
import cn.com.betacat.parkerpal.domain.enums.OrderStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ExitParkingServiceImpl implements ExitParkingService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RecordsCarOutboundService recordsCarOutboundService;

    @Autowired
    private RecordCarEnterService recordCarEnterService;

    @Autowired
    private TemporaryService temporaryService;

    @Autowired
    private OrderPaidCatOutboundService orderPaidCatOutboundService;

    /**
     * 车辆出场业务处理
     */
    @Override
    public void handleCarExit(SystemCameraDevice cameraDevice, String license) {
        // 查询车辆进场记录
        RecordCarEnter recordCarEnter = recordCarEnterService.getByDeviceIp(license, cameraDevice.getDeviceIp());
        if (recordCarEnter == null) {
            throw new BizException("车牌号为：【" + license + "】暂无进场记录。");
        }

        // 创建车辆出场记录
        RecordsCarOutbound outboundRecords = createRecordsCarOut(LocalDateTime.now(), recordCarEnter, cameraDevice);

        // 判断是否需要生成支付订单
        OrderPaidCatOutbound orderPaidCatOutbound = orderPaidCatOutboundService.getEntityByPlates(OrderStatus.SUCCESS.getType(), outboundRecords.getMainlandLicensePlates());

        // 判断是否需要生成支付订单
        if (outboundRecords.getIsToll() != null && outboundRecords.getIsToll() == 1 && orderPaidCatOutbound == null) {
            // 需要生成支付订单
            orderService.createOrder(outboundRecords);
            throw new BizException("请支付停车费用");
        }


        // 判断是否需要支付
        if (orderPaidCatOutbound != null && !Objects.equals(orderPaidCatOutbound.getPayStatus(), OrderStatus.SUCCESS.getType())) {
            // 订单未支付，提示用户支付
            throw new BizException("请支付停车费用");
        }

        // 不需要支付或已支付，则直接保存出场记录并放行
        recordsCarOutboundService.save(outboundRecords);
        recordCarEnterService.updateById(recordCarEnter);
        openControlGate(cameraDevice.getDeviceIp());
    }

    /**
     * 创建车辆出场记录
     */
    private RecordsCarOutbound createRecordsCarOut(LocalDateTime nowTime,
        RecordCarEnter recordCarEnter,
        SystemCameraDevice cameraDevice) {
        RecordsCarOutbound outboundRecords = new RecordsCarOutbound();
        outboundRecords.setStartTime(recordCarEnter.getStartTime());
        outboundRecords.setEndTime(nowTime);
        outboundRecords.setMainlandLicensePlates(recordCarEnter.getMainlandLicensePlates());
        outboundRecords.setEndCameraDeviceIp(cameraDevice.getDeviceIp());
        outboundRecords.setDeviceGroupId(cameraDevice.getGroupId());
        outboundRecords.setDeviceGroupName(Objects.requireNonNull(DeviceGroupEnum.getById(cameraDevice.getGroupId().toString())).getDeviceGroupName());
        outboundRecords.setIsToll(recordCarEnter.getIsToll());
        outboundRecords.setCarGroupId(recordCarEnter.getCarGroupId());
        outboundRecords.setCarGroupName(recordCarEnter.getCarGroupName());
        outboundRecords.setParkingDuration(
            temporaryService.getParkingDuration(
                outboundRecords.getStartTime(),
                outboundRecords.getEndTime()
            )
        );
        outboundRecords.setId(recordCarEnter.getId()); // 假设RecordCarEnter中有userId字段
        return outboundRecords;
    }

    /**
     * 模拟方法：用于打开控制闸门（实际应用中应替换为真实的数据访问逻辑）
     */
    private void openControlGate(String deviceIp) {
        log.info("Opening control gate for device IP: {}", deviceIp);
        // 实际的控制闸门打开逻辑应该在这里实现
    }
}