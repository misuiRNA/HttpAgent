package proxy.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "route.routes")
public class HttpRouteConfig {
    private List<HttpRoute> routeList;

    public void setRouteList(List<HttpRoute> routes) {
        System.out.println("========= print test begin =========");
        for (HttpRoute route : routes) {
            System.out.println("- " + route);
        }
        System.out.println("========= print test end =========");
        this.routeList = routes;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("num=%d\n<br>", routeList.size()));
        for (Object route : routeList) {
            res.append(route.toString());
            res.append("\n<br>");
        }
        return res.toString();
    }
}
