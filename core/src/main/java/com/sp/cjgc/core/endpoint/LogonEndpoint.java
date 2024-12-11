package com.sp.cjgc.core.endpoint;

import com.sp.cjgc.common.domain.query.SystemUsersQuery;
import com.sp.cjgc.core.service.SystemUsersService;
import com.sp.cjgc.core.endpoint.converter.SystemUsersConverter;
import com.sp.cjgc.core.endpoint.request.SystemUsersReq;
import com.sp.cjgc.core.endpoint.response.SystemUsersResp;
import com.sp.cjgc.common.exception.RequestObject;
import com.sp.cjgc.common.exception.ResponseObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Zoey
 * @Since: 2024-08-13 16:38:33
 * @Description:
 */
@RestController
@Api(tags = "用户登陆")
@RequestMapping("spUser")
public class LogonEndpoint {

    @Autowired
    private SystemUsersService systemUsersService;

    @ApiOperation(value = "账号密码登录")
    @PostMapping(value = "/logon", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseObject<SystemUsersResp.SystemUsersDTO> logon(@RequestBody RequestObject<SystemUsersReq.LoginDTO> ro) {
        // 请求数据转换
        SystemUsersQuery query = SystemUsersConverter.INSTANCE.toQuery(ro.getBody());
        // 用户登录
        return ResponseObject.success(SystemUsersConverter.INSTANCE.toDTO(systemUsersService.login(query)));
    }
}
