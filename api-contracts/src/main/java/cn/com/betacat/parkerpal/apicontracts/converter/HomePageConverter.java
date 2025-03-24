package cn.com.betacat.parkerpal.apicontracts.converter;

import cn.com.betacat.parkerpal.apicontracts.dto.resp.HomePageResp;
import cn.com.betacat.parkerpal.domain.entity.MonthlyStatistics;
import cn.com.betacat.parkerpal.domain.entity.ParkingSpaceStatistics;
import cn.com.betacat.parkerpal.domain.entity.RevenueStatistics;
import java.util.List;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: 
 * @Since: 2024-08-29 09:42:15
 * @Description:
 */
@Mapper(builder = @Builder(disableBuilder = true))
public interface HomePageConverter {

    HomePageConverter INSTANCE = Mappers.getMapper(HomePageConverter.class);

    HomePageResp.CountOrderDTO toDto(MonthlyStatistics entity);

    List<HomePageResp.CountOrderDTO> toListDto(List<MonthlyStatistics> list);

    HomePageResp.RevenueStatisticsDTO toDto(RevenueStatistics entity);

    HomePageResp.ParkingSpaceStatisticsDTO toDto(ParkingSpaceStatistics entity);
}
