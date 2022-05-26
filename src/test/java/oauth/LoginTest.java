package oauth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import oauth.controller.LoginController;
import oauth.entity.dto.UserInfo;
import oauth.entity.request.LoginForm;
import oauth.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import sample.OAuthApplication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = LoginController.class)
@ContextConfiguration(classes = OAuthApplication.class)
public class LoginTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService mockUserService;

    @Before
    public void setup() {
        UserInfo mockUser = new UserInfo();
        mockUser.setUserId(0);
        mockUser.setUserName("loginTest");
        mockUser.setPassword("123456");

        when(mockUserService.getUserByName("loginTest")).thenReturn(mockUser);
    }

    @Test
    public void test_login_ok() throws Exception {
        LoginForm loginForm = new LoginForm();
        loginForm.setName("loginTest");
        loginForm.setPassword("123456");
        String content = objToJson(loginForm);

        MvcResult result = mockMvc.perform(
                post("/login").content(content).contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
    }

    @Test
    public void test_login_failed_with_incorrectPassword() throws Exception {
        LoginForm loginForm = new LoginForm();
        loginForm.setName("loginTest");
        loginForm.setPassword("incorrect_password");
        String content = objToJson(loginForm);

        MvcResult result = mockMvc.perform(
                post("/login").content(content).contentType(MediaType.APPLICATION_JSON)
        ).andReturn();

        assertEquals(HttpStatus.FORBIDDEN.value(), result.getResponse().getStatus());
    }

    private String objToJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
