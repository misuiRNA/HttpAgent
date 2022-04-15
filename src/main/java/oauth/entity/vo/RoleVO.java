package oauth.entity.vo;

import lombok.Data;
import oauth.entity.dto.RoleInfo;

@Data
public class RoleVO {

    private Integer roleId;
    private String roleName;

    public static RoleVO build(RoleInfo info) {
        RoleVO vo = new RoleVO();
        vo.setRoleId(info.getRoleId());
        vo.setRoleName(info.getRoleName());
        return vo;
    }

}
