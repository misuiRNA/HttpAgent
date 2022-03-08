package agent.configuration;

import agent.service.HttpDispatchService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import agent.interceptor.HttpRoutingInterceptor;

@Configuration
public class HttpRoutingConfigurer implements WebMvcConfigurer {

    private HttpDispatchService routingManager;
    
    public HttpRoutingConfigurer(HttpDispatchService routingManager) {
        this.routingManager = routingManager;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new HttpRoutingInterceptor(routingManager));
        registration.addPathPatterns("/**");
    }
}
