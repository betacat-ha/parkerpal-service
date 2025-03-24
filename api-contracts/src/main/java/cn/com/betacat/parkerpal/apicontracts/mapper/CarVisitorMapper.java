package cn.com.betacat.parkerpal.apicontracts.mapper;

import cn.com.betacat.parkerpal.domain.entity.CarVisitor;
import cn.com.betacat.parkerpal.domain.query.CarVisitorQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 车辆管理-访客车登记管理表(CarVisitor)表数据库访问层
 *
 * @author 
 * @since 2024-08-14 13:03:03
 */
@Repository
public interface CarVisitorMapper extends BaseMapper<CarVisitor> {

    /**
     * 统计 车辆管理-访客车登记管理表 总数
     *
     * @param query
     * @return
     */
    Long countTotal(@Param("query") CarVisitorQuery query);

    /**
     * 分页查询 车辆管理-访客车登记管理表 列表
     *
     * @param query
     * @return
     */
    List<CarVisitor> getPageList(@Param("query") CarVisitorQuery query);

    /**
     * 根据内地车牌号码查询数据是否存在
     *
     * @param mainlandLicensePlates
     * @return
     */
    CarVisitor getByLicensePlates(@Param("mainlandLicensePlates") String mainlandLicensePlates);

    /**
     * 根据内地车牌号码查询是否免费
     *
     * @param mainlandLicensePlates
     * @return
     */
    CarVisitor getIsFreeByLicensePlates(@Param("mainlandLicensePlates") String mainlandLicensePlates);
}
