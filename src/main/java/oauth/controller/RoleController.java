package oauth.controller;

import oauth.entity.dto.RoleInfo;
import oauth.entity.utils.RoleBuilder;
import oauth.entity.vo.RoleVO;
import oauth.service.RoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{id}")
    public RoleVO get(@PathVariable(name = "id") Integer roleId) {
        RoleInfo roleInfo = roleService.getRoleById(roleId);
        return RoleBuilder.buildVO(roleInfo);
    }

    @PostMapping("/create")
    public int create(@Validated @RequestBody RoleVO roleVO) {
        RoleInfo info = RoleBuilder.buildInfo(roleVO);
        return roleService.createRole(info);
    }

    @PostMapping("/update/{id}")
    public int update(@PathVariable("id") Integer roleId, @Validated @RequestBody RoleVO roleVO) {
        RoleInfo info = RoleBuilder.buildInfo(roleVO);
        info.setRoleId(roleId);
        return roleService.updateRole(info);
    }

    @PostMapping("/delete/{id}")
    public int delete(@PathVariable("id") Integer roleId) {
        return roleService.deleteRole(roleId);
    }

    @GetMapping("/all")
    public List<RoleVO> listAll() {
        List<RoleInfo> infos = roleService.listAllRoles();
        return infos.stream().map(RoleBuilder::buildVO).collect(Collectors.toList());
    }

}
