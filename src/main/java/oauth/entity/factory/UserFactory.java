package oauth.entity.factory;

import oauth.entity.domain.User;
import oauth.entity.dto.UserInfo;
import oauth.entity.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

public class UserFactory {

    private static final UserFactory singletonInst = new UserFactory();

    private UserFactory() {

    }

    public static UserFactory instance() {
        return singletonInst;
    }

    public User build(UserInfo info) {
        if (info == null) {
            return null;
        }

        User user = new User();
        user.setUserId(info.getUserId());
        user.setUserName(info.getUserName());
        user.setPassword(info.getPassword());
        return user;
    }

    public UserInfo buildInfo(UserVO vo) {
        if (vo == null) {
            return null;
        }

        UserInfo info = new UserInfo();
        info.setUserId(vo.getUserId());
        info.setUserName(vo.getUserName());
        info.setPassword(vo.getPassword());
        // TODO fill roles
        return info;
    }

    public UserInfo buildInfo(User user) {
        if (user == null) {
            return null;
        }

        UserInfo info = new UserInfo();
        info.setUserId(user.getUserId());
        info.setUserName(user.getUserName());
        info.setPassword(user.getPassword());
        // TODO fill roles
        return info;
    }

    public List<UserInfo> buildInfo(List<User> users) {
        if (users == null) {
            return null;
        }

        List<UserInfo> infos = new ArrayList<>(users.size());
        for (User user : users) {
            infos.add(buildInfo(user));
        }
        return infos;
    }

    public UserVO buildVO(UserInfo info) {
        if (info == null) {
            return null;
        }

        UserVO vo = new UserVO();
        vo.setUserId(info.getUserId());
        vo.setUserName(info.getUserName());
        vo.setPassword(info.getPassword());
        // TODO fill roles
        return vo;
    }

    public List<UserVO> buildVO(List<UserInfo> infos) {
        if (infos == null) {
            return null;
        }

        List<UserVO> vos = new ArrayList<>(infos.size());
        for (UserInfo info : infos) {
            vos.add(buildVO(info));
        }
        return vos;
    }

}
