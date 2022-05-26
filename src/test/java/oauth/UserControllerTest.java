package oauth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import oauth.controller.UserController;
import oauth.entity.vo.UserVO;
import oauth.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sample.dao.UserDaoImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Before
    public void setup() {
        UserService mockUserService = new UserService(new UserDaoImpl());

        mockMvc = MockMvcBuilders.standaloneSetup(new UserController(mockUserService)).build();
    }

    @Test
    public void test_getUserWithId_ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/user/1")).andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());

        String content = mvcResult.getResponse().getContentAsString();
        UserVO vo = jsonToObj(content, UserVO.class);
        assertNotNull(vo);
        assertEquals("Tom001", vo.getUserName());
    }

    @Test
    public void test_listAllUser_ok() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/user/all")).andReturn();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());

        String content = mvcResult.getResponse().getContentAsString();
        List<UserVO> vos = jsonToObjList(content, UserVO.class);
        assertNotNull(vos);
        assertEquals(5, vos.size());
    }

    private <T> T jsonToObj(String jsonStr, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readerFor(type).readValue(jsonStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private <T> List<T> jsonToObjList(String jsonStr, Class<T> type) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(ArrayList.class, type);
            return mapper.readValue(jsonStr, javaType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
