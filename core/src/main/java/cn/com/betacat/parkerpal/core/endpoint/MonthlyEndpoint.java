package cn.com.betacat.parkerpal.core.endpoint;

import cn.com.betacat.parkerpal.common.domain.MonthlyInsurancePayment;
import cn.com.betacat.parkerpal.common.domain.query.MonthlyInsurancePaymentQuery;
import cn.com.betacat.parkerpal.common.enums.RespEnum;
import cn.com.betacat.parkerpal.core.endpoint.converter.MonthlyInsurancePaymentConverter;
import cn.com.betacat.parkerpal.core.endpoint.request.MonthlyInsurancePaymentReq;
import cn.com.betacat.parkerpal.core.endpoint.response.MonthlyInsurancePaymentResp;
import cn.com.betacat.parkerpal.core.service.MonthlyInsurancePaymentService;
import cn.com.betacat.parkerpal.common.utils.DateTimeUtil;
import cn.com.betacat.parkerpal.common.utils.MonthlyUtil;
import cn.com.betacat.parkerpal.common.annotation.PassToken;
import cn.com.betacat.parkerpal.common.exception.BizException;
import cn.com.betacat.parkerpal.common.exception.RequestObject;
import cn.com.betacat.parkerpal.common.exception.ResponseObject;
import cn.com.betacat.parkerpal.common.pageutil.PageInfoRespQuery;
import cn.com.betacat.parkerpal.common.utils.AuthorityType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Zoey
 * @Since: 2024-08-16 14:32:11
 * @Description:
 */
@RestController
@Api(tags = "月保")
@RequestMapping("monthlyInsurance")
public class MonthlyEndpoint {

    @Autowired
    private MonthlyInsurancePaymentService monthlyInsurancePaymentService;

    @PassToken(required = false)
    @ApiOperation(value = "缴费记录-分页查询列表")
    @PostMapping(value = "/pageList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<MonthlyInsurancePaymentResp.ListDTO> pageList(@RequestBody RequestObject<MonthlyInsurancePaymentReq.QueryDTO> ro) {
        // 请求数据转换
        MonthlyInsurancePaymentQuery query = MonthlyInsurancePaymentConverter.INSTANCE.toQuery(ro.getBody());
        // 调用服务
        PageInfoRespQuery resp = monthlyInsurancePaymentService.getPageList(query);
        // 响应数据转换
        return ResponseObject.success(MonthlyInsurancePaymentConverter.INSTANCE.toPage(resp));
    }

    @ApiOperation(value = "缴费记录-新增或更新")
    @PassToken(required = false, authority = AuthorityType.CREATE)
    @PostMapping(value = "/createOrUpdate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<?> createOrUpdate(@RequestBody RequestObject<MonthlyInsurancePaymentReq.CreateOrUpdateDTO> ro) {
        // 请求数据转换
        MonthlyInsurancePayment entity = MonthlyInsurancePaymentConverter.INSTANCE.toEntity(ro.getBody());
        // 调用服务
        monthlyInsurancePaymentService.createOrUpdate(entity);
        // 响应数据转换
        return ResponseObject.success();
    }

    @ApiOperation(value = "缴费记录-删除")
    @PassToken(required = false, authority = AuthorityType.DELETE)
    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<?> delete(@RequestBody RequestObject<MonthlyInsurancePaymentReq.DeleteDTO> ro) {
        // 调用服务
        monthlyInsurancePaymentService.removeBatchByIds(ro.getBody().getIds());
        // 响应数据转换
        return ResponseObject.success();
    }

    @PassToken(required = false)
    @ApiOperation(value = "缴费记录-缴费金额计算")
    @PostMapping(value = "/countMoney", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<?> countMoney(@RequestBody RequestObject<MonthlyInsurancePaymentReq.CountMoneyDTO> ro) {
        // 获取计算金额的必要参数
        MonthlyInsurancePaymentReq.CountMoneyDTO dto = ro.getBody();
        // 判断是否长期有效
        if (null != dto.getLongTerm() && 1 == dto.getLongTerm()) {
            return ResponseObject.success(MonthlyUtil.countLongTerm(dto.getMonthlyFree()));
        } else {
            if (StringUtils.isBlank(dto.getMonthlyStartTime()))
                throw new BizException(RespEnum.FAILURE.getCode(), "开始时间不能为空");
            if (StringUtils.isBlank(dto.getMonthlyEndTime()))
                throw new BizException(RespEnum.FAILURE.getCode(), "结束时间不能为空");
            if (StringUtils.isBlank(dto.getCarTypeCode()))
                throw new BizException(RespEnum.FAILURE.getCode(), "车辆类型编码不能为空");
            if (StringUtils.isBlank(dto.getMonthlyFree()))
                throw new BizException(RespEnum.FAILURE.getCode(), "月保费用不能为空");
            // 响应数据转换
            return ResponseObject.success(MonthlyUtil.countFree(
                    dto.getCarTypeCode(),
                    dto.getMonthlyFree(),
                    DateTimeUtil.timeUtils(dto.getMonthlyStartTime(), 1),
                    DateTimeUtil.timeUtils(dto.getMonthlyEndTime(), 2)
            ));
        }
    }
}
