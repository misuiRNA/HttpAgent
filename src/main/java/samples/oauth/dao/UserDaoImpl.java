package samples.oauth.dao;

import oauth.dao.UserDao;
import oauth.entity.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    @Override
    public List<User> listAll() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        return users;
    }

    @Override
    public User getUser(Integer userId) {
        User user = new User();
        user.setUserId(userId);
        return user;
    }

    @Override
    public int create(User user) {
        return 0;
    }

    @Override
    public int update(User user) {
        return 0;
    }

    @Override
    public int delete(Integer userId) {
        return 0;
    }
}
