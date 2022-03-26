package oauth.domain.dto;

import lombok.Data;
import oauth.domain.vo.UserVO;

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
}
