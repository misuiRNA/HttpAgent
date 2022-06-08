package oauth.entity.utils;

import oauth.entity.domain.User;
import oauth.entity.dto.RoleInfo;
import oauth.entity.dto.UserInfo;
import oauth.entity.vo.RoleVO;
import oauth.entity.vo.UserVO;

import java.util.List;
import java.util.stream.Collectors;

public class UserBuilder {

    /**************
     **** User ****
     **************/

    public static User build(UserInfo info) {
        if (info == null) {
            return null;
        }

        User user = new User();
        user.setUserId(info.getUserId());
        user.setUserName(info.getUserName());
        user.setPassword(info.getPassword());
        return user;
    }

    /******************
     **** UserInfo ****
     ******************/

    public static UserInfo buildInfo(UserVO vo) {
        if (vo == null) {
            return null;
        }

        UserInfo info = new UserInfo();
        info.setUserId(vo.getUserId());
        info.setUserName(vo.getUserName());
        info.setPassword(vo.getPassword());
        List<RoleInfo> roles = vo.getRoles().stream().map(RoleBuilder::buildInfo).collect(Collectors.toList());
        info.setRoles(roles);
        return info;
    }

    public static UserInfo buildInfo(User user) {
        if (user == null) {
            return null;
        }

        UserInfo info = new UserInfo();
        info.setUserId(user.getUserId());
        info.setUserName(user.getUserName());
        info.setPassword(user.getPassword());
        List<RoleInfo> roles = user.getRoles().stream().map(RoleBuilder::buildInfo).collect(Collectors.toList());
        info.setRoles(roles);
        return info;
    }

    /****************
     **** UserVO ****
     ****************/

    public static UserVO buildVO(UserInfo info) {
        if (info == null) {
            return null;
        }

        UserVO vo = new UserVO();
        vo.setUserId(info.getUserId());
        vo.setUserName(info.getUserName());
        vo.setPassword(info.getPassword());
        List<RoleVO> roles = info.getRoles().stream().map(RoleBuilder::buildVO).collect(Collectors.toList());
        vo.setRoles(roles);
        return vo;
    }

}
