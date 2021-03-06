package agent;

import agent.domain.HttpDispatcher;
import agent.domain.InternalServiceAgent;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

class HttpDispatcherTest {

	private final HttpDispatcher dispatcher = new HttpDispatcher();
	{
		dispatcher.register(createInternalServiceAgent("service_register_001", "http://test001.com"));
		dispatcher.register(createInternalServiceAgent("service_register_002", "http://test002.com"));
	}

	private InternalServiceAgent createInternalServiceAgent(String name, String hostUrl) {
		InternalServiceAgent agent = new InternalServiceAgent();
		agent.setServiceName(name);
		agent.setServiceHostUrl(hostUrl);
		return agent;
	}

	@Test
	void test_register_fail_when_serviceName_null() {
		HttpDispatcher dispatcher = new HttpDispatcher();

		InternalServiceAgent agent = new InternalServiceAgent();
		agent.setServiceHostUrl("http://test.what");

		assertThrowsExactly(RuntimeException.class, () -> dispatcher.register(agent));
	}

	@Test
	void test_shouldDispatch_success() {
		MockHttpServletRequest request = mockRequest("GET", "/greet", "service_register_001");
		assertTrue(dispatcher.shouldDispatch(request));

		request = mockRequest("GET", "/hello", "service_register_002");
		assertTrue(dispatcher.shouldDispatch(request));
	}

	@Test
	void test_shouldDispatch_fail_without_invoke_header() {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/greet");
		assertFalse(dispatcher.shouldDispatch(request));
	}

	@Test
	void test_shouldDispatch_fail_without_agent_register() {
		MockHttpServletRequest request = mockRequest("POST", "/test", "service_never_register");
		assertFalse(dispatcher.shouldDispatch(request));
	}

	private MockHttpServletRequest mockRequest(String method, String requestURI, String invokeServer) {
		MockHttpServletRequest request = new MockHttpServletRequest(method, requestURI);
		request.addHeader(HttpDispatcher.INVOKE_HEADER, invokeServer);
		return request;
	}

}
