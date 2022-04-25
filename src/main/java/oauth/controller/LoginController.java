package oauth.controller;

import oauth.authentication.UserDetailsAdapter;
import oauth.authentication.JWTToken;
import oauth.entity.dto.UserInfo;
import oauth.entity.request.LoginForm;
import oauth.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTToken jwtUtils;

    public LoginController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtils = new JWTToken(userService);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getName(), loginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsAdapter userDetails = (UserDetailsAdapter) authentication.getPrincipal();

        // TODO try to optimize
        UserInfo userInfo = userService.getUserByName(userDetails.getUsername());
        return jwtUtils.encrypt(userInfo);
    }

}
