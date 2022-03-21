package samples.agent.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/proxy")
public class HelloController {

    private int cont = 0;

    @GetMapping("/test")
    public String greeting() {
        return "Hello, I am the test! [" + ++cont +"]";
    }

}
