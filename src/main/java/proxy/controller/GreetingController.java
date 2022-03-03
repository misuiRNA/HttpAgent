package proxy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import proxy.configuration.HttpRoutingManager;
import proxy.configuration.HttpRoutingRule;

import java.util.List;

@RestController
public class GreetingController {

    @Autowired
    private HttpRoutingManager manager;

    @GetMapping(path = "hello")
    public String sayHello() {
        return "Hello, I am SpringBoot!";
    }

    @GetMapping(path = "route")
    public String getRoute() {
        List<HttpRoutingRule> routingRules = manager.getRoutingRules();

        StringBuilder res = new StringBuilder();
        res.append(String.format("rule num=%d", routingRules.size()));
        for (Object route : routingRules) {
            res.append("<br>");
            res.append("- ");
            res.append(route.toString());
        }
        return res.toString();
    }

}
