package oauth.controller;

import oauth.bean.vo.RoleVO;
import oauth.bean.vo.UserVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{id}")
    public UserVO get(@PathVariable(name = "id") Integer userId) {
        return null;
    }

    @PostMapping("/create")
    public int create(@Validated @RequestBody UserVO userVO) {
        return 0;
    }

    @PostMapping("/{id}/update")
    public int update(@PathVariable("id") Integer userId, @Validated @RequestBody UserVO userVO) {
        return 0;
    }

    @PostMapping("/{id}/delete")
    public int delete(@PathVariable("id") Integer userId) {
        return 0;
    }

    @GetMapping("/list")
    public List<UserVO> listAll() {
        return null;
    }

    @GetMapping("/{id}/listRoles")
    public List<RoleVO> listRoles(@PathVariable("id") Integer userId) {
        return null;
    }

}
