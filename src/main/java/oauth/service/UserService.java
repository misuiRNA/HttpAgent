package oauth.service;

import oauth.domain.dto.UserInfo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    public UserInfo getUserById(Integer userId) {
        return null;
    }

    public int createRole(UserInfo info) {
        return 0;
    }

    public int updateRole(UserInfo info) {
        return 0;
    }

    public int deleteRole(Integer userId) {
        return 0;
    }

    public List<UserInfo> listAllUsers() {
        return null;
    }

    public List<UserInfo> listWithRole() {
        return null;
    }

}
