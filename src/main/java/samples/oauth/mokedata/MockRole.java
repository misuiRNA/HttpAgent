package samples.oauth.mokedata;

import oauth.entity.domain.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockRole {

    private static final MockRole inst = new MockRole();

    private int keyCount = 0;

    public Map<Integer, Role> mockRoleMap = new HashMap<>();
    {
        mockRoleMap.put(nextId(), createRole(keyCount, "ADMIN"));
        mockRoleMap.put(nextId(), createRole(keyCount, "USER_OO1"));
        mockRoleMap.put(nextId(), createRole(keyCount, "USER_OO2"));
        mockRoleMap.put(nextId(), createRole(keyCount, "USER_OO3"));
        mockRoleMap.put(nextId(), createRole(keyCount, "USER_OO4"));
    }

    private MockRole() {

    }

    public static MockRole instance() {
        return inst;
    }

    public Role get(Integer id) {
        return mockRoleMap.get(id);
    }

    public void set(Integer id, Role role) {
        mockRoleMap.put(id, role);
    }

    public void remove(Integer roleId) {
        mockRoleMap.remove(roleId);
    }

    public Integer nextId() {
        return ++keyCount;
    }

    public List<Role> getAll() {
        return new ArrayList<>(mockRoleMap.values());
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
            for (Role role : inst.mockRoleMap.values()) {
                if (role.getRoleName().equals(roleName)) {
                    roles.add(role);
                }
            }
        }
        return roles;
    }

}
