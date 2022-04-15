package oauth.dao;

import oauth.entity.domain.User;

import java.util.List;

public interface UserDao {

    List<User> listAll();

    User getUser(Integer userId);

    int create(User user);

    int update(User user);

    int delete(Integer userId);

}
