package io.exception;

public class InvalidCredentialsException extends ResponseException {

    private static final int code = 403;
    private static final String description = "bad credential";

    public InvalidCredentialsException(String message) {
        super(code, description, message);
    }

}
