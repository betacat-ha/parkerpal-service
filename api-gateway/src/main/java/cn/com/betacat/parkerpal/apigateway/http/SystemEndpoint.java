package cn.com.betacat.parkerpal.apigateway.http;

import cn.com.betacat.parkerpal.apicontracts.converter.SystemCameraDeviceConverter;
import cn.com.betacat.parkerpal.apicontracts.converter.SystemChargeRulesConverter;
import cn.com.betacat.parkerpal.apicontracts.converter.SystemParkingConverter;
import cn.com.betacat.parkerpal.apicontracts.converter.SystemParkingDetailConverter;
import cn.com.betacat.parkerpal.apicontracts.converter.SystemRoleConverter;
import cn.com.betacat.parkerpal.apicontracts.converter.SystemUsersConverter;
import cn.com.betacat.parkerpal.apicontracts.converter.SystemWeChatJsapiPayConverter;
import cn.com.betacat.parkerpal.apicontracts.dto.req.SystemCameraDeviceReq;
import cn.com.betacat.parkerpal.apicontracts.dto.req.SystemChargeRulesReq;
import cn.com.betacat.parkerpal.apicontracts.dto.req.SystemParkingDetailReq;
import cn.com.betacat.parkerpal.apicontracts.dto.req.SystemParkingReq;
import cn.com.betacat.parkerpal.apicontracts.dto.req.SystemRoleReq;
import cn.com.betacat.parkerpal.apicontracts.dto.req.SystemUsersReq;
import cn.com.betacat.parkerpal.apicontracts.dto.req.SystemWeChatJsapiPayReq;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.SystemCameraDeviceResp;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.SystemChargeRulesResp;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.SystemParkingDetailResp;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.SystemParkingResp;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.SystemRoleResp;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.SystemUsersResp;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.SystemUsersResp.ListDTO;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.SystemWeChatJsapiPayResp;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemCameraDeviceService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemChargeRulesService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemParkingDetailService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemParkingService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemRoleService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemUsersService;
import cn.com.betacat.parkerpal.apicontracts.service.wxpay.WeChatJsapiPayService;
import cn.com.betacat.parkerpal.common.annotation.PassToken;
import cn.com.betacat.parkerpal.common.utils.AuthorityType;
import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import cn.com.betacat.parkerpal.domain.base.ResResult;
import cn.com.betacat.parkerpal.domain.entity.SystemCameraDevice;
import cn.com.betacat.parkerpal.domain.entity.SystemChargeRules;
import cn.com.betacat.parkerpal.domain.entity.SystemParking;
import cn.com.betacat.parkerpal.domain.entity.SystemParkingDetail;
import cn.com.betacat.parkerpal.domain.entity.SystemRole;
import cn.com.betacat.parkerpal.domain.entity.SystemUsers;
import cn.com.betacat.parkerpal.domain.entity.SystemWeChatJsapiPay;
import cn.com.betacat.parkerpal.domain.query.SystemCameraDeviceQuery;
import cn.com.betacat.parkerpal.domain.query.SystemParkingDetailQuery;
import cn.com.betacat.parkerpal.domain.query.SystemRoleQuery;
import cn.com.betacat.parkerpal.domain.query.SystemUsersQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 系统管理-用户表(SystemUsers)表控制层
 *
 * @author 
 * @since 2024-08-14 09:10:41
 */

@Api(tags = "系统管理")
@RequestMapping("systemManagement")
@RestController
public class SystemEndpoint {

    @Autowired
    private SystemRoleService systemRoleService;
    @Autowired
    private SystemUsersService systemUsersService;
    @Autowired
    private SystemParkingService systemParkingService;
    @Autowired
    private SystemChargeRulesService systemChargeRulesService;
    @Autowired
    private SystemCameraDeviceService systemCameraDeviceService;
    @Autowired
    private SystemParkingDetailService systemParkingDetailService;
    @Autowired
    private WeChatJsapiPayService weChatJsapiPayService;

