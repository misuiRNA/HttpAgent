package samples.oauth.dao;

import oauth.dao.RoleDao;
import oauth.entity.domain.Role;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleDaoImpl implements RoleDao {

    @Override
    public List<Role> listAll() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role());
        return roles;
    }

    @Override
    public Role getRole(Integer roleId) {
        Role role = new Role();
        role.setRoleId(roleId);
        return role;
    }

    @Override
    public int create(Role role) {
        return 0;
    }

    @Override
    public int update(Role role) {
        return 0;
    }

    @Override
    public int delete(Integer roleId) {
        return 0;
    }

}
