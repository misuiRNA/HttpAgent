package oauth.service;

import oauth.dao.RoleDao;
import oauth.entity.domain.Role;
import oauth.entity.dto.RoleInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleDao roleDao;

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public RoleInfo getRoleById(Integer roleId) {
        Role role = roleDao.getRole(roleId);
        return RoleInfo.buildFromRole(role);
    }

    public int createRole(RoleInfo info) {
        Role role = Role.build(info);
        return roleDao.create(role);
    }

    public int updateRole(RoleInfo info) {
        Role role = Role.build(info);
        return roleDao.update(role);
    }

    public int deleteRole(Integer roleId) {
        return roleDao.delete(roleId);
    }

    public List<RoleInfo> listAllRoles() {
        List<Role> roles = roleDao.listAll();
        List<RoleInfo> infos = roles.stream().map(RoleInfo::buildFromRole).collect(Collectors.toList());
        return infos;
    }
}
