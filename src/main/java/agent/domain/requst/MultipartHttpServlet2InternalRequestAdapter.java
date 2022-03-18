package agent.domain.requst;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
        for (Map.Entry<String, List<MultipartFile>> entry : req.getMultiFileMap().entrySet()) {
            List<ByteArrayResource> resources = new ArrayList<>();
            for (MultipartFile file : entry.getValue()) {
                ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes()) {
                    @Override
                    public String getFilename() {
                        return file.getOriginalFilename();
                    }

                    @Override
                    public long contentLength() {
                        return file.getSize();
                    }
                };
                resources.add(fileAsResource);
            }
            formatBody.addAll(entry.getKey(), resources);
        }
        return formatBody;
    }
}
