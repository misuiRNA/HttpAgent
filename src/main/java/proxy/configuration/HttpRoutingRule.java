package proxy.configuration;

public class HttpRoutingRule {
    private String serviceName;
    private String serviceUrl;

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String getServiceName() {
        return serviceName;
    }

    public String toString() {
        return String.format("serviceName=%s, serviceUrl=%s", serviceName, serviceUrl);
    }
}

