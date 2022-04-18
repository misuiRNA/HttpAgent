package samples.oauth.dao;

import oauth.dao.UserDao;
import oauth.entity.domain.User;
import org.springframework.stereotype.Component;
import samples.oauth.mokedata.MockUser;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    private final MockUser mockUser = MockUser.instance();

    @Override
    public List<User> listAll() {
        return mockUser.getAll();
    }

    @Override
    public User getUser(Integer userId) {
        return mockUser.get(userId);
    }

    @Override
    public int create(User user) {
        mockUser.set(mockUser.nextId(), user);
        return 0;
    }

    @Override
    public int update(User user) {
        mockUser.set(user.getUserId(), user);
        return 0;
    }

    @Override
    public int delete(Integer userId) {
        mockUser.remove(userId);
        return 0;
    }
}
