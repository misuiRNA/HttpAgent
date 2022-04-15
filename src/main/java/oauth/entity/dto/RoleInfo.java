package oauth.entity.dto;

import lombok.Data;
import oauth.entity.domain.Role;
import oauth.entity.vo.RoleVO;

@Data
public class RoleInfo {

    private Integer roleId;
    private String roleName;

    public static RoleInfo build(RoleVO vo) {
        RoleInfo info = new RoleInfo();
        info.setRoleId(vo.getRoleId());
        info.setRoleName(vo.getRoleName());
        return info;
    }

    public static RoleInfo buildFromRole(Role role) {
        RoleInfo info = new RoleInfo();
        info.setRoleId(role.getRoleId());
        info.setRoleName(role.getRoleName());
        return info;
    }
}
