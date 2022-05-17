package samples.oauth.controller;

import io.exception.InvalidArgumentsException;
import io.exception.InvalidCredentialsException;
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
    public String testIllegalArgumentException() throws InvalidArgumentsException {
        throw new InvalidArgumentsException("test - Invalid Argument Exception");
    }

    @GetMapping("/bad_cred")
    public String testInvalidCredentialsException() throws InvalidCredentialsException {
        throw new InvalidCredentialsException("test - Bad Credentials Exception");
    }

}
