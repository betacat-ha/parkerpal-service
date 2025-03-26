package cn.com.betacat.parkerpal.core.interceptor;

import cn.com.betacat.parkerpal.apicontracts.mapper.SystemUsersMapper;
import cn.com.betacat.parkerpal.apicontracts.service.sys.SystemUsersService;
import cn.com.betacat.parkerpal.common.annotation.PassToken;
import cn.com.betacat.parkerpal.common.utils.AuthorityType;
import cn.com.betacat.parkerpal.common.utils.JwtUtil;
import cn.com.betacat.parkerpal.domain.entity.SystemUsers;
import cn.com.betacat.parkerpal.domain.enums.RespEnum;
import cn.com.betacat.parkerpal.domain.enums.RoleEnum;
import cn.com.betacat.parkerpal.core.exception.BizException;
import java.lang.reflect.Method;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
public class JwtAuthInterceptor implements HandlerInterceptor {

    @Value("${global.token}")
    private boolean openOrNotToken;

    @Value("${global.authorization}")
    private String authorization;

    @Autowired
    private SystemUsersService systemUsersService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 如果token验证未开启或不是映射到方法，则直接通过
        if (!openOrNotToken || !(handler instanceof HandlerMethod)) return true;

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        PassToken passToken = method.getAnnotation(PassToken.class);
        boolean verifyPermissions = passToken != null && !passToken.required();
        String authority = passToken != null ? passToken.authority() : AuthorityType.READ;

        // 获取并检查token
        String token = request.getHeader(authorization);
        if (StringUtils.isBlank(token))
            throw new BizException(RespEnum.FAILURE.getCode(), "请输入token");

        // 进行登录验证
        loginInspect(token, verifyPermissions, authority);

        // 存入用户信息
        request.setAttribute("user", systemUsersService.getEntityByAccountOrId(JwtUtil.getAccount(token)));

        return true;
    }

    public void loginInspect(String token, boolean verifyPermissions, String authority) {
        // 从token中获取用户名
        String account = JwtUtil.getAccount(token);

        // 根据账户查找用户信息
        SystemUsers user = systemUsersService.getEntityByAccountOrId(account);
        if (Objects.isNull(user))
            throw new BizException(RespEnum.FAILURE.getCode(), "用户不存在");

        // 验证token的有效性
        if (!JwtUtil.verify(token, account, user.getPassword()))
            throw new BizException(RespEnum.FAILURE.getCode(), "登录失效，请重新登录");

        // 检查用户状态
        switch (user.getStatus()) {
            case 2:
                throw new BizException(RespEnum.FAILURE.getCode(), "用户【" + account + "】已被锁定");
            case 3:
                throw new BizException(RespEnum.FAILURE.getCode(), "用户【" + account + "】已被禁用");
        }

        // 进一步验证用户权限
        if (verifyPermissions) {
            // 判断用户角色是否是超级管理员或是管理员
            if ((RoleEnum.CJ.getRoleId().equals(user.getRoleId()) || RoleEnum.GL.getRoleId().equals(user.getRoleId()))
                && authority.equals(AuthorityType.SUPER)) {
                // 如果是超级管理员或管理员且需要超级权限，则允许访问
                return;
            }

            // 获取用户权限
            String permissions = user.getPermissions();
            // 判断是否有设置权限, 没有设置权限，则默认只有查询权限
            if (StringUtils.isBlank(permissions)) {
                if (!authority.equals(AuthorityType.READ))
                    throw new BizException(RespEnum.FAILURE.getCode(), "您只有阅读权限");
            } else {
                // 增改权限判断
                if (authority.equals(AuthorityType.CREATE) && !permissions.contains(AuthorityType.CREATE)) {
                    throw new BizException(RespEnum.FAILURE.getCode(), "您没有新增与编辑权限");
                }
                // 删除权限判断
                if (authority.equals(AuthorityType.DELETE) && !permissions.contains(AuthorityType.DELETE)) {
                    throw new BizException(RespEnum.FAILURE.getCode(), "您没有删除权限");
                }
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}