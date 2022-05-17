package io.domain;

import io.exception.ResponseException;

public class HttpResult {
    private final Integer code;
    private final String message;
    private final Object data;

    HttpResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public static HttpResult result(Integer code, String message, Object data) {
        return new HttpResult(code, message, data);
    }

    public static HttpResult ok(Object data) {
        return result(200, "success", data);
    }

    public static HttpResult fail(ResponseException e) {
        return result(e.getCode(), e.getDescription(), e.getMessage());
    }

}
