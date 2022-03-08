package agent.domain;

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

    public ResponseEntity<String> invoke(InternalHttpRequest request)  throws RestClientException {
        String remoteUrl = parseUrl(request) + request.getQueryString();
        return new RestTemplate().exchange(remoteUrl, request.getMethod(), request.getEntity(), String.class);
    }

    private String parseUrl(InternalHttpRequest request) {
        String orgURI = request.getURI();
        String targetURI = orgURI.replaceFirst("/" + serviceName, "");
        log(green(String.format("match routing rule success: %s ==>> %s", orgURI, targetURI)));
        return serviceHostUrl + targetURI;
    }

    public String toString() {
        return String.format("serviceName=%s, serviceUrl=%s", serviceName, serviceHostUrl);
    }

    private static void log(String msg) {
        System.out.println("[InternalServiceAgent] " + msg);
    }
}
