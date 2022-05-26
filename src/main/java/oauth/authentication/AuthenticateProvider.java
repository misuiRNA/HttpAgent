package oauth.authentication;

import io.exception.InvalidCredentialsException;
import oauth.entity.dto.UserInfo;
import oauth.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticateProvider extends AbstractUserDetailsAuthenticationProvider {

    private final UserService userService;

    public AuthenticateProvider(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        if (userDetails.getUsername() == null) {
            throw new IllegalArgumentException("can't find user");
        }

        if (authentication.getCredentials() == null) {
            throw new InvalidCredentialsException("unknown error!");
        }

        String password = authentication.getCredentials().toString();
        String expectedPassword = userDetails.getPassword();
        if (password == null || !password.equals(expectedPassword)) {
            throw new InvalidCredentialsException("incorrect password!");
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        UserInfo info = userService.getUserByName(username);
        return new UserDetailsAdapter(info);
    }
}
