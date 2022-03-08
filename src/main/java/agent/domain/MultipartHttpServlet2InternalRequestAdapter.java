package agent.domain;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class MultipartHttpServlet2InternalRequestAdapter extends HttpServlet2InternalRequestAbstractAdapter {

    public MultipartHttpServlet2InternalRequestAdapter(StandardMultipartHttpServletRequest request) {
        super(request);
    }

    @Override
    public Object getBody() throws IOException {
        MultiValueMap<String, Object> formatBody = new LinkedMultiValueMap<>();
        for (Map.Entry<String, String[]> paramEntity : request.getParameterMap().entrySet()) {
            formatBody.addAll(paramEntity.getKey(), Arrays.asList(paramEntity.getValue()));
        }
        return formatBody;
    }
}
