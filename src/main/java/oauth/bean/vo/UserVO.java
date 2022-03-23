package oauth.bean.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserVO {

    private Integer userId;
    private String userName;
    private String password;
    private List<RoleVO> roles;

}
