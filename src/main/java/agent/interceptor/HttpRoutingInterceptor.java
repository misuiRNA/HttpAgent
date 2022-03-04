package agent.interceptor;

import agent.service.HttpRoutingService;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

import static utils.AnsiString.*;

public class HttpRoutingInterceptor implements HandlerInterceptor {

    private HttpRoutingService routingService;

    public HttpRoutingInterceptor(HttpRoutingService routingService) {
        this.routingService = routingService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String targetUrl = routingService.routeUrl(request);
        if (null == targetUrl) {
            System.out.println("[routing] " + red("match routing rule failed!"));
            return true;
        }
        System.out.println("[routing] " + green(String.format("match routing rule success: %s ==>> %s",
                                         request.getRequestURI(),
                                         targetUrl)));

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
