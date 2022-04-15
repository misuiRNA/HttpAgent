package oauth.entity.dto;

import lombok.Data;
import oauth.entity.domain.User;
import oauth.entity.vo.UserVO;

import java.util.List;

@Data
public class UserInfo {

    private Integer userId;
    private String userName;
    private String password;
    private List<RoleInfo> roles;

    public static UserInfo build(UserVO vo) {
        UserInfo info = new UserInfo();
        info.setUserId(vo.getUserId());
        info.setUserName(vo.getUserName());
        info.setPassword(vo.getPassword());
        // TODO fill roles
        return info;
    }

    public static UserInfo buildFromUser(User user) {
        UserInfo info = new UserInfo();
        info.setUserId(user.getUserId());
        info.setUserName(user.getUserName());
        info.setPassword(user.getPassword());
        // TODO fill roles
        return info;
    }
}
