package cn.com.betacat.parkerpal.apicontracts.mapper;

import cn.com.betacat.parkerpal.domain.entity.SystemParkingDetail;
import cn.com.betacat.parkerpal.domain.query.SystemParkingDetailQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 系统管理-车位数量明细表(SystemParkingDetail)表数据库访问层
 *
 * @author 
 * @since 2024-08-15 16:24:56
 */
@Repository
public interface SystemParkingDetailMapper extends BaseMapper<SystemParkingDetail> {

    /**
     * 统计 系统管理-车位数量明细表 总数
     *
     * @param query
     * @return
     */
    Long countTotal(@Param("query") SystemParkingDetailQuery query);

    /**
     * 分页查询 系统管理-车位数量明细表 列表
     *
     * @param query
     * @return
     */
    List<SystemParkingDetail> getPageList(@Param("query") SystemParkingDetailQuery query);

    /**
     * 统计租赁车位总数
     *
     * @return
     */
    Integer sumAssignedNumber();

    /**
     * 根据用户id查询租赁车位
     *
     * @param userId
     * @return
     */
    SystemParkingDetail getEntityByUserId(@Param("userId") String userId);
}
