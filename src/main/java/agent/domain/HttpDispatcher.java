package agent.domain;

import agent.domain.requst.InternalHttpRequest;
import agent.domain.requst.MultipartHttpServlet2InternalRequestAdapter;
import agent.domain.requst.SimpleHttpServlet2InternalRequestAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

public class HttpDispatcher {

    private static final String HTTP_HEAD_INVOKE_SERVICE_NAME = "Target-Service";

    private final Map<String, InternalServiceAgent> interServiceMap = new HashMap<>();

    public void register(String name, InternalServiceAgent service) {
        interServiceMap.put(name, service);
    }

    public boolean shouldDispatch(HttpServletRequest request) {
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

    private InternalServiceAgent findService(HttpServletRequest request) {
        return interServiceMap.get(request.getHeader(HTTP_HEAD_INVOKE_SERVICE_NAME));
    }
    private InternalHttpRequest createInternalHttpRequest(HttpServletRequest request) {
        // TODO: try to optimize the condition, use head 'Content-Type'
        if (request instanceof StandardMultipartHttpServletRequest) {
            return new MultipartHttpServlet2InternalRequestAdapter((StandardMultipartHttpServletRequest)request);
        }
        return new SimpleHttpServlet2InternalRequestAdapter(request);
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

}
