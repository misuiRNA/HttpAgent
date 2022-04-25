package samples.oauth.mokedata;

import oauth.entity.domain.Role;
import oauth.entity.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserMock {

    private static final UserMock inst = new UserMock();

    private int keyCount = 0;

    public Map<Integer, User> userMockMap = new HashMap<>();
    {
        userMockMap.put(nextId(), createUser(keyCount, "Tom001", "123456", RoleMock.listRoles("ADMIN")));
        userMockMap.put(nextId(), createUser(keyCount, "Tom002", "654321", RoleMock.listRoles("USER_001")));
        userMockMap.put(nextId(), createUser(keyCount, "Tom003", "111111", RoleMock.listRoles("ADMIN", "USER_001")));
        userMockMap.put(nextId(), createUser(keyCount, "Tom004", "222222", RoleMock.listRoles("USER_001")));
        userMockMap.put(nextId(), createUser(keyCount, "Tom005", "333333", RoleMock.listRoles("USER_001")));
    }

    private UserMock() {

    }

    public static UserMock instance() {
        return inst;
    }

    public User get(Integer id) {
        return userMockMap.get(id);
    }

    public void set(Integer id, User User) {
        userMockMap.put(id, User);
    }

    public void remove(Integer UserId) {
        userMockMap.remove(UserId);
    }

    public Integer nextId() {
        return ++keyCount;
    }

    public List<User> getAll() {
        return new ArrayList<>(userMockMap.values());
    }

    private static User createUser(Integer id, String name, String password, List<Role> roles) {
        User user = new User();
        user.setUserId(id);
        user.setUserName(name);
        user.setPassword(password);
        user.setRoles(roles);
        return user;
    }

}
