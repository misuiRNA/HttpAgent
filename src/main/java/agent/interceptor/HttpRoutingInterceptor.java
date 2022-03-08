package agent.interceptor;

import agent.service.HttpDispatchService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static utils.AnsiString.red;

public class HttpRoutingInterceptor implements HandlerInterceptor {

    private final HttpDispatchService dispatcher;

    public HttpRoutingInterceptor(HttpDispatchService dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (!dispatcher.needDispatchToInternalServices(request)) {
            log(red("match routing rule failed! " + request.getRequestURI()));
            return true;
        }
        dispatcher.dispatch(request, response);
        log("exchanged! http code is [" + response.getStatus() + "]");
        return false;
    }

    private static void log(String msg) {
        System.out.println("[routing] " + msg);
    }

}
