package oauth.controller;

import oauth.domain.dto.RoleInfo;
import oauth.domain.dto.UserInfo;
import oauth.domain.vo.RoleVO;
import oauth.domain.vo.UserVO;
import oauth.service.RoleService;
import oauth.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;
    private final UserService userService;

    public RoleController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public RoleVO get(@PathVariable(name = "id") Integer roleId) {
        RoleInfo roleInfo = roleService.getRoleById(roleId);
        return RoleVO.build(roleInfo);
    }

    @PostMapping("/create")
    public int create(@Validated @RequestBody RoleVO roleVO) {
        RoleInfo info = RoleInfo.build(roleVO);
        return roleService.createRole(info);
    }

    @PostMapping("/{id}/update")
    public int update(@PathVariable("id") Integer roleId, @Validated @RequestBody RoleVO roleVO) {
        RoleInfo info = RoleInfo.build(roleVO);
        info.setRoleId(roleId);
        return roleService.updateRole(info);
    }

    @PostMapping("/{id}/delete")
    public int delete(@PathVariable("id") Integer roleId) {
        return roleService.deleteRole(roleId);
    }

    @GetMapping("/list")
    public List<RoleVO> listAll() {
        List<RoleInfo> infos = roleService.listAllRoles();
        return RoleVO.buildList(infos);
    }

    @GetMapping("/{id}/allUsers")
    public List<UserVO> listUserHasRole(@PathVariable("id") Integer roleId) {
        List<UserInfo> infos = userService.listWithRole();
        return UserVO.buildList(infos);
    }

}
