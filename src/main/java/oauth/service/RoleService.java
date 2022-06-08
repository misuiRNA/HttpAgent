package oauth.service;

import oauth.dao.RoleDao;
import oauth.entity.domain.Role;
import oauth.entity.dto.RoleInfo;
import oauth.entity.utils.RoleBuilder;
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
        return RoleBuilder.buildInfo(role);
    }

    public int createRole(RoleInfo info) {
        Role role = RoleBuilder.build(info);
        return roleDao.create(role);
    }

    public int updateRole(RoleInfo info) {
        Role role = RoleBuilder.build(info);
        return roleDao.update(role);
    }

    public int deleteRole(Integer roleId) {
        return roleDao.delete(roleId);
    }

    public List<RoleInfo> listAllRoles() {
        List<Role> roles = roleDao.listAll();
        return roles.stream().map(RoleBuilder::buildInfo).collect(Collectors.toList());
    }

}
