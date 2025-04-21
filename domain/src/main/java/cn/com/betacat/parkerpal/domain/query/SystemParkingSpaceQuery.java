package cn.com.betacat.parkerpal.domain.query;

import cn.com.betacat.parkerpal.domain.base.PageInfoQuery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统管理-车位(SystemParkingSpace)查询类
 *
 * @author
 * @since 2024-08-20 13:50:20
 */
@Getter
@Setter
@NoArgsConstructor
public class SystemParkingSpaceQuery extends PageInfoQuery implements Serializable {
    private static final long serialVersionUID = -17165114019670305L;
    // @formatter:off
    private String id;              // ID
    private String deviceId;        // 所属的设备ID，0为未设置
    private String name;            // 车位名称
    private String type;            // 车位类型
    private Integer parentId;       // 车位父ID
    private Boolean isReserved;     // 是否预留车位，0|否；1|是
    private Integer status;         // 车位状态，0|空闲；1|已分配；2|已占用
    private Date createTime;        // 创建时间
    private Date updateTime;        // 更新时间
    private String sensorSlot;      // 传感器插槽
    private Integer fnum;           // 楼层号码
    private String comment;         // 备注
    private String modelName;       // 模型号
    // @formatter:on
}
