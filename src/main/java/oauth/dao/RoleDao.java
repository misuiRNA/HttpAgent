package oauth.dao;

import oauth.entity.domain.Role;

import java.util.List;

public interface RoleDao {

    List<Role> listAll();

    Role getRole(Integer roleId);

    int create(Role role);

    int update(Role role);

    int delete(Integer roleId);

}
