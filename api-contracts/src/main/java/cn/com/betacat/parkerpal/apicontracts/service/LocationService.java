package cn.com.betacat.parkerpal.apicontracts.service;

import cn.com.betacat.parkerpal.domain.entity.RssiRecord;
import cn.com.betacat.parkerpal.domain.entity.SignalSource;

import java.util.List;

/**
 * @author BetaCat
 * @since 2025-05-12 12:04:18
 */
public interface LocationService {
    /**
     * 根据MAC地址从缓存中获取信号源信息
     * @param macAddress 源MAC地址
     * @return 信号源信息列表
     */
    SignalSource getRssiFromCache(String macAddress);

    /**
     * 存储源RSSI信息到缓存
     * @param rssiList RSSI信息列表
     */
    void setRssiToCache(List<RssiRecord> rssiList);


    /**
     * 存储源RSSI信息到缓存
     * @param rssiRecord RSSI信息
     */
    void setRssiToCache(RssiRecord rssiRecord);
}
