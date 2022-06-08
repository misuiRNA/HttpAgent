package oauth.entity.utils;

import oauth.entity.domain.Role;
import oauth.entity.dto.RoleInfo;
import oauth.entity.vo.RoleVO;

public class RoleBuilder {

    /**************
     **** Role ****
     **************/

    public static Role build(RoleInfo info) {
        if (info == null) {
            return null;
        }

        Role role = new Role();
        role.setRoleId(info.getRoleId());
        role.setRoleName(info.getRoleName());
        return role;
    }

    /******************
     **** RoleInfo ****
     ******************/

    public static RoleInfo buildInfo(RoleVO vo) {
        if (vo == null) {
            return null;
        }

        RoleInfo info = new RoleInfo();
        info.setRoleId(vo.getRoleId());
        info.setRoleName(vo.getRoleName());
        return info;
    }

    public static RoleInfo buildInfo(Role role) {
        if (role == null) {
            return null;
        }

        RoleInfo info = new RoleInfo();
        info.setRoleId(role.getRoleId());
        info.setRoleName(role.getRoleName());
        return info;
    }

    /****************
     **** RoleVO ****
     ****************/

    public static RoleVO buildVO(RoleInfo info) {
        if (info == null) {
            return null;
        }

        RoleVO vo = new RoleVO();
        vo.setRoleId(info.getRoleId());
        vo.setRoleName(info.getRoleName());
        return vo;
    }

}
