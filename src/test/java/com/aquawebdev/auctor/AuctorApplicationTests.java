package com.aquawebdev.auctor;

import com.aquawebdev.auctor.entity.User;
import com.aquawebdev.auctor.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuctorApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserRepository userRepository;

    @Test
    void signUp_getView() throws Exception {
        mockMvc.perform(get("/signUp"))
                .andExpect(status().isOk())
                .andExpect(view().name("signUp"))
                .andExpect(model().attribute("user", allOf(
                        hasProperty("userId", is(0L)),
                        hasProperty("login", nullValue()),
                        hasProperty("name", nullValue()),
                        hasProperty("surname", nullValue()),
                        hasProperty("email", nullValue()),
                        hasProperty("password", nullValue()),
                        hasProperty("photo", nullValue()),
                        hasProperty("news", nullValue()),
                        hasProperty("articles", nullValue()),
                        hasProperty("roles", nullValue())
                        )
                ));
    }

    @Test
    void signIn_getView() throws Exception {
        mockMvc.perform(get("/signIn"))
                .andExpect(status().isOk())
                .andExpect(view().name("signIn"));
    }

    @Test
    @WithMockUser
    void signUp_isForbidden() throws Exception {
        mockMvc.perform(get("/signUp"))
                .andExpect(status().isForbidden());
    }

    @Test
    void signIn_isFound() throws Exception {
        String username = "test username";
        String password = "test password";

        User user = new User();
        user.setLogin(username);
        user.setPassword(passwordEncoder.encode(password));

        when(userRepository.findByLogin(username)).thenReturn(Optional.of(user));

        mockMvc.perform(post("/signIn")
                .param("username", username)
                .param("password", password))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }
}
