package cn.com.betacat.parkerpal.core.service;

import cn.com.betacat.parkerpal.apicontracts.mapper.BaseStationMapper;
import cn.com.betacat.parkerpal.apicontracts.mapper.IotDeviceMapper;
import cn.com.betacat.parkerpal.apicontracts.service.BaseStationService;
import cn.com.betacat.parkerpal.domain.entity.BaseStation;
import cn.com.betacat.parkerpal.domain.entity.IotDevice;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author BetaCat
 * @since 2025-04-2 16:55:15
 */
@Slf4j
@Service
public class BaseStationServiceImpl extends ServiceImpl<BaseStationMapper, BaseStation> implements BaseStationService {

    @Autowired
    private IotDeviceMapper iotDeviceMapper;

    /**
     * 查询所有基站信息，包含设备信息和车位信息
     *
     * @return 基站信息
     */
    @Override
    public List<BaseStation> selectAllWithDevicesAndSpaces() {
        List<BaseStation> baseStations = baseMapper.selectList(null);
        // 收集所有iotDeviceId
        List<String> deviceIds = baseStations.stream()
                .map(BaseStation::getIotDeviceId)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        // 批量查询IotDevice
        if (!deviceIds.isEmpty()) {
            List<IotDevice> iotDevices = iotDeviceMapper.selectBatchIds(deviceIds);
            Map<String, IotDevice> deviceMap = iotDevices.stream()
                    .collect(Collectors.toMap(IotDevice::getId, device -> device));

            // 填充到BaseStation中
            for (BaseStation baseStation : baseStations) {
                baseStation.setIotDevice(deviceMap.get(baseStation.getIotDeviceId()));
            }
        }

        // 返回结果
        return baseMapper.selectList(null);
    }

    /**
     * 查询所有基站信息
     *
     * @return 基站信息
     */
    @Override
    public List<BaseStation> selectAll() {
        return baseMapper.selectList(null);
    }

    /**
     * 根据基站ID查询基站信息
     *
     * @param id 基站ID
     * @return 基站信息
     */
    @Override
    public BaseStation selectById(String id) {
        return baseMapper.selectById(id);
    }

    /**
     * 根据基站ID查询基站信息，包含设备信息
     *
     * @param id 基站ID
     * @return 基站信息
     */
    @Override
    public BaseStation selectByIdWithDevices(String id) {
        BaseStation baseStation = baseMapper.selectById(id);
        if (baseStation != null && baseStation.getIotDeviceId() != null) {
            IotDevice iotDevice = iotDeviceMapper.selectById(baseStation.getIotDeviceId());
            baseStation.setIotDevice(iotDevice);
        }
        return baseStation;
    }

    /**
     * 根据基站ID列表删除基站信息
     *
     * @param ids 基站ID列表
     */
    @Override
    public void deleteById(List<String> ids) {
        if (ids == null || ids.isEmpty()) {
            log.warn("删除基站信息时，基站ID列表为空");
            return;
        }

        // 删除基站信息
        baseMapper.deleteBatchIds(ids);

        // 删除对应的设备信息
        List<IotDevice> devices = iotDeviceMapper.selectList(null);
        List<String> deviceIds = devices.stream()
                .map(IotDevice::getId)
                .filter(ids::contains)
                .collect(Collectors.toList());
        if (!deviceIds.isEmpty()) {
            iotDeviceMapper.deleteBatchIds(deviceIds);
        }
    }
}
