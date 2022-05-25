package sample.dao;

import oauth.dao.RoleDao;
import oauth.entity.domain.Role;
import org.springframework.stereotype.Component;
import sample.datamocker.RoleMock;

import java.util.List;

@Component
public class RoleDaoImpl implements RoleDao {

    RoleMock roleMock = RoleMock.instance();

    @Override
    public List<Role> listAll() {
        return roleMock.getAll();
    }

    @Override
    public Role getRole(Integer roleId) {
        return roleMock.get(roleId);
    }

    @Override
    public int create(Role role) {
        roleMock.set(roleMock.nextId(), role);
        return 0;
    }

    @Override
    public int update(Role role) {
        roleMock.set(role.getRoleId(), role);
        return 0;
    }

    @Override
    public int delete(Integer roleId) {
        roleMock.remove(roleId);
        return 0;
    }

}
