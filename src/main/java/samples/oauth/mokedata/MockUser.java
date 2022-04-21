package samples.oauth.mokedata;

import oauth.entity.domain.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockUser {

    private static final MockUser inst = new MockUser();

    private int keyCount = 0;

    public Map<Integer, User> mockUserMap = new HashMap<>();
    {
        mockUserMap.put(nextId(), createUser(keyCount, "Tom001", "123456"));
        mockUserMap.put(nextId(), createUser(keyCount, "Tom002", "654321"));
        mockUserMap.put(nextId(), createUser(keyCount, "Tom003", "111111"));
        mockUserMap.put(nextId(), createUser(keyCount, "Tom004", "222222"));
        mockUserMap.put(nextId(), createUser(keyCount, "Tom005", "333333"));
    }

    private MockUser() {

    }

    public static MockUser instance() {
        return inst;
    }

    public User get(Integer id) {
        return mockUserMap.get(id);
    }

    public void set(Integer id, User User) {
        mockUserMap.put(id, User);
    }

    public void remove(Integer UserId) {
        mockUserMap.remove(UserId);
    }

    public Integer nextId() {
        return ++keyCount;
    }

    public List<User> getAll() {
        return new ArrayList<>(mockUserMap.values());
    }

    private static User createUser(Integer id, String name, String password) {
        User user = new User();
        user.setUserId(id);
        user.setUserName(name);
        user.setPassword(password);
        return user;
    }


}
