package samples.oauth.mokedata;

import oauth.entity.domain.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleMock {

    private static final RoleMock inst = new RoleMock();

    private int keyCount = 0;

    public Map<Integer, Role> roleMockMap = new HashMap<>();
    {
        roleMockMap.put(nextId(), createRole(keyCount, "ADMIN"));
        roleMockMap.put(nextId(), createRole(keyCount, "USER_OO1"));
        roleMockMap.put(nextId(), createRole(keyCount, "USER_OO2"));
        roleMockMap.put(nextId(), createRole(keyCount, "USER_OO3"));
        roleMockMap.put(nextId(), createRole(keyCount, "USER_OO4"));
    }

    private RoleMock() {

    }

    public static RoleMock instance() {
        return inst;
    }

    public Role get(Integer id) {
        return roleMockMap.get(id);
    }

    public void set(Integer id, Role role) {
        roleMockMap.put(id, role);
    }

    public void remove(Integer roleId) {
        roleMockMap.remove(roleId);
    }

    public Integer nextId() {
        return ++keyCount;
    }

    public List<Role> getAll() {
        return new ArrayList<>(roleMockMap.values());
    }

    private static Role createRole(Integer id, String name) {
        Role role = new Role();
        role.setRoleId(id);
        role.setRoleName(name);
        return role;
    }

    public static List<Role> listRoles(String ... roleNames) {
        List<Role> roles = new ArrayList<>(roleNames.length);
        for (String roleName : roleNames) {
            for (Role role : inst.roleMockMap.values()) {
                if (role.getRoleName().equals(roleName)) {
                    roles.add(role);
                }
            }
        }
        return roles;
    }

}
