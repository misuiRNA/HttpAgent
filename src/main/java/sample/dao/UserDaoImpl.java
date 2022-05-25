package sample.dao;

import oauth.dao.UserDao;
import oauth.entity.domain.User;
import org.springframework.stereotype.Component;
import sample.datamocker.UserMock;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    private final UserMock userMock = UserMock.instance();

    @Override
    public List<User> listAll() {
        return userMock.getAll();
    }

    @Override
    public User getUser(Integer userId) {
        return userMock.get(userId);
    }

    @Override
    public int create(User user) {
        userMock.set(userMock.nextId(), user);
        return 0;
    }

    @Override
    public int update(User user) {
        userMock.set(user.getUserId(), user);
        return 0;
    }

    @Override
    public int delete(Integer userId) {
        userMock.remove(userId);
        return 0;
    }
}
