package agent.domain;

import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HttpServlet2InternalRequestAdapter extends HttpServlet2InternalRequestAbstractAdapter {

    public HttpServlet2InternalRequestAdapter(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Object getBody() throws IOException {
        return StreamUtils.copyToByteArray(request.getInputStream());
    }
}
