package oauth.entity.domain;

import oauth.entity.dto.UserInfo;

import java.util.List;

public class User {

    private Integer userId;
    private String userName;
    private String password;
    private List<Role> roles;

    public static User build(UserInfo info) {
        User user = new User();
        user.userId = info.getUserId();
        user.userName = info.getUserName();
        user.password = info.getPassword();
        return user;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
