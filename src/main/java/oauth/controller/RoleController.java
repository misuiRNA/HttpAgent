package oauth.controller;

import oauth.entity.dto.RoleInfo;
import oauth.entity.vo.RoleVO;
import oauth.service.RoleService;
import oauth.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @PostMapping("/update/{id}")
    public int update(@PathVariable("id") Integer roleId, @Validated @RequestBody RoleVO roleVO) {
        RoleInfo info = RoleInfo.build(roleVO);
        info.setRoleId(roleId);
        return roleService.updateRole(info);
    }

    @PostMapping("/delete/{id}")
    public int delete(@PathVariable("id") Integer roleId) {
        return roleService.deleteRole(roleId);
    }

    @GetMapping("/list")
    public List<RoleVO> listAll() {
        List<RoleInfo> infos = roleService.listAllRoles();
        List<RoleVO> vos = infos.stream().map(RoleVO::build).collect(Collectors.toList());
        return vos;
    }

}
