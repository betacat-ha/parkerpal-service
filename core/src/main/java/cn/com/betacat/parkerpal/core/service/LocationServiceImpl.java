package cn.com.betacat.parkerpal.core.service;

import cn.com.betacat.parkerpal.apicontracts.service.LocationService;
import cn.com.betacat.parkerpal.common.constants.RedisMessageConstant;
import cn.com.betacat.parkerpal.common.utils.RedisUtil;
import cn.com.betacat.parkerpal.domain.entity.RssiRecord;
import cn.com.betacat.parkerpal.domain.entity.SignalSource;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author BetaCat
 * @since 2025-05-12 12:10:40
 */
@Service
@Slf4j
public class LocationServiceImpl implements LocationService {
    /**
     * 根据MAC地址从缓存中获取信号源信息
     *
     * @param macAddress 源MAC地址
     * @return 信号源信息列表
     */
    @Override
    public SignalSource getRssiFromCache(String macAddress) {
        Object obj = RedisUtil.get(macAddress + RedisMessageConstant.IOT_DEVICE_WIFI_RSSI);
        if (obj == null) {
            return null;
        }
        return JSON.parseObject((String) obj, SignalSource.class);
    }

    /**
     * 存储源RSSI信息到缓存
     *
     * @param rssiList RSSI信息列表
     */
    @Override
    public void setRssiToCache(List<RssiRecord> rssiList) {
        for (RssiRecord rssiRecord : rssiList) {
            setRssiToCache(rssiRecord);
        }
    }

    /**
     * 存储源RSSI信息到缓存
     *
     * @param rssiRecord RSSI信息
     */
    @Override
    public void setRssiToCache(RssiRecord rssiRecord) {
        String sourceMacAddress = rssiRecord.getSourceMacAddress();
        Object obj = RedisUtil.get(sourceMacAddress + RedisMessageConstant.IOT_DEVICE_WIFI_RSSI);
        SignalSource signalSource = obj == null ? new SignalSource(sourceMacAddress) : JSON.parseObject((String) obj, SignalSource.class);
        List<RssiRecord> currentRssiList = signalSource.getRssiRecords();

        // 如果列表不存在，创建新的列表
        if (currentRssiList == null) {
            currentRssiList = List.of(rssiRecord);
        }

        // 检查记录是否存在
        RssiRecord currentRecord = currentRssiList.stream().filter(record -> record.getSensorMacAddress().equals(rssiRecord.getSensorMacAddress())).findFirst().orElse(null);

        // 减少冗余
        rssiRecord.setSourceMacAddress(null);

        // 设置记录
        rssiRecord.setSensorMacAddress(sourceMacAddress);

        // 如果记录不存在，添加新的记录
        if (currentRecord == null) {
            currentRssiList.add(rssiRecord);
        } else {
            // 如果记录存在，更新记录
            currentRecord.setRssi(rssiRecord.getRssi());
            currentRecord.setTimestamp(rssiRecord.getTimestamp());
        }

        // 更新列表
        signalSource.setRssiRecords(currentRssiList);

        // 更新缓存
        RedisUtil.set(sourceMacAddress + RedisMessageConstant.IOT_DEVICE_WIFI_RSSI, JSON.toJSONString(signalSource));
    }
}
