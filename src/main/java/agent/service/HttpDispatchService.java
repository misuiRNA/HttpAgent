package agent.service;

import agent.domain.HttpDispatcher;
import agent.domain.InternalServiceAgent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ConfigurationProperties(prefix = "dispatcher")
public class HttpDispatchService {

    private final HttpDispatcher dispatcher = new HttpDispatcher();

    public void setInterServices(List<InternalServiceAgent> interServices) {
        log("========= start init http invoke dispatcher =========");
        for (InternalServiceAgent service : interServices) {
            log("- " + service);
            dispatcher.register(service.getServiceName(), service);
        }
        log("========= init http invoke dispatcher end =========");
    }

    public HttpDispatcher getDispatcher() {
        return dispatcher;
    }

    private static void log(String msg) {
        System.out.println("[dispatcher] " + msg);
    }

}
