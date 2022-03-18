package agent.domain;

import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class SimpleHttpServlet2InternalRequestAdapter extends HttpServlet2InternalRequestAbstractAdapter {

    public SimpleHttpServlet2InternalRequestAdapter(HttpServletRequest request) {
        super(request);
    }

    @Override
    public Object getBody() throws IOException {
        return StreamUtils.copyToByteArray(request.getInputStream());
    }
}
