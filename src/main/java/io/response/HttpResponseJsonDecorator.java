package io.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.domain.HttpResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class HttpResponseJsonDecorator implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // TODO try to optimize
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof HttpResult) {
            response.setStatusCode(HttpStatus.valueOf(((HttpResult) body).getCode()));
            return body;
        }

        Object result = new HttpResult(200, "success", body);
        if (body instanceof String) {
            HttpHeaders headers = response.getHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            result = objectToJsonString(result);
        }
        return result;
    }

    private String objectToJsonString(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void log(String msg) {
        System.out.println("[ResponseBodyAdvice] " + msg);
    }
}
