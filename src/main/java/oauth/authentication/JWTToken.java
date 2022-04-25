package oauth.authentication;

import oauth.entity.dto.UserInfo;
import oauth.service.UserService;

public class JWTToken {

    // TODO try to remove
    private final UserService userService;

    public JWTToken(UserService userService) {
        this.userService = userService;
    }

    public String encrypt(UserInfo userInfo) {
        return userInfo.getUserName();
    }

    public UserInfo decrypt(String token) {
        return userService.getUserByName(token);
    }

}
