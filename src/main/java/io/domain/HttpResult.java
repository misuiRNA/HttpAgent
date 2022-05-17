package io.domain;

// TODO try to optimize with Generic(<T>)
public class HttpResult {
    private final Integer code;
    private final String message;
    private final Object data;

    public HttpResult(Integer code, String message, Object data) {
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

}
