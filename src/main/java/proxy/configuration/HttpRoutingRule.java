package proxy.configuration;

public class HttpRoutingRule {
    private String name;
    private String serviceUrl;

    public void setName(String name) {
        this.name = name;
    }

    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    public String toString() {
        return String.format("name=%s, serviceUri=%s", name, serviceUrl);
    }

}

