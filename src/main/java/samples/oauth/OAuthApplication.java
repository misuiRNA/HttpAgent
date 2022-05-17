package samples.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"oauth", "io", "samples.oauth"})
public class OAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuthApplication.class, args);
    }

}
