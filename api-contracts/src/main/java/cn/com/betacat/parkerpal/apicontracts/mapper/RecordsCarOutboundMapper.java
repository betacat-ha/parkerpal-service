package cn.com.betacat.parkerpal.apicontracts.mapper;

import cn.com.betacat.parkerpal.domain.entity.RecordsCarOutbound;
import cn.com.betacat.parkerpal.domain.query.RecordsCarOutboundQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 记录-车辆出场记录表(RecordsCarOutbound)表数据库访问层
 *
 * @author
 * @since 2024-08-14 13:50:45
 */
@Repository
public interface RecordsCarOutboundMapper extends BaseMapper<RecordsCarOutbound> {

    /**
     * 统计 记录-车辆出场记录表 总数
     *
     * @param query
     * @return
     */
    Long countTotal(@Param("query") RecordsCarOutboundQuery query);

    /**
     * 分页查询 记录-车辆出场记录表 列表
     *
     * @param query
     * @return
     */
    List<RecordsCarOutbound> getPageList(@Param("query") RecordsCarOutboundQuery query);
}
