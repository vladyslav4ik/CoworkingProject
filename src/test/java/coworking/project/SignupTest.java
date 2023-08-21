package coworking.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class SignupTest {
    private final MockMvc mockMvc;

    @Autowired
    public SignupTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void correctSignupRedirectionTest() throws Exception {
        mockMvc.perform(post("/signup")
                .param("username", "new user")
                .param("yearOfBirth", "2000")
                .param("email", "test@email")
                .param("password", "password")
                .with(csrf()))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login"));
    }

    @Test
    void emptyUsernameFieldTest() throws Exception {
        doRequest("", "2000", "test@email", "12345678");
    }

    @Test
    void emptyYearOfBirthFieldTest() throws Exception {
        doRequest("new user", "", "test@email", "12345678");
    }

    @Test
    void emptyMailFieldTest() throws Exception {
        doRequest("new user", "2000", "", "12345678");
    }

    @Test
    void emptyPasswordFieldTest() throws Exception {
        doRequest("new user", "2000", "test@email", "");
    }

    @Test
    void shortUsernameTest() throws Exception {
        doRequest("n", "2000", "test@email", "12345678");
    }

    @Test
    void shortPasswordTest() throws Exception {
        doRequest("new user", "2000", "test@email", "1234567");
    }

    @Test
    void notEmailTest() throws Exception {
        doRequest("new user", "2000", "testemail", "12345678");
    }

    @Test
    void incorrectYearOfBirthTest() throws Exception {
        doRequest("new user", "1800", "test@email", "12345678");
    }

    private void doRequest(String username, String yearOfBirth, String email, String password) throws Exception {
        mockMvc.perform(post("/signup")
                .param("username", username)
                .param("yearOfBirth", yearOfBirth)
                .param("email", email)
                .param("password", password)
                .with(csrf()))
                .andDo(print())
                .andExpect(view().name("auth/signup"));
    }
}