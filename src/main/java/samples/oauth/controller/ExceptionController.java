package samples.oauth.controller;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exp")
public class ExceptionController {

    @GetMapping()
    public String testException() throws Exception {
        throw new Exception("test - Exception");
    }

    @GetMapping("/illegal_arg")
    public String testIllegalArgumentException() throws IllegalArgumentException {
        throw new IllegalArgumentException("test - IllegalArgumentException");
    }

    @GetMapping("/bad_cred")
    public String testBadCredentialsException() throws BadCredentialsException {
        throw new BadCredentialsException("test - BadCredentialsException");
    }

}
