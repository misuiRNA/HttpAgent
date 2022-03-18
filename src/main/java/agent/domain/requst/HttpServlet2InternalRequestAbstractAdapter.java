package agent.domain.requst;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.Enumeration;

public abstract class HttpServlet2InternalRequestAbstractAdapter  implements InternalHttpRequest {
    final HttpServletRequest request;

    public HttpServlet2InternalRequestAbstractAdapter(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String getURI() {
        return request.getRequestURI();
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.valueOf(request.getMethod());
    }

    @Override
    public HttpEntity<Object> getEntity() {
        Object body = null;
        try {
            body = getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HttpEntity<>(body, getHeaders());
    }

    @Override
    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> it =  request.getHeaderNames();
        while (it.hasMoreElements()) {
            String headName = it.nextElement();
            headers.addAll(headName, Collections.list(request.getHeaders(headName)));
        }
        return headers;
    }

    @Override
    public String getQueryString() {
        String queryStr = request.getQueryString();
        if (null == queryStr || 0 == queryStr.length()) {
            return "";
        }

        try {
            queryStr = URLDecoder.decode(queryStr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return String.format("?%s", queryStr);
    }
}
