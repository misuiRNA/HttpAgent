package proxy.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "route.routes")
public class HttpRouteConfig {
    private List<HttpRoutingRule> routingRules;

    public void setRoutingRules(List<HttpRoutingRule> routes) {
        System.out.println("========= set routing rules begin =========");
        for (HttpRoutingRule route : routes) {
            System.out.println("- " + route);
        }
        System.out.println("========= set routing rules end =========");
        this.routingRules = routes;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("num=%d\n<br>", routingRules.size()));
        for (Object route : routingRules) {
            res.append(route.toString());
            res.append("\n<br>");
        }
        return res.toString();
    }
}
