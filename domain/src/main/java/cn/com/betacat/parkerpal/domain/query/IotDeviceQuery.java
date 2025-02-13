package cn.com.betacat.parkerpal.domain.query;

import cn.com.betacat.parkerpal.domain.base.PageInfoQuery;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * IOT-设备(IotDevice)查询类
 *
 * @author
 * @since 2024-08-20 13:50:20
 */
@Getter
@Setter
@NoArgsConstructor
public class IotDeviceQuery extends PageInfoQuery implements Serializable {
    private static final long serialVersionUID = -17165114019670305L;
    // @formatter:off
    private String id;              // ID
    private String macAddress;      // MAC地址
    private String name;            // 设备名称
    private String location;        // 设备位置
    private Integer role;           // 设备角色
    private Date createTime;        // 创建时间
    private Date updateTime;        // 更新时间
    private Integer groupId;        // 设备组ID
    private Integer createUserId;   // 创建用户ID
    private Byte isDisabled;        // 是否禁用
    private Byte isDeleted;         // 是否删除
    // @formatter:on
}
