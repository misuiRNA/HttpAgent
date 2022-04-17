package oauth.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserInfo {

    private Integer userId;
    private String userName;
    private String password;
    private List<RoleInfo> roles;

}
