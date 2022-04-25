package oauth.authentication;

import oauth.entity.dto.UserInfo;
import oauth.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;

public class MyJWTUtils {

    private final UserService userService;

    public MyJWTUtils(UserService userService) {
        this.userService = userService;
    }

    public String encrypt(UserDetails userDetails) {
        return userDetails.getUsername();
    }

    public UserDetails decrypt(String token) {
        UserInfo info = userService.getUserByName(token);
        if (info == null) {
            return null;
        }
        return new AuthUser(info);
    }

}
