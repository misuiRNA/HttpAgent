package oauth.controller;

import oauth.entity.dto.UserInfo;
import oauth.entity.factory.UserFactory;
import oauth.entity.vo.UserVO;
import oauth.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        return UserFactory.instance().buildVO(info);
    }

    @PostMapping("/create")
    public int create(@Validated @RequestBody UserVO userVO) {
        UserInfo info = UserFactory.instance().buildInfo(userVO);
        return userService.createRole(info);
    }

    @PostMapping("/update/{id}")
    public int update(@PathVariable("id") Integer userId, @Validated @RequestBody UserVO userVO) {
        UserInfo info = UserFactory.instance().buildInfo(userVO);
        info.setUserId(userId);
        return userService.updateRole(info);
    }

    @PostMapping("/delete/{id}")
    public int delete(@PathVariable("id") Integer userId) {
        return userService.deleteRole(userId);
    }

    @GetMapping("/all")
    public List<UserVO> listAll() {
        List<UserInfo> infos = userService.listAllUsers();
        return UserFactory.instance().buildVO(infos);
    }

}
