package oauth.entity.domain;

import oauth.entity.dto.RoleInfo;

public class Role {

    private Integer roleId;
    private String roleName;

    public static Role build(RoleInfo info) {
        Role role = new Role();
        role.roleId = info.getRoleId();
        role.roleName = info.getRoleName();
        return role;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
