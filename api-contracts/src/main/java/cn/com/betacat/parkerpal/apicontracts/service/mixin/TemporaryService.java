package cn.com.betacat.parkerpal.apicontracts.service.mixin;

import cn.com.betacat.parkerpal.domain.entity.ParkCollectCoupons;
import java.time.LocalDateTime;

public interface TemporaryService {

    String temporaryFee(Boolean flg, LocalDateTime startTime, LocalDateTime endTime);

    String getParkingDuration(LocalDateTime startTime, LocalDateTime endTime);

    double parseDurationToHours(String durationStr);

    void computationalCost(ParkCollectCoupons parkCollectCoupons, Integer freeTime, String tollStandardStr);

}
