package oauth.controller;

import oauth.authentication.JWTEntry;
import oauth.authentication.JWTUtils;
import oauth.authentication.UserDetailsAdapter;
import oauth.entity.dto.UserInfo;
import oauth.entity.request.LoginForm;
import oauth.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTUtils jwtUtils;

    public LoginController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtils = new JWTUtils();
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getName(), loginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsAdapter userDetails = (UserDetailsAdapter) authentication.getPrincipal();

        // TODO try to optimize
        UserInfo userInfo = userService.getUserByName(userDetails.getUsername());

        JWTEntry entry = new JWTEntry();
        entry.setTokenId("0001");
        entry.setTokenSubject("test_subject");
        entry.setTokenDurationMillis(60 * 60 * 1000);

        entry.setUserName(userInfo.getUserName());
        entry.setRoleName("None");
        return jwtUtils.encrypt(entry);
    }

}
