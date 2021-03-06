package oauth.service;

import oauth.dao.UserDao;
import oauth.entity.domain.User;
import oauth.entity.dto.UserInfo;
import oauth.entity.utils.UserBuilder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public UserInfo getUserById(Integer userId) {
        User user = userDao.getUser(userId);
        return UserBuilder.buildInfo(user);
    }

    public int createRole(UserInfo info) {
        User user = UserBuilder.build(info);
        return userDao.create(user);
    }

    public int updateRole(UserInfo info) {
        User user = UserBuilder.build(info);
        return userDao.update(user);
    }

    public int deleteRole(Integer userId) {
        return userDao.delete(userId);
    }

    public List<UserInfo> listAllUsers() {
        List<User> users = userDao.listAll();
        return users.stream().map(UserBuilder::buildInfo).collect(Collectors.toList());
    }

    public UserInfo getUserByName(String name) {
        List<User> users = userDao.listAll();

        User user = null;
        while (user == null && users.size() > 0) {
            User u = users.get(0);
            if (u.getUserName().equals(name)) {
                user = u;
            }
            users.remove(u);
        }

        return UserBuilder.buildInfo(user);
    }
}
