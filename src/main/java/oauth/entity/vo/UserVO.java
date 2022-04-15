package oauth.entity.vo;

import lombok.Data;
import oauth.entity.dto.UserInfo;

import java.util.List;

@Data
public class UserVO {

    private Integer userId;
    private String userName;
    private String password;
    private List<RoleVO> roles;

    public static UserVO build(UserInfo info) {
        UserVO vo = new UserVO();
        vo.setUserId(info.getUserId());
        vo.setUserName(info.getUserName());
        vo.setPassword(info.getPassword());
        // TODO fill roles
        return vo;
    }

}