    @PassToken(required = false)
    @ApiOperation(value = "用户-分页查询列表")
    @PostMapping(value = "/pageUserList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<ListDTO> pageUserList(@RequestBody SystemUsersReq.QueryDTO dto) {
        // 请求数据转换
        SystemUsersQuery query = SystemUsersConverter.INSTANCE.toQuery(dto);
        // 调用服务
        PageInfoRespQuery resp = systemUsersService.getPageList(query);
        // 响应数据转换
        return ResResult.success(SystemUsersConverter.INSTANCE.toPage(resp));
    }

    @ApiOperation(value = "用户-更改密码")
    @PassToken(required = false, authority = AuthorityType.CREATE)
    @PostMapping(value = "/updatePassword", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<SystemUsersResp.SystemUsersDTO> updatePassword(@RequestBody SystemUsersReq.UpdatePasswordDTO dto) {
        // 请求数据转换
        SystemUsersQuery query = SystemUsersConverter.INSTANCE.toQuery(dto);
        // 用户登录
        return ResResult.success(SystemUsersConverter.INSTANCE.toDTO(systemUsersService.updatePassword(query)));
    }

    @ApiOperation(value = "用户-新增或编辑")
    @PassToken(required = false, authority = AuthorityType.CREATE)
    @PostMapping(value = "/userCreateOrUpdate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<SystemUsersResp.SystemUsersDTO> userCreateOrUpdate(@RequestBody SystemUsersReq.CreateOrUpdateDTO dto) {
        // 请求数据转换
        SystemUsers newEntity = SystemUsersConverter.INSTANCE.toEntity(dto);
        // 用户登录
        return ResResult.success(SystemUsersConverter.INSTANCE.toDTO(systemUsersService.createOrUpdateUser(newEntity)));
    }

    @ApiOperation(value = "用户-删除")
    @PassToken(required = false, authority = AuthorityType.DELETE)
    @PostMapping(value = "/deleteUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> deleteUser(@RequestBody SystemUsersReq.DeleteDTO dto) {
        // 删除
        systemUsersService.removeUser(dto.getIds());
        // 响应数据转换
        return ResResult.success();
    }

    @ApiOperation(value = "用户-重置密码")
    @PassToken(required = false, authority = AuthorityType.CREATE)
    @PostMapping(value = "/resettingPassword", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> resettingPassword(@RequestBody SystemUsersReq.ResettingPasswordDTO dto) {
        // 删除
        systemUsersService.resettingPassword(dto.getIds(), dto.getPassword());
        // 响应数据转换
        return ResResult.success();
    }

    @ApiOperation(value = "用户-编辑(只编辑头像和用户名称)")
    @PassToken(required = false, verifyPermissions = false)
    @PostMapping(value = "/userUpdate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<SystemUsersResp.SystemUsersDTO> userUpdate(@RequestBody SystemUsersReq.UpdateDTO dto) {
        // 请求数据转换
        SystemUsers newEntity = SystemUsersConverter.INSTANCE.toSpUsers(dto);
        // 用户编辑
        return ResResult.success(SystemUsersConverter.INSTANCE.toDTO(systemUsersService.updateUser(newEntity)));
    }

    @ApiOperation(value = "用户-更新状态")
    @PassToken(required = false, authority = AuthorityType.CREATE)
    @PostMapping(value = "/updateUserStatus", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> updateUserStatus(@RequestBody SystemUsersReq.UpdateStatusDTO dto) {
        // 更新用户状态
        systemUsersService.updateStatus(dto.getIds(), dto.getStatus());
        return ResResult.success();
    }

    @PassToken(required = false)
    @ApiOperation(value = "角色-分页查询列表")
    @PostMapping(value = "/pageRoleList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<SystemRoleResp.ListDTO> pageRoleList(@RequestBody SystemRoleReq.QueryDTO dto) {
        // 请求数据转换
        SystemRoleQuery query = SystemRoleConverter.INSTANCE.toQuery(dto);
        // 调用服务
        PageInfoRespQuery resp = systemRoleService.getPageList(query);
        // 响应数据转换
        return ResResult.success(SystemRoleConverter.INSTANCE.toPage(resp));
    }

    @ApiOperation(value = "角色-新增或修改")
    @PassToken(required = false, authority = AuthorityType.CREATE)
    @PostMapping(value = "/createOrUpdateRole", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> createOrUpdateRole(@RequestBody SystemRoleReq.CreateOrUpdateDTO dto) {
        // 请求数据转换
        SystemRole entity = SystemRoleConverter.INSTANCE.toEntity(dto);
        // 调用服务
        systemRoleService.saveOrUpdate(entity);
        // 响应数据转换
        return ResResult.success();
    }

    @PassToken(required = false)
    @ApiOperation(value = "摄像头设备-查询列表")
    @PostMapping(value = "/getCameraList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<List<SystemCameraDeviceResp.SystemCameraDeviceDTO>> getCameraList(
            @RequestBody SystemCameraDeviceReq.QueryDTO dto
    ) {
        // 请求数据转换
        SystemCameraDeviceQuery query = SystemCameraDeviceConverter.INSTANCE.toQuery(dto);
        // 响应数据转换
        return ResResult.success(SystemCameraDeviceConverter.INSTANCE.toListDTO(systemCameraDeviceService.getList(query)));
    }

    @ApiOperation(value = "摄像头设备-新增或修改")
    @PassToken(required = false, authority = AuthorityType.CREATE)
    @PostMapping(value = "/newOrUpdatedCamera", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> newOrUpdatedCamera(@RequestBody SystemCameraDeviceReq.CreateOrUpdateDTO dto) {
        // 请求数据转换
        SystemCameraDevice entity = SystemCameraDeviceConverter.INSTANCE.toEntity(dto);
        // 新增或更新
        systemCameraDeviceService.createOrUpdate(entity);
        // 响应数据转换
        return ResResult.success();
    }

    @ApiOperation(value = "摄像头设备-删除")
    @PassToken(required = false, authority = AuthorityType.DELETE)
    @PostMapping(value = "/deleteCamera", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> deleteCamera(@RequestBody SystemCameraDeviceReq.DeleteDTO dto) {
        // 删除
        systemCameraDeviceService.removeBatchByIds(dto.getIds());
        // 响应数据转换
        return ResResult.success();
    }

    @PassToken(required = false)
    @ApiOperation(value = "收费规则-查询")
    @PostMapping(value = "/getChargeRules")
    public ResResult<SystemChargeRulesResp.SystemChargeRulesDTO> getChargeRules() {
        // 响应数据转换
        return ResResult.success(SystemChargeRulesConverter.INSTANCE.toDTO(systemChargeRulesService.getEntity()));
    }

    @ApiOperation(value = "收费规则-更新")
    @PostMapping(value = "/updateChargeRules")
    @PassToken(required = false, authority = AuthorityType.CREATE)
    public ResResult<SystemChargeRulesResp.SystemChargeRulesDTO> updateChargeRules(
            @RequestBody SystemChargeRulesReq.UpdateDTO dto
    ) {
        // 请求数据转换
        SystemChargeRules entity = SystemChargeRulesConverter.INSTANCE.toEntity(dto);
        // 响应数据转换
        return ResResult.success(SystemChargeRulesConverter.INSTANCE.toDTO(systemChargeRulesService.updateEntity(entity)));
    }

    @PassToken(required = false)
    @ApiOperation(value = "车位配置-查询")
    @PostMapping(value = "/getParking")
    public ResResult<SystemParkingResp.SystemParkingDTO> getParking() {
        // 响应数据转换
        return ResResult.success(SystemParkingConverter.INSTANCE.toDTO(systemParkingService.getEntity()));
    }

    @ApiOperation(value = "车位配置-更新")
    @PostMapping(value = "/updateParking")
    @PassToken(required = false, authority = AuthorityType.CREATE)
    public ResResult<SystemParkingResp.SystemParkingDTO> updateParking(@RequestBody SystemParkingReq.UpdateDTO dto) {
        // 请求数据转换
        SystemParking entity = SystemParkingConverter.INSTANCE.toEntity(dto);
        // 响应数据转换
        return ResResult.success(SystemParkingConverter.INSTANCE.toDTO(systemParkingService.updateEntity(entity)));
    }

    @PassToken(required = false)
    @ApiOperation(value = "租赁车位-分页查询列表")
    @PostMapping(value = "/pageParkingDetailList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<SystemParkingDetailResp.ListDTO> pageParkingDetailList(@RequestBody SystemParkingDetailReq.QueryDTO dto) {
        // 请求数据转换
        SystemParkingDetailQuery query = SystemParkingDetailConverter.INSTANCE.toQuery(dto);
        // 调用服务
        PageInfoRespQuery resp = systemParkingDetailService.getPageList(query);
        // 响应数据转换
        return ResResult.success(SystemParkingDetailConverter.INSTANCE.toPage(resp));
    }

    @ApiOperation(value = "租赁车位-新增或编辑")
    @PassToken(required = false, authority = AuthorityType.CREATE)
    @PostMapping(value = "/parkingDetailCreateOrUpdate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<SystemParkingDetailResp.SystemParkingDetailDTO> parkingDetailCreateOrUpdate(
            @RequestBody SystemParkingDetailReq.CreateOrUpdateDTO dto
    ) {
        // 请求数据转换
        SystemParkingDetail entity = SystemParkingDetailConverter.INSTANCE.toEntity(dto);
        // 响应数据转换
        return ResResult.success(SystemParkingDetailConverter.INSTANCE.toDTO(systemParkingDetailService.createOrUpdate(entity)));
    }

    @PassToken(required = false)
    @ApiOperation(value = "租赁车位-统计")
    @PostMapping(value = "/parkingDetailSum")
    public ResResult<Integer> parkingDetailSum() {
        // 响应数据转换
        return ResResult.success(systemParkingDetailService.sumAssignedNumber());
    }

    @ApiOperation(value = "租赁车位-删除")
    @PassToken(required = false, authority = AuthorityType.DELETE)
    @PostMapping(value = "/parkingDetailDelete", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> parkingDetailDelete(@RequestBody SystemParkingDetailReq.DeleteDTO dto) {
        systemParkingDetailService.removeBatchByIds(dto.getIds());
        return ResResult.success();
    }

    @PassToken(required = false)
    @ApiOperation(value = "微信JSAPI支付配置-查询")
    @PostMapping(value = "/getWeChatJsapiPay")
    public ResResult<SystemWeChatJsapiPayResp.SystemWeChatJsapiPayDTO> getWeChatJsapiPay() {
        // 响应数据转换
        return ResResult.success(SystemWeChatJsapiPayConverter.INSTANCE.toDTO(weChatJsapiPayService.getWeChatJsapiPay()));
    }

    @ApiOperation(value = "微信JSAPI支付配置-新增或更新")
    @PassToken(required = false, authority = AuthorityType.CREATE)
    @PostMapping(value = "/weChatJsapiPayCreateOrUpdate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<SystemWeChatJsapiPayResp.SystemWeChatJsapiPayDTO> weChatJsapiPayCreateOrUpdate(
            @RequestBody SystemWeChatJsapiPayReq.CreateOrUpdateDTO dto
    ) {
        // 请求数据转换
        SystemWeChatJsapiPay entity = SystemWeChatJsapiPayConverter.INSTANCE.toEntity(dto);
        // 响应数据转换
        return ResResult.success(SystemWeChatJsapiPayConverter.INSTANCE.toDTO(weChatJsapiPayService.createOrUpdate(entity)));
    }

    @PostMapping(value = "/uploadPrivateKey")
    @ApiOperation(value = "微信JSAPI支付配置-私钥文件上传")
    @PassToken(required = false, authority = AuthorityType.CREATE)
    @ApiImplicitParam(name = "files", value = "文件上传", required = true, dataType = "MultipartFile", allowMultiple = true, paramType = "query")
    public ResResult<String> uploadPrivateKey(@RequestParam("files") MultipartFile[] files) {
        // 响应数据转换
        return ResResult.success(weChatJsapiPayService.uploadPrivateKey(files[0]));
    }

    /**
     * 专门用于给前端使用
     *
     * @return
     */
    @PostMapping(value = "/getAppid")
    @ApiOperation(value = "微信JSAPI支付配置-公众号APPID查询")
    public ResResult<String> getAppid() {
        weChatJsapiPayService.getWeChatJsapiPay();
        return ResResult.success(weChatJsapiPayService.getAppId());
    }
}
