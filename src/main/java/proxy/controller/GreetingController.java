package proxy.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import proxy.configuration.HttpRouteConfig;

@RestController
public class GreetingController {

    @Autowired
    private HttpRouteConfig routes;

    @GetMapping(path = "hello")
    public String sayHello() {
        return "Hello, I am SpringBoot!";
    }

    @GetMapping(path = "route")
    public String getRoute() {
        return routes.toString();
    }

}
