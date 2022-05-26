package io.exception;

public class InternalServerError extends ResponseException {

    private static final int code = 500;
    private static final String description = "internal server error";

    public InternalServerError(String message) {
        super(code, description, message);
    }

    public InternalServerError(Throwable cause) {
        super(code, description, cause);
    }

}
