package cn.com.betacat.parkerpal.apigateway.http;

import cn.com.betacat.parkerpal.apicontracts.converter.MerchantReconciliationConverter;
import cn.com.betacat.parkerpal.apicontracts.converter.ParkCollectCouponsConverter;
import cn.com.betacat.parkerpal.apicontracts.dto.req.MerchantReconciliationReq;
import cn.com.betacat.parkerpal.apicontracts.dto.req.ParkCollectCouponsReq;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.MerchantReconciliationResp;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.ParkCollectCouponsResp;
import cn.com.betacat.parkerpal.apicontracts.service.MerchantReconciliationService;
import cn.com.betacat.parkerpal.apicontracts.service.ParkCollectCouponsService;
import cn.com.betacat.parkerpal.common.annotation.PassToken;
import cn.com.betacat.parkerpal.common.constants.AppConstants;
import cn.com.betacat.parkerpal.common.utils.AuthorityType;
import cn.com.betacat.parkerpal.common.utils.QRCodeUtil;
import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import cn.com.betacat.parkerpal.domain.base.ResResult;
import cn.com.betacat.parkerpal.domain.enums.RespEnum;
import cn.com.betacat.parkerpal.domain.query.MerchantReconciliationQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商户-对账记录(MerchantReconciliation)表控制层
 *
 * @author 
 * @since 2024-08-20 13:13:44
 */

@Api(tags = "商户")
@RestController
@RequestMapping("merchantReconciliation")
public class MerchantEndpoint {

    @Value("${file.qrCode}")
    private String qrCodeUrl;

    @Value("${file.thirdParty}")
    private Boolean thirdParty;

    /**
     * 服务对象
     */
    @Autowired
    private ParkCollectCouponsService parkCollectCouponsService;
    @Autowired
    private MerchantReconciliationService merchantReconciliationService;

    @PassToken(required = false)
    @ApiOperation(value = "对账记录-分页查询列表")
    @PostMapping(value = "/pageList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<MerchantReconciliationResp.ListDTO> pageList(@RequestBody MerchantReconciliationReq.QueryDTO dto) {
        // 请求数据转换
        MerchantReconciliationQuery query = MerchantReconciliationConverter.INSTANCE.toQuery(dto);
        // 调用服务
        PageInfoRespQuery resp = merchantReconciliationService.getPageList(query);
        // 响应数据转换
        return ResResult.success(MerchantReconciliationConverter.INSTANCE.toPage(resp));
    }

    @ApiOperation(value = "对账记录-确认发放")
    @PassToken(required = false, authority = AuthorityType.CREATE)
    @PostMapping(value = "/updateStatus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> updateStatus(@RequestBody MerchantReconciliationReq.UpdateStatusDTO dto) {
        // 调用服务
        merchantReconciliationService.updateStatus(dto.getId());
        // 响应数据转换
        return ResResult.success();
    }

    @ApiOperation(value = "统计剩余抵用券")
    @PassToken(required = false, verifyPermissions = false)
    @PostMapping(value = "/assignedNumber", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<ParkCollectCouponsResp.ParkCollectDTO> assignedNumber(
            @RequestBody ParkCollectCouponsReq.CountDTO dto
    ) {
        // 调用服务
        Map<String, Object> params = parkCollectCouponsService.assignedNumber(dto.getUserId());
        // 响应数据转换
        return ResResult.success(ParkCollectCouponsConverter.INSTANCE.toDTO(params));
    }

    @ApiOperation(value = "扫码领劵")
    @GetMapping(value = "/generateQRCode")
    @PassToken(required = false, verifyPermissions = false)
    public ResResult<String> generateQRCode(
            @ApiParam(value = "商户ID", required = true) @RequestParam String userId,
            @ApiParam(value = "跳转地址，地址结尾不需要拼接/", required = true) @RequestParam String url) {
        if (userId == null || userId.isEmpty())
            return ResResult.error(RespEnum.FAILURE.getCode(), "请输入商户ID");
        if (url == null || url.isEmpty())
            return ResResult.error(RespEnum.FAILURE.getCode(), "请输入跳转地址");
        // 移除 url 的最后一个字符
        if (url.endsWith("/")) url = url.substring(0, url.length() - 1);
        // 获取当前时间并加上 30 分钟
        LocalDateTime thirtyMinutesLater = LocalDateTime.now().plusMinutes(AppConstants.QR_TIME);
        // 将 LocalDateTime 转换为 Unix 时间戳
        long timestamp = thirtyMinutesLater.toInstant(ZoneOffset.UTC).toEpochMilli();
        // 二维码地址内容
        String content = url + "?userId=" + userId + "&timestamp=" + timestamp;
        // 响应数据转换
        return ResResult.success(
            QRCodeUtil.generateQRCode(userId, qrCodeUrl, thirdParty, content));
    }
}
