package oauth.authentication;

import oauth.entity.dto.RoleInfo;
import oauth.entity.dto.UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthUser implements UserDetails {

    private final UserInfo userInfo;

    public AuthUser(UserInfo userInfo) {
        if (userInfo == null) {
            userInfo = UserInfo.defaultUserInfo();
        }
        this.userInfo = userInfo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<RoleInfo> userRoles = userInfo.getRoles();

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleInfo role : userRoles) {
            authorities.add((GrantedAuthority) role::getRoleName);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return userInfo.getPassword();
    }

    @Override
    public String getUsername() {
        return userInfo.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
