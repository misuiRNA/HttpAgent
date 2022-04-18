package samples.oauth.dao;

import oauth.dao.RoleDao;
import oauth.entity.domain.Role;
import org.springframework.stereotype.Component;
import samples.oauth.mokedata.MockRole;

import java.util.List;

@Component
public class RoleDaoImpl implements RoleDao {

    MockRole mockRole = MockRole.instance();

    @Override
    public List<Role> listAll() {
        return mockRole.getAll();
    }

    @Override
    public Role getRole(Integer roleId) {
        return mockRole.get(roleId);
    }

    @Override
    public int create(Role role) {
        mockRole.set(mockRole.nextId(), role);
        return 0;
    }

    @Override
    public int update(Role role) {
        mockRole.set(role.getRoleId(), role);
        return 0;
    }

    @Override
    public int delete(Integer roleId) {
        mockRole.remove(roleId);
        return 0;
    }

}
