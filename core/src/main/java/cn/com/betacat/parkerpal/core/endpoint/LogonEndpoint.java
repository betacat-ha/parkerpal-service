package cn.com.betacat.parkerpal.core.endpoint;

import cn.com.betacat.parkerpal.common.domain.query.SystemUsersQuery;
import cn.com.betacat.parkerpal.core.endpoint.converter.SystemUsersConverter;
import cn.com.betacat.parkerpal.core.endpoint.request.SystemUsersReq;
import cn.com.betacat.parkerpal.core.endpoint.response.SystemUsersResp;
import cn.com.betacat.parkerpal.core.service.SystemUsersService;
import cn.com.betacat.parkerpal.common.exception.RequestObject;
import cn.com.betacat.parkerpal.common.exception.ResponseObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:
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
