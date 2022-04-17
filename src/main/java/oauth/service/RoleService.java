package oauth.service;

import oauth.dao.RoleDao;
import oauth.entity.domain.Role;
import oauth.entity.dto.RoleInfo;
import oauth.entity.factory.RoleFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleDao roleDao;

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public RoleInfo getRoleById(Integer roleId) {
        Role role = roleDao.getRole(roleId);
        return RoleFactory.instance().buildInfo(role);
    }

    public int createRole(RoleInfo info) {
        Role role = RoleFactory.instance().build(info);
        return roleDao.create(role);
    }

    public int updateRole(RoleInfo info) {
        Role role = RoleFactory.instance().build(info);
        return roleDao.update(role);
    }

    public int deleteRole(Integer roleId) {
        return roleDao.delete(roleId);
    }

    public List<RoleInfo> listAllRoles() {
        List<Role> roles = roleDao.listAll();
        return RoleFactory.instance().buildInfo(roles);
    }

}
