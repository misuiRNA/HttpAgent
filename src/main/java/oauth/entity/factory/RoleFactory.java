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
        Role role = new Role();
        role.setRoleId(info.getRoleId());
        role.setRoleName(info.getRoleName());
        return role;
    }

    public RoleInfo buildInfo(RoleVO vo) {
        RoleInfo info = new RoleInfo();
        info.setRoleId(vo.getRoleId());
        info.setRoleName(vo.getRoleName());
        return info;
    }

    public RoleInfo buildInfo(Role role) {
        RoleInfo info = new RoleInfo();
        info.setRoleId(role.getRoleId());
        info.setRoleName(role.getRoleName());
        return info;
    }

    public List<RoleInfo> buildInfo(List<Role> roles) {
        List<RoleInfo> infos = new ArrayList<>(roles.size());
        for (Role role : roles) {
            infos.add(buildInfo(role));
        }
        return infos;
    }

    public RoleVO buildVO(RoleInfo info) {
        RoleVO vo = new RoleVO();
        vo.setRoleId(info.getRoleId());
        vo.setRoleName(info.getRoleName());
        return vo;
    }

    public List<RoleVO> buildVO(List<RoleInfo> infos) {
        List<RoleVO> vos = new ArrayList<>(infos.size());
        for (RoleInfo info : infos) {
            vos.add(buildVO(info));
        }
        return vos;
    }

}
