package io.response;

import io.domain.HttpResult;
import io.exception.InternalServerError;
import io.exception.ResponseException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public HttpResult exceptionHandler(Exception e) {
        e.printStackTrace();
        return exceptionHandler(new InternalServerError(e.getMessage()));
    }

    @ExceptionHandler
    public HttpResult exceptionHandler(ResponseException e) {
        e.printStackTrace();
        return HttpResult.fail(e);
    }

}
