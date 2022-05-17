package io.response;

import io.domain.HttpResult;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public HttpResult exceptionHandler(Exception e) {
        e.printStackTrace();
        return new HttpResult(500, "internal error", e.getMessage());
    }

    @ExceptionHandler
    public HttpResult exceptionHandler(IllegalArgumentException e) {
        e.printStackTrace();
        return new HttpResult(423, "invalid arguments", e.getMessage());
    }

    @ExceptionHandler
    public HttpResult exceptionHandler(BadCredentialsException e) {
        e.printStackTrace();
        return new HttpResult(403, "bad credentials", e.getMessage());
    }

}
