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
class SignControllerTests {

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
                        hasProperty("login", nullValue()),
                        hasProperty("name", nullValue()),
                        hasProperty("surname", nullValue()),
                        hasProperty("email", nullValue()),
                        hasProperty("password", nullValue()),
                        hasProperty("photo", nullValue())
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

    @Test
    void signUp_isOk() throws Exception {
        String login = "test login";
        String password = "Test password";
        String email = "test@email.com";
        String username = "test username";

        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setName(username);

        when(userRepository.findByLogin(username)).thenReturn(Optional.of(user));

        mockMvc.perform(post("/signUp")
                .param("username",username)
                .param("password",password)
                .param("login",login)
                .param("email",email))
                .andExpect(status().isOk())
                .andExpect(view().name("signIn"));
    }

    @Test
    void signUp_notFound() throws Exception {
        String login = "test login";
        String password = "test password";
        String email = "";
        String username = "test username";

        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user.setName(username);

        when(userRepository.findByLogin(username)).thenReturn(Optional.of(user));

        mockMvc.perform(post("/signUp")
                .param("username",username)
                .param("password",password)
                .param("login",login)
                .param("email",email))
                .andExpect(status().isOk())
                .andExpect(view().name("signUp"));
    }

    @Test
    void signIn_resetPassword() throws Exception {
        mockMvc.perform(get("/resetPassword"))
                .andExpect(status().isOk())
                .andExpect(view().name("resetPassword"));
    }

    @Test
    void signUp_IsPresent() throws Exception {
        String login = "test login";
        String password = "test password";
        String email = "test email";
        String username = "test username";

        when(userRepository.findByLogin(login)).thenReturn(Optional.of(new User()));

        mockMvc.perform(post("/signUp")
                .param("username",username)
                .param("password",password)
                .param("login",login)
                .param("email",email))
                .andExpect(status().isOk())
                .andExpect(view().name("signUp"));
    }
}
