package agent;

import agent.domain.HttpDispatcher;
import agent.mock.HttpProxyApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HttpProxyApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpProxyTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void test_localResponse_should_ok_without_invokeHeader() {
        ResponseEntity<String> result = testRestTemplate.exchange("/proxy/test", HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Hello, I am the test! [1]", result.getBody());
    }

    @Test
    public void test_localResponse_should_notFound_without_invokeHeader() {
        ResponseEntity<String> result = testRestTemplate.exchange("/proxy/no_such_path", HttpMethod.GET, null, String.class);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    public void test_agentResponse_should_badGateway_with_realServiceStopped() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpDispatcher.INVOKE_HEADER, "flask");
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> result = testRestTemplate.exchange("/greet", HttpMethod.GET, requestEntity, String.class);
        assertEquals(HttpStatus.BAD_GATEWAY, result.getStatusCode());
    }
    @Test
    public void test_localResponse_should_notFound_with_invalidInvokeHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpDispatcher.INVOKE_HEADER, "noServer");
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> result = testRestTemplate.exchange("/greet", HttpMethod.GET, requestEntity, String.class);
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

}
