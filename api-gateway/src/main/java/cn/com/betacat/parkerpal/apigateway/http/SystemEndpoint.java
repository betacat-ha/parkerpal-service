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
import cn.com.betacat.parkerpal.apicontracts.service.SystemParkingSpaceService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemCameraDeviceService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemChargeRulesService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemParkingDetailService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemParkingService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemRoleService;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemUsersService;
import cn.com.betacat.parkerpal.apicontracts.service.wxpay.WeChatJsapiPayService;
import cn.com.betacat.parkerpal.common.annotation.PassToken;
import cn.com.betacat.parkerpal.common.utils.AuthorityType;
import cn.com.betacat.parkerpal.core.exception.BizException;
import cn.com.betacat.parkerpal.domain.base.PageInfoRespQuery;
import cn.com.betacat.parkerpal.domain.base.ResResult;
import cn.com.betacat.parkerpal.domain.entity.*;
import cn.com.betacat.parkerpal.domain.enums.RespEnum;
import cn.com.betacat.parkerpal.domain.query.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统管理-用户表(SystemUsers)表控制层
 *
 * @author 
 * @since 2024-08-14 09:10:41
 */

@Api(tags = "系统管理")
@RequestMapping("systemManagement")
@RestController
@Slf4j
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
    @Autowired
    private SystemParkingSpaceService systemParkingSpaceService;

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

    @PassToken(required = false)
    @ApiOperation(value = "车主-分页查询列表")
    @PostMapping(value = "/pageCustomerList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<ListDTO> pageCustomerList(@RequestBody SystemUsersReq.QueryDTO dto) {
        // 请求数据转换
        SystemUsersQuery query = SystemUsersConverter.INSTANCE.toQuery(dto);
        // 调用服务
        PageInfoRespQuery resp = systemUsersService.getCustomerPageList(query);
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

    /**
     * 车位-分页查询列表
     * @param query 查询条件
     */
    @ApiOperation(value = "车位-分页查询列表")
    @PostMapping(value = "/pageParkingSpaceList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<PageInfoRespQuery> pageParkingSpaceList(@RequestBody SystemParkingSpaceQuery query) {
        return ResResult.success(systemParkingSpaceService.getPageList(query));
    }

    /**
     * 车位-新增或修改
     * @param query 查询条件
     * @return ResResult
     */
    @ApiOperation(value = "车位-新增或修改")
    @PostMapping(value = "/createOrUpdateParkingSpace", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<SystemParkingSpace> createOrUpdateParkingSpace(@RequestBody SystemParkingSpace query) {
        try {
            if (query.getId() == null) {
                systemParkingSpaceService.insert(query);
            } else {
                systemParkingSpaceService.updateBy(query);
            }
        } catch (BizException e) {
            return ResResult.error(RespEnum.FAILURE.getCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("新增或修改车位时遇到错误：", e);
            return ResResult.error(RespEnum.FAILURE.getCode(), "操作失败，请刷新页面重试");
        }
        return ResResult.success(query);
    }

    /**
     * 车位-删除
     * @param ids 车位ID
     */
    @ApiOperation(value = "车位-删除")
    @PassToken(required = false, authority = AuthorityType.DELETE)
    @PostMapping(value = "/deleteParkingSpace", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<?> deleteParkingSpace(@RequestBody List<String> ids) {
        try {
            systemParkingSpaceService.removeBatchByIds(ids);
        } catch (BizException e) {
            return ResResult.error(RespEnum.FAILURE.getCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("删除车位时遇到错误：", e);
            return ResResult.error(RespEnum.FAILURE.getCode(), "删除失败，请刷新页面重试");
        }
        return ResResult.success();
    }

    /**
     * 预约车位
     * @param query
     * @return ResResult
     */
    @ApiOperation(value = "车位-新增或修改预约记录")
    @PostMapping(value = "/reserveParking", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<SystemParkingSpace> reserveParking(@RequestBody SystemParkingSpaceReservationRecord query, HttpServletRequest request) {
        // 获取用户信息
        SystemUsers user = (SystemUsers) request.getAttribute("user");

        SystemParkingSpace result;
        try {
            if (query.getId() == null) {
                result = systemParkingSpaceService.newReservation(query, user);
            } else {
                result = systemParkingSpaceService.editReservation(query, user);
            }
        } catch (BizException e) {
            return ResResult.error(RespEnum.FAILURE.getCode(), e.getErrorMsg());
        } catch (Exception e) {
            log.error("预约车位时遇到错误：", e);
            return ResResult.error(RespEnum.FAILURE.getCode(), "预约车位失败，请刷新页面重试");
        }
        return ResResult.success(result);
    }

    /**
     * 分页查询车位预约信息
     * @param query 查询条件
     * @return ResResult
     */
    @ApiOperation(value = "车位-分页查询预约记录")
    @PostMapping(value = "/pageReservation", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<PageInfoRespQuery> pageReservation(@RequestBody SystemParkingSpaceReservationRecordQuery query) {
        PageInfoRespQuery pageInfoRespQuery = systemParkingSpaceService.getPageReservation(query);
        return ResResult.success(pageInfoRespQuery);
    }
}
