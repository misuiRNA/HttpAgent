package oauth.authentication;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class URLFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final AntPathMatcher matcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String url = ((FilterInvocation) object).getRequestUrl();

        List<ConfigAttribute> accessRoles = new ArrayList<>();
        if (matcher.match("/admin/**", url)) {
            accessRoles.add(new SecurityConfig("ADMIN"));
        }
        return accessRoles;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
