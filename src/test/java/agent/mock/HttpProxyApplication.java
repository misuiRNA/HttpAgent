package agent.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"agent", "agent.mock"})    // TODO 研究搜索逻辑的工作方式，为什么可以这样配置
public class HttpProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(HttpProxyApplication.class, args);
	}

}
