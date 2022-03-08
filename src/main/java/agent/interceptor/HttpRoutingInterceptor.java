package agent.interceptor;

import agent.domain.HttpServletRequestRoutingAdapter;
import agent.service.HttpRoutingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

import static utils.AnsiString.green;
import static utils.AnsiString.red;

public class HttpRoutingInterceptor implements HandlerInterceptor {

    private final HttpRoutingService routingService;

    public HttpRoutingInterceptor(HttpRoutingService routingService) {
        this.routingService = routingService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!routingService.needRoute(request)) {
            log(red("match routing rule failed! " + request.getRequestURI()));
            return true;
        }
        HttpServletRequestRoutingAdapter req = new HttpServletRequestRoutingAdapter(request, routingService.findRoutingRule(request));
        log(green(String.format("match routing rule success: %s ==>> %s", request.getRequestURI(), req.getTotalUrl())));
        ResponseEntity<String> returnValue = routingService.invoke(req);
        fillResponse(response, returnValue);
        log("exchanged! http code is [" + response.getStatus() + "]");
        return false;
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

    private void log(String msg) {
        System.out.println("[routing] " + msg);
    }

}
