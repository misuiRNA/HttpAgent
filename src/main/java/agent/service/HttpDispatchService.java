package agent.service;

import agent.domain.SimpleHttpServlet2InternalRequestAdapter;
import agent.domain.InternalHttpRequest;
import agent.domain.InternalServiceAgent;
import agent.domain.MultipartHttpServlet2InternalRequestAdapter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ConfigurationProperties(prefix = "dispatcher")
public class HttpDispatchService {
    private List<InternalServiceAgent> interServices;
    private Map<String, InternalServiceAgent> interServiceMap;

    public List<InternalServiceAgent> getInterServices() {
        return interServices;
    }

    public void setInterServices(List<InternalServiceAgent> interServices) {
        this.interServices = interServices;
        interServiceMap = new HashMap<>();
        for (InternalServiceAgent rule : interServices) {
            interServiceMap.put(rule.getServiceName(), rule);
        }
        logInterServicesInit(interServices);
    }

    public boolean needDispatchToInternalServices(HttpServletRequest request) {
        InternalServiceAgent serviceAgent = findService(request);
        return null != serviceAgent;
    }

    public void dispatch(HttpServletRequest request, HttpServletResponse response) {
        InternalServiceAgent service = findService(request);
        if (null == service) {
            return;
        }

        ResponseEntity<String> returnValue;
        try {
            returnValue = service.invoke(createInternalHttpRequest(request));
        } catch (HttpStatusCodeException e) {
            returnValue = new ResponseEntity<>(e.getResponseBodyAsString(), e.getStatusCode());
            e.printStackTrace();
        } catch (ResourceAccessException e) {
            returnValue = new ResponseEntity<>("Connection refused! Can't find service(s)", HttpStatus.BAD_GATEWAY);
            e.printStackTrace();
        } catch (RestClientException e) {
            returnValue = new ResponseEntity<>("Unknown Error!", HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        fillResponse(response, returnValue);
    }

    private InternalHttpRequest createInternalHttpRequest(HttpServletRequest request) {
        // TODO: try to optimize the condition, use head 'Content-Type'
        if (request instanceof StandardMultipartHttpServletRequest) {
            return new MultipartHttpServlet2InternalRequestAdapter((StandardMultipartHttpServletRequest)request);
        }
        return new SimpleHttpServlet2InternalRequestAdapter(request);
    }

    private InternalServiceAgent findService(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String serviceName = uri.split("[/]")[1];
        return interServiceMap.get(serviceName);
    }

    private void fillResponse(HttpServletResponse response, ResponseEntity<String> returnValue) {
        response.setStatus(returnValue.getStatusCodeValue());

        for (Map.Entry<String, List<String>> entity : returnValue.getHeaders().entrySet()) {
            response.setHeader(entity.getKey(), entity.getValue().get(0));
        }

        try {
            Writer writer = response.getWriter();
            writer.write(returnValue.getBody());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO try to move out as AOP
    private void logInterServicesInit(List<InternalServiceAgent> routingRules) {
        log("========= init routing rules begin =========");
        for (InternalServiceAgent route : routingRules) {
            log("- " + route);
        }
        log("========= init routing rules end =========");
    }

    private static void log(String msg) {
        System.out.println("[dispatcher] " + msg);
    }

}
