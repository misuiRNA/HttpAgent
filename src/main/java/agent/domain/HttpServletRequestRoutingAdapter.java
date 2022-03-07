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

public class HttpServletRequestRoutingAdapter {
    private final HttpServletRequest request;
    private final HttpRoutingRule rule;

    public HttpServletRequestRoutingAdapter(HttpServletRequest request, HttpRoutingRule rule) {
        this.request = request;
        this.rule = rule;
    }

    public String getTotalUrl() {
        return rule.routeUrl(request.getRequestURI()) + genQueryString();
    }

    public HttpMethod getMethod() {
        return HttpMethod.valueOf(request.getMethod());
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
            MultiValueMap<String, Object> formatBody = new LinkedMultiValueMap<String, Object>();
            for (Map.Entry<String, String[]> paramEntity : request.getParameterMap().entrySet()) {
                formatBody.addAll(paramEntity.getKey(), Arrays.asList(paramEntity.getValue()));
            }
            return formatBody;
        } else {
            return StreamUtils.copyToByteArray(request.getInputStream());
        }
    }

    public HttpEntity<Object> getEntity() {
        Object body = null;
        try {
            body = getBody();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new HttpEntity<Object>(body, getHeaders());
    }

    private String genQueryString() {
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
