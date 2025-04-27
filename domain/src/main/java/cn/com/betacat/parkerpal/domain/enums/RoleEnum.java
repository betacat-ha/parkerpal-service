package cn.com.betacat.parkerpal.domain.enums;

import lombok.Getter;

/**
 * 角色枚举 -- 需要匹配数据库,可自定义
 * @author
 */
@Getter
public enum RoleEnum {
    CZ("0", "ordinary", "车主用户"),
    JL("1", "vip", "酒店用户"),
    FX("2", "ordinary", "商户用户"),
    GL("3", "admin", "管理员"),
    CJ("4", "super", "超级管理员"),
    ;


    private final String roleId;

    private final String roleCode;

    private final String roleName;

    RoleEnum(String roleId, String roleCode, String roleName) {
        this.roleId = roleId;
        this.roleCode = roleCode;
        this.roleName = roleName;
    }

    public static String getRoleName(String roleId) {
        for (RoleEnum v : RoleEnum.values()) {
            if (roleId.equals(v.getRoleId())) {
                return v.roleName;
            }
        }
        return roleId;
    }
}
