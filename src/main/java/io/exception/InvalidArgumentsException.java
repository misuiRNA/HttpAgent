package io.exception;

public class InvalidArgumentsException extends ResponseException {

    private static final int code = 423;
    private static final String description = "invalid argument";

    public InvalidArgumentsException(String message) {
        super(code, description, message);
    }

}
