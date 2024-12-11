package com.sp.cjgc.core.utils;

import com.sp.cjgc.common.domain.SystemUsers;
import com.sp.cjgc.common.enums.RespEnum;
import com.sp.cjgc.common.enums.RoleEnum;
import com.sp.cjgc.common.exception.BizException;
import com.sp.cjgc.common.utils.AuthorityType;
import com.sp.cjgc.common.mapper.SystemUsersMapper;
import com.sp.cjgc.common.utils.JwtUtil;
import java.util.Objects;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author: Zoey
 * @Date: 2024/3/14
 * @Time: 下午3:25
 * @Describe:
 */
public class LoginUtil {

    /**
     * 登录验证
     *
     * @param token
     */
    public static void loginInspect(String token, boolean verifyPermissions, String authority, SystemUsersMapper spUsersMapper) {
        // 获取token中的用户名
        String account = JwtUtil.getAccount(token);
        // 查找数据库
        SystemUsers user = spUsersMapper.getEntityByAccount(account);
        // 判断是否有该用户
        if (Objects.isNull(user)) throw new BizException(RespEnum.FAILURE.getCode(), "用户不存在");
        // token 验证
        if (!JwtUtil.verify(token, account, user.getPassword()))
            throw new BizException(RespEnum.FAILURE.getCode(), "登录失效，请重新登录");
        if (user.getStatus() == 2) throw new BizException(RespEnum.FAILURE.getCode(), "用户【" + account + "】已被锁定");
        if (user.getStatus() == 3) throw new BizException(RespEnum.FAILURE.getCode(), "用户【" + account + "】已被禁用");
        // 判断是否需要进行权限验证
        if (verifyPermissions) {
            // 判断用户角色是否是超级管理员或是管理员
            if ((!RoleEnum.CJ.getRoleId().equals(user.getRoleId()) || RoleEnum.GL.getRoleId().equals(user.getRoleId()))
                    && authority.equals(AuthorityType.SUPER))
                throw new BizException(RespEnum.FAILURE.getCode(), "您只有阅读权限");
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
                if (authority.equals(AuthorityType.DELETE) && !permissions.contains(AuthorityType.DELETE))
                    throw new BizException(RespEnum.FAILURE.getCode(), "您没有删除权限");
            }
        }
    }
}
