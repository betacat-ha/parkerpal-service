package cn.com.betacat.parkerpal.apicontracts.mapper;

import cn.com.betacat.parkerpal.domain.entity.BaseStation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author BetaCat
 * @description
 * @since 2025-04-27 16:43:56
 */
public interface BaseStationMapper extends BaseMapper<BaseStation> {
    ///**
    // * 根据基站ID查询基站信息
    // *
    // * @param id 基站ID
    // * @return 基站信息
    // */
    //@Select("select * from base_station where id = #{id}")
    //BaseStation selectById(String id);
}
