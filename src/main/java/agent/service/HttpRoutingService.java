package agent.service;

import agent.domain.HttpRoutingRule;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@ConfigurationProperties(prefix = "route.manager")
public class HttpRoutingService {
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

    private void logSettingRoutingRules(List<HttpRoutingRule> routingRules) {
        // TODO try to move out as AOP

        System.out.println("========= init routing rules begin =========");
        for (HttpRoutingRule route : routingRules) {
            System.out.println("- " + route);
        }
        System.out.println("========= init routing rules end =========");
    }

    public String convertUrl(String uri) {
        String serviceName = parseServiceNameFromUri(uri);
        if (!routingRuleMap.containsKey(serviceName)) {
            return null;
        }
        String serviceHostUrl = routingRuleMap.get(serviceName).getServiceHostUrl();
        String tidyUrl = uri.replaceFirst("/" + serviceName, "");
        return serviceHostUrl + tidyUrl;
    }

    private String parseServiceNameFromUri(String uri) {
        return uri.split("[/]")[1];
    }
}
