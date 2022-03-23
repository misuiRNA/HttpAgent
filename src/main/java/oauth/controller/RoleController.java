package oauth.controller;

import oauth.bean.vo.RoleVO;
import oauth.bean.vo.UserVO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @GetMapping("/{id}")
    public RoleVO get(@PathVariable(name = "id") Integer roleId) {
        return null;
    }

    @PostMapping("/create")
    public int create(@Validated @RequestBody RoleVO roleVO) {
        return 0;
    }

    @PostMapping("/{id}/update")
    public int update(@PathVariable("id") Integer roleId, @Validated @RequestBody RoleVO roleVO) {
        return 0;
    }

    @PostMapping("/{id}/delete")
    public int delete(@PathVariable("id") Integer roleId) {
        return 0;
    }

    @GetMapping("/list")
    public List<RoleVO> listAll() {
        return null;
    }

    @GetMapping("/{id}/listUser")
    public List<UserVO> listUserHasRole(@PathVariable("id") Integer roleId) {
        return null;
    }

}
