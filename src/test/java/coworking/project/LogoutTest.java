package coworking.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.logout;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

@SpringBootTest
@AutoConfigureMockMvc
class LogoutTest {
    private final MockMvc mockMvc;

    @Autowired
    public LogoutTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void logoutTest() throws Exception {
        mockMvc.perform(logout().logoutUrl("/logout"))
                .andExpect(redirectedUrl("/"));
    }
}
