package oauth.entity.factory;

import oauth.entity.domain.Role;
import oauth.entity.dto.RoleInfo;
import oauth.entity.vo.RoleVO;

import java.util.ArrayList;
import java.util.List;

public class RoleFactory {

    private static final RoleFactory singletonInst = new RoleFactory();

    private RoleFactory() {

    }

    public static RoleFactory instance() {
        return singletonInst;
    }

    public Role build(RoleInfo info) {
        if (info == null) {
            return null;
        }

        Role role = new Role();
        role.setRoleId(info.getRoleId());
        role.setRoleName(info.getRoleName());
        return role;
    }

    public RoleInfo buildInfo(RoleVO vo) {
        if (vo == null) {
            return null;
        }

        RoleInfo info = new RoleInfo();
        info.setRoleId(vo.getRoleId());
        info.setRoleName(vo.getRoleName());
        return info;
    }

    public RoleInfo buildInfo(Role role) {
        if (role == null) {
            return null;
        }

        RoleInfo info = new RoleInfo();
        info.setRoleId(role.getRoleId());
        info.setRoleName(role.getRoleName());
        return info;
    }

    public List<RoleInfo> buildInfo(List<Role> roles) {
        if (roles == null) {
            return null;
        }

        List<RoleInfo> infos = new ArrayList<>(roles.size());
        for (Role role : roles) {
            infos.add(buildInfo(role));
        }
        return infos;
    }

    // TODO try to optimize
    public List<RoleInfo> buildInfoWithVOS(List<RoleVO> roleVOS) {
        if (roleVOS == null) {
            return null;
        }

        List<RoleInfo> infos = new ArrayList<>(roleVOS.size());
        for (RoleVO vo : roleVOS) {
            infos.add(buildInfo(vo));
        }
        return infos;
    }

    public RoleVO buildVO(RoleInfo info) {
        if (info == null) {
            return null;
        }

        RoleVO vo = new RoleVO();
        vo.setRoleId(info.getRoleId());
        vo.setRoleName(info.getRoleName());
        return vo;
    }

    public List<RoleVO> buildVO(List<RoleInfo> infos) {
        if (infos == null) {
            return null;
        }

        List<RoleVO> vos = new ArrayList<>(infos.size());
        for (RoleInfo info : infos) {
            vos.add(buildVO(info));
        }
        return vos;
    }

}
