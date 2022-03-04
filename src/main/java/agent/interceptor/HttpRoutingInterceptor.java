package agent.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import agent.service.HttpRoutingService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class HttpRoutingInterceptor implements HandlerInterceptor {

    private HttpRoutingService routingManager;

    public HttpRoutingInterceptor(HttpRoutingService routingManager) {
        this.routingManager = routingManager;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String orgUri = request.getRequestURI();
        String targetUrl = routingManager.routeUrl(orgUri);
        if (null == targetUrl) {
            System.out.println("[routing]\033[31m match routing rule failed!\033[0m");
            return true;
        }

        String logInfo = String.format("[routing]\033[32m match routing rule success: %s ==>> %s\033[0m",  orgUri, targetUrl);
        System.out.println(logInfo);
        responseWithString(response, targetUrl);
        return false;
    }

    private void responseWithString(HttpServletResponse response, String content) {
        try {
            Writer writer = response.getWriter();
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
