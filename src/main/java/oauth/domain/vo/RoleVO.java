package oauth.domain.vo;

import lombok.Data;
import oauth.domain.dto.RoleInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class RoleVO {

    private Integer roleId;
    private String roleName;

    public static RoleVO build(RoleInfo info) {
        RoleVO vo = new RoleVO();
        vo.setRoleId(info.getRoleId());
        vo.setRoleName(info.getRoleName());
        return vo;
    }

    public static List<RoleVO> buildList(List<RoleInfo> infos) {
//        return infos.stream().map(RoleVO::build).collect(Collectors.toList());
        List<RoleVO> vos = new ArrayList<>(infos.size());
        for (RoleInfo info : infos) {
            vos.add(build(info));
        }
        return vos;
    }
}
