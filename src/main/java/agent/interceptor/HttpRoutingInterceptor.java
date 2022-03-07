package agent.interceptor;

import agent.service.HttpRoutingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URLDecoder;

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
        targetUrl += getQueryString(request);
        log(green(String.format("match routing rule success: %s ==>> %s", request.getRequestURI(), targetUrl)));

        ResponseEntity<String> returnValue = null;
        RestTemplate restTemplate = new RestTemplate();
        try {
            returnValue = restTemplate.getForEntity(targetUrl, String.class);
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

    private String getQueryString(HttpServletRequest request) {
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
