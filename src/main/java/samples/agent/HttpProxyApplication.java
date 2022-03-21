package samples.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"agent", "samples.agent"})
public class HttpProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpProxyApplication.class, args);
	}

}
