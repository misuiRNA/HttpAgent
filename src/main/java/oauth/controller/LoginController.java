package oauth.controller;

import oauth.authentication.AuthUser;
import oauth.entity.request.LoginForm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class LoginController {

    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getName(), loginForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        return authUser.toString();
    }

}
