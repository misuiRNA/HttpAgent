package agent;

import agent.domain.HttpDispatcher;
import agent.domain.InternalServiceAgent;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HttpDispatcherTest {

	private static final String INVOKE_HEADER = "Target-Service";

	private final HttpDispatcher dispatcher = new HttpDispatcher();
	{
		dispatcher.register("service_register_001", createInternalServiceAgent("service_register_001", "http://test001.com"));
		dispatcher.register("service_register_002", createInternalServiceAgent("service_register_002", "http://test002.com"));
	}

	private InternalServiceAgent createInternalServiceAgent(String name, String hostUrl) {
		InternalServiceAgent agent = new InternalServiceAgent();
		agent.setServiceName(name);
		agent.setServiceHostUrl(hostUrl);
		return agent;
	}

	@Test
	void testShouldDispatch_success() {
		MockHttpServletRequest request = mockRequest("GET", "/greet", "service_register_001");
		assertTrue(dispatcher.shouldDispatch(request));

		request = mockRequest("GET", "/greet", "service_register_002");
		assertTrue(dispatcher.shouldDispatch(request));
	}

	@Test
	void testShouldDispatch_fail_without_invoke_header() {
		MockHttpServletRequest request = new MockHttpServletRequest("GET", "/greet");
		assertFalse(dispatcher.shouldDispatch(request));
	}

	@Test
	void testShouldDispatch_fail_without_agent_register() {
		MockHttpServletRequest request = mockRequest("GET", "/greet", "service_never_register");
		assertFalse(dispatcher.shouldDispatch(request));
	}

	private MockHttpServletRequest mockRequest(String method, String requestURI, String invokeServer) {
		MockHttpServletRequest request = new MockHttpServletRequest(method, requestURI);
		request.addHeader(INVOKE_HEADER, invokeServer);
		return request;
	}

}
