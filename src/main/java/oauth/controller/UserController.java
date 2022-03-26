package oauth.controller;

import oauth.domain.dto.RoleInfo;
import oauth.domain.dto.UserInfo;
import oauth.domain.vo.RoleVO;
import oauth.domain.vo.UserVO;
import oauth.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public UserVO get(@PathVariable(name = "id") Integer userId) {
        UserInfo info = userService.getUserById(userId);
        return UserVO.build(info);
    }

    @PostMapping("/create")
    public int create(@Validated @RequestBody UserVO userVO) {
        UserInfo info = UserInfo.build(userVO);
        return userService.createRole(info);
    }

    @PostMapping("/{id}/update")
    public int update(@PathVariable("id") Integer userId, @Validated @RequestBody UserVO userVO) {
        UserInfo info = UserInfo.build(userVO);
        info.setUserId(userId);
        return userService.updateRole(info);
    }

    @PostMapping("/{id}/delete")
    public int delete(@PathVariable("id") Integer userId) {
        return userService.deleteRole(userId);
    }

    @GetMapping("/list")
    public List<UserVO> listAll() {
        List<UserInfo> infos = userService.listAllUsers();
        return UserVO.buildList(infos);
    }

    @GetMapping("/{id}/allRoles")
    public List<RoleVO> listRoles(@PathVariable("id") Integer userId) {
        UserInfo info = userService.getUserById(userId);
        List<RoleInfo> roles = info.getRoles();
        return RoleVO.buildList(roles);
    }

}
