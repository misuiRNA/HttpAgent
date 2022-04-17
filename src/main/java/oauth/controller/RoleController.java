package oauth.controller;

import oauth.entity.dto.RoleInfo;
import oauth.entity.factory.RoleFactory;
import oauth.entity.vo.RoleVO;
import oauth.service.RoleService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return RoleFactory.instance().buildVO(roleInfo);
    }

    @PostMapping("/create")
    public int create(@Validated @RequestBody RoleVO roleVO) {
        RoleInfo info = RoleFactory.instance().buildInfo(roleVO);
        return roleService.createRole(info);
    }

    @PostMapping("/update/{id}")
    public int update(@PathVariable("id") Integer roleId, @Validated @RequestBody RoleVO roleVO) {
        RoleInfo info = RoleFactory.instance().buildInfo(roleVO);
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
        return RoleFactory.instance().buildVO(infos);
    }

}
