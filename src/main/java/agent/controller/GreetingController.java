package agent.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import agent.service.HttpDispatchService;
import agent.domain.InternalServiceAgent;

import java.util.List;

@RestController
public class GreetingController {

    @Autowired
    private HttpDispatchService manager;

    @GetMapping(path = "/hello")
    public String sayHello() {
        return "Hello, I am SpringBoot!";
    }

    @GetMapping(path = "/routes")
    public String getRoute() {
        List<InternalServiceAgent> routingRules = manager.getInterServices();

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
