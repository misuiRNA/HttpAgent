package oauth.domain.vo;

import lombok.Data;
import oauth.domain.dto.UserInfo;

import java.util.ArrayList;
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

    public static List<UserVO> buildList(List<UserInfo> infos) {
        List<UserVO> vos = new ArrayList<>(infos.size());
        for (UserInfo info : infos) {
            vos.add(build(info));
        }
        return vos;
    }
}
