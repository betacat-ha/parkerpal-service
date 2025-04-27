package cn.com.betacat.parkerpal.apigateway.http;

import cn.com.betacat.parkerpal.apicontracts.converter.MonthlyInsurancePaymentConverter;
import cn.com.betacat.parkerpal.apicontracts.dto.req.MonthlyInsurancePaymentReq;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.MonthlyInsurancePaymentResp;
import cn.com.betacat.parkerpal.apicontracts.service.MonthlyInsurancePaymentService;
import cn.com.betacat.parkerpal.common.annotation.PassToken;
import cn.com.betacat.parkerpal.common.utils.AuthorityType;
import cn.com.betacat.parkerpal.common.utils.DateTimeUtil;
import cn.com.betacat.parkerpal.common.utils.MonthlyFeeUtil;
import cn.com.betacat.parkerpal.core.exception.BizException;
import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import cn.com.betacat.parkerpal.domain.base.ResResult;
import cn.com.betacat.parkerpal.domain.entity.MonthlyInsurancePayment;
import cn.com.betacat.parkerpal.domain.enums.RespEnum;
import cn.com.betacat.parkerpal.domain.query.MonthlyInsurancePaymentQuery;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  
 * @since 2024-08-16 14:32:11
 */

@Api(tags = "月保")
@RestController
@RequestMapping("monthlyInsurance")
public class MonthlyEndpoint {

    @Autowired
    private MonthlyInsurancePaymentService monthlyInsurancePaymentService;

    @PassToken(required = false)
    @ApiOperation(value = "缴费记录-分页查询列表")
    @PostMapping(value = "/pageList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<MonthlyInsurancePaymentResp.ListDTO> pageList(@RequestBody MonthlyInsurancePaymentReq.QueryDTO dto) {
        // 请求数据转换
        MonthlyInsurancePaymentQuery query = MonthlyInsurancePaymentConverter.INSTANCE.toQuery(dto);
        // 调用服务
        PageInfoRespQuery resp = monthlyInsurancePaymentService.getPageList(query);
        // 响应数据转换
        return ResResult.success(MonthlyInsurancePaymentConverter.INSTANCE.toPage(resp));
    }

    @ApiOperation(value = "缴费记录-新增或更新")
    @PassToken(required = false, authority = AuthorityType.CREATE)
    @PostMapping(value = "/createOrUpdate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> createOrUpdate(@RequestBody MonthlyInsurancePaymentReq.CreateOrUpdateDTO dto) {
        // 请求数据转换
        MonthlyInsurancePayment entity = MonthlyInsurancePaymentConverter.INSTANCE.toEntity(dto);
        // 调用服务
        monthlyInsurancePaymentService.createOrUpdate(entity);
        // 响应数据转换
        return ResResult.success();
    }

    @ApiOperation(value = "缴费记录-删除")
    @PassToken(required = false, authority = AuthorityType.DELETE)
    @PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> delete(@RequestBody MonthlyInsurancePaymentReq.DeleteDTO dto) {
        // 调用服务
        monthlyInsurancePaymentService.removeBatchByIds(dto.getIds());
        // 响应数据转换
        return ResResult.success();
    }

    @PassToken(required = false)
    @ApiOperation(value = "缴费记录-缴费金额计算")
    @PostMapping(value = "/countMoney", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> countMoney(@RequestBody MonthlyInsurancePaymentReq.CountMoneyDTO dto) {
        // 判断是否长期有效
       if (null != dto.getLongTerm() && 1 == dto.getLongTerm()) {
           return ResResult.success(MonthlyFeeUtil.countLongTerm(dto.getMonthlyFree()));
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
           return ResResult.success(MonthlyFeeUtil.countFree(
                   dto.getCarTypeCode(),
                   dto.getMonthlyFree(),
                   DateTimeUtil.timeUtils(dto.getMonthlyStartTime(), 1),
                   DateTimeUtil.timeUtils(dto.getMonthlyEndTime(), 2)
           ));
       }
    }
}
