package agent.domain;

import agent.domain.requst.InternalHttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static utils.AnsiString.green;

public class InternalServiceAgent {
    private String serviceName;
    private String serviceHostUrl;

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceHostUrl(String serviceHostUrl) {
        this.serviceHostUrl = serviceHostUrl;
    }

    public String getServiceName() {
        return serviceName;
    }

    public boolean isAvailable() {
        return serviceName != null && serviceHostUrl != null;
    }

    public ResponseEntity<String> invoke(InternalHttpRequest request)  throws RestClientException {
        String remoteUrl = parseUrl(request) + request.getQueryString();
        log(green(String.format("invoke internal service: %s", remoteUrl)));
        return new RestTemplate().exchange(remoteUrl, request.getMethod(), request.getEntity(), String.class);
    }

    private String parseUrl(InternalHttpRequest request) {
        return serviceHostUrl + request.getURI();
    }

    public String toString() {
        return String.format("serviceName=%s, serviceUrl=%s", serviceName, serviceHostUrl);
    }

    private static void log(String msg) {
        System.out.println("[InternalServiceAgent] " + msg);
    }
}
