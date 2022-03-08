package agent.domain;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;

public class HttpServlet2InternalRequestAdapter implements InternalHttpRequest {
    private final HttpServletRequest request;

    public HttpServlet2InternalRequestAdapter(HttpServletRequest request) {
        this.request = request;
    }

    public String getURI() {
        return request.getRequestURI();
    }

    public HttpMethod getMethod() {
        return HttpMethod.valueOf(request.getMethod());
    }

    public HttpEntity<Object> getEntity() {
        Object body = null;
        try {
            body = getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HttpEntity<>(body, getHeaders());
    }

    public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> it =  request.getHeaderNames();
        while (it.hasMoreElements()) {
            String headName = it.nextElement();
            headers.addAll(headName, Collections.list(request.getHeaders(headName)));
        }
        return headers;
    }

    public Object getBody() throws IOException {
        if (request instanceof StandardMultipartHttpServletRequest) {
            MultiValueMap<String, Object> formatBody = new LinkedMultiValueMap<>();
            for (Map.Entry<String, String[]> paramEntity : request.getParameterMap().entrySet()) {
                formatBody.addAll(paramEntity.getKey(), Arrays.asList(paramEntity.getValue()));
            }
            return formatBody;
        } else {
            return StreamUtils.copyToByteArray(request.getInputStream());
        }
    }

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
