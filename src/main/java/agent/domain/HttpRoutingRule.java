package agent.domain;

public class HttpRoutingRule {
    private String serviceName;
    private String serviceHostUrl;

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceHostUrl(String serviceHostUrl) {
        this.serviceHostUrl = serviceHostUrl;
    }

    public String getServiceHostUrl() {
        return serviceHostUrl;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String toString() {
        return String.format("serviceName=%s, serviceUrl=%s", serviceName, serviceHostUrl);
    }
}

