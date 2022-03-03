package proxy.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "route.manager")
public class HttpRoutingManager {
    private List<HttpRoutingRule> routingRules;
    private Map<String, HttpRoutingRule> routingRuleMap;

    public List<HttpRoutingRule> getRoutingRules() {
        return routingRules;
    }

    public void setRoutingRules(List<HttpRoutingRule> routingRules) {
        this.routingRules = routingRules;
        routingRuleMap = new HashMap<String, HttpRoutingRule>();
        for (HttpRoutingRule rule : routingRules) {
            routingRuleMap.put(rule.getServiceName(), rule);
        }
        logSettingRoutingRules(routingRules);
    }

    // TODO try to move out with AOP
    private void logSettingRoutingRules(List<HttpRoutingRule> routingRules) {
        System.out.println("========= init routing rules begin =========");
        for (HttpRoutingRule route : routingRules) {
            System.out.println("- " + route);
        }
        System.out.println("========= init routing rules end =========");
    }

    public String convertUrl(String url) {
        String targetUrl = url;
        return targetUrl;
    }
}
