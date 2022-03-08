package agent.domain;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import java.io.IOException;

public interface InternalHttpRequest {

    String getURI();

    HttpMethod getMethod();

    HttpEntity<Object> getEntity();

    HttpHeaders getHeaders();

    Object getBody() throws IOException;

    String getQueryString();
}
