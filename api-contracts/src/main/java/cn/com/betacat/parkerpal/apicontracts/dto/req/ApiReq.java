package cn.com.betacat.parkerpal.apicontracts.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author: 
 * @Since: 2024-08-29 16:14:41
 * @Description:
 */
public class ApiReq implements Serializable {
    private static final long serialVersionUID = 701842395678353080L;

    @Getter
    @Setter
    @ApiModel(value = "ApiReq.QueryDTO", description = "商户-停车领劵新增DTO")
    public static class QueryDTO implements Serializable {
        private static final long serialVersionUID = -66837852169218985L;
        //@formatter:off
        @ApiModelProperty(value = "商户ID", position = 1, required = true)
        private String userId;
        @ApiModelProperty(value = "时间戳，用于验证二维码的有效时间", position = 2)
        private Long timestamp;
        //@formatter:on
    }

    @Getter
    @Setter
    @ApiModel(value = "ApiReq.CreateDTO", description = "商户-停车领劵新增DTO")
    public static class CreateDTO implements Serializable {
        private static final long serialVersionUID = -66837852169218985L;
        //@formatter:off
        @ApiModelProperty(value = "商户ID", position = 1, required = true)
        private String userId;
        @ApiModelProperty(value = "车牌号/手机号码(无牌车)", position = 2, required = true)
        private String plates;
        @ApiModelProperty(value = "时间戳，用于验证二维码的有效时间", position = 3)
        private Long timestamp;
        //@formatter:on
    }

    @Getter
    @Setter
    @ApiModel(value = "ApiReq.InnerDoorMap", description = "获取室内地图")
    public static class InnerDoorMap implements Serializable {
        private static final long serialVersionUID = -66837852169218985L;
        //@formatter:off
        @ApiModelProperty(value = "车场ID", position = 1, required = true)
        private String poi;
        @ApiModelProperty(value = "楼层号", position = 2)
        private String fnum;
        //@formatter:on
    }
}
