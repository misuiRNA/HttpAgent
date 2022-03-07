package agent.service;

import agent.domain.HttpRoutingRule;
import agent.domain.HttpServletRequestRoutingAdapter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
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

    public boolean needRoute(HttpServletRequest request) {
        HttpRoutingRule rule = findRoutingRule(request);
        return null != rule;
    }

    public ResponseEntity<String> route(HttpServletRequestRoutingAdapter req) {
        String url = req.getTotalUrl();
        HttpMethod mtd = req.getMethod();
        HttpEntity<Object> entity = req.getEntity();

        ResponseEntity<String> returnValue;
        try {
            returnValue = new RestTemplate().exchange(url, mtd, entity, String.class);
        } catch (HttpStatusCodeException e) {
            returnValue = new ResponseEntity<String>(e.getResponseBodyAsString(), e.getStatusCode());
            e.printStackTrace();
        } catch (ResourceAccessException e) {
            returnValue = new ResponseEntity<String>("Connection refused! Can't find service(s)", HttpStatus.BAD_GATEWAY);
            e.printStackTrace();
        } catch (RestClientException e) {
            returnValue = new ResponseEntity<String>("Unknown Error!", HttpStatus.INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
        return returnValue;
    }

    public HttpRoutingRule findRoutingRule(HttpServletRequest request) {
        String uri = request.getRequestURI();
        String serviceName = uri.split("[/]")[1];
        return routingRuleMap.get(serviceName);
    }

    private void logSettingRoutingRules(List<HttpRoutingRule> routingRules) {
        // TODO try to move out as AOP

        System.out.println("========= init routing rules begin =========");
        for (HttpRoutingRule route : routingRules) {
            System.out.println("- " + route);
        }
        System.out.println("========= init routing rules end =========");
    }

}
