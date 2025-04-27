package cn.com.betacat.parkerpal.apigateway.http;

import cn.com.betacat.parkerpal.apicontracts.converter.SystemUsersConverter;
import cn.com.betacat.parkerpal.apicontracts.dto.req.SystemUsersReq;
import cn.com.betacat.parkerpal.apicontracts.dto.resp.SystemUsersResp;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemUsersService;
import cn.com.betacat.parkerpal.domain.base.ResResult;
import cn.com.betacat.parkerpal.domain.query.SystemUsersQuery;
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
 * @since 2024-08-13 16:38:33
 */

@Api(tags = "用户登陆")
@RestController
@RequestMapping("spUser")
public class LogonEndpoint {

    @Autowired
    private SystemUsersService systemUsersService;

    @ApiOperation(value = "账号密码登录")
    @PostMapping(value = "/logon", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResResult<SystemUsersResp.SystemUsersDTO> logon(@RequestBody SystemUsersReq.LoginDTO dto) {
        // 请求数据转换
        SystemUsersQuery query = SystemUsersConverter.INSTANCE.toQuery(dto);
        // 用户登录
        return ResResult.success(SystemUsersConverter.INSTANCE.toDTO(systemUsersService.login(query)));
    }
}
