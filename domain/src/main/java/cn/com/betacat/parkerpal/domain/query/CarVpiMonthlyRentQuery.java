package cn.com.betacat.parkerpal.domain.query;

import cn.com.betacat.parkerpal.domain.base.PageInfoQuery;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 车辆管理-vip月租车登记管理表(CarVpiMonthlyRent)查询类
 *
 * @author 
 * @since 2024-08-14 13:03:03
 */
@Getter
@Setter
@NoArgsConstructor
public class CarVpiMonthlyRentQuery extends PageInfoQuery implements Serializable {
    private static final long serialVersionUID = 564208683115068580L;
    //@formatter:off
    private String id;                    //ID
    private String mainlandLicensePlates; //内地车牌号码
    private String otherLicensePlates;    //其他车牌号码，多个车牌号码英文逗号分隔
    private LocalDateTime createTime;     //登记时间
    private LocalDateTime updateTime;     //修改时间

    private Integer sort;                 //排序方式，1｜根据创建时间排序；2｜根据更新时间排序, 默认根据创建时间排序
    private Integer sortType;             //排序类型，1｜升序；2｜倒序, 默认倒序
    private String startTime;             //创建时间段开始时间,时间格式: yyyy-MM-dd HH:mm:ss
    private String endTime;               //创建时间段结束时间,时间格式: yyyy-MM-dd HH:mm:ss
    //@formatter:on
}
