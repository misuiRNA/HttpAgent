package oauth.domain.dto;

import lombok.Data;
import oauth.domain.vo.RoleVO;

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
}
