package agent.interceptor;

import agent.service.HttpRoutingService;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;
import java.util.*;

import static utils.AnsiString.green;
import static utils.AnsiString.red;

public class HttpRoutingInterceptor implements HandlerInterceptor {

    private final HttpRoutingService routingService;

    public HttpRoutingInterceptor(HttpRoutingService routingService) {
        this.routingService = routingService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String targetUrl = routingService.routeUrl(request);
        if (null == targetUrl) {
            log(red("match routing rule failed! " + request.getRequestURI()));
            return true;
        }
        targetUrl += genQueryString(request);
        log(green(String.format("match routing rule success: %s ==>> %s", request.getRequestURI(), targetUrl)));

        HttpMethod method = HttpMethod.valueOf(request.getMethod());
        HttpHeaders headers = genHeaders(request);

        Object body = null;
        try {
            body = genHttpRequestBody(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity<Object> requestEntity = new HttpEntity<Object>(body, headers);

        ResponseEntity<String> returnValue = null;
        try {
            returnValue = new RestTemplate().exchange(targetUrl, method, requestEntity, String.class);
        } catch (HttpStatusCodeException e) {
            returnValue = new ResponseEntity<String>(e.getResponseBodyAsString(), e.getStatusCode());
            e.printStackTrace();
        } catch (ResourceAccessException e) {
            returnValue = new ResponseEntity<String>("Connection refused! Can't find service(s)", HttpStatus.BAD_GATEWAY);
            e.printStackTrace();
        } catch (RestClientException e) {
            returnValue = new ResponseEntity<String>("Unknown Error!", HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
            throw e;
        } finally {
            fillResponse(response, returnValue);
            log("exchanged! http code is [" + response.getStatus() + "]");
        }
        return false;
    }

    private Object genHttpRequestBody(HttpServletRequest request) throws IOException {
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

    private String genQueryString(HttpServletRequest request) {
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
    private HttpHeaders genHeaders(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> it =  request.getHeaderNames();
        while (it.hasMoreElements()) {
            String headName = it.nextElement();
            headers.addAll(headName, Collections.list(request.getHeaders(headName)));
        }
        return headers;
    }
    private void fillResponse(HttpServletResponse response, ResponseEntity<String> returnValue) {
        response.setStatus(returnValue.getStatusCodeValue());
        response.setHeader("Content-type", "text/html;charset=UTF-8");

        try {
            Writer writer = response.getWriter();
            writer.write(returnValue.getBody());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void log(String msg) {
        System.out.println("[routing] " + msg);
    }

}
