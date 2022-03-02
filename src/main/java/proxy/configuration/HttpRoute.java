package proxy.configuration;

public class HttpRoute {
    private String name;
    private String url;
    private int port;

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String toString() {
        return String.format("name=%s, path=%s, port=%d", name, url, port);
    }

}

