package samples.oauth.mokedata;

import oauth.entity.domain.Role;

import java.util.*;

public class MockRole {

    private static final MockRole inst = new MockRole();

    private int keyCount = 0;

    public Map<Integer, Role> mockRoleMap = new HashMap<>();
    {
        mockRoleMap.put(nextId(), createRole(keyCount, "管理员"));
        mockRoleMap.put(nextId(), createRole(keyCount, "角色001"));
        mockRoleMap.put(nextId(), createRole(keyCount, "角色002"));
        mockRoleMap.put(nextId(), createRole(keyCount, "角色003"));
        mockRoleMap.put(nextId(), createRole(keyCount, "角色005"));
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

}
