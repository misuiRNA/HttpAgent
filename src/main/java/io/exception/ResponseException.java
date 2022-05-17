package io.exception;

public class ResponseException extends RuntimeException {

    private final int code;
    private final String description;

    public ResponseException(int code, String description, String message) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
