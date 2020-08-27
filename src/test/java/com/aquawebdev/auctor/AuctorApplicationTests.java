package com.aquawebdev.auctor;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuctorApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void signUp_getUserAttributeIsOk() throws Exception {
        mockMvc.perform(get("/signUp"))
                .andExpect(status().isOk())
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
    @WithMockUser(username = "u", password = "p", roles = {"USER", "ADMIN"})
    void securityTestAbility() throws Exception {
        mockMvc.perform(get("/signUp"))
                .andExpect(status().isForbidden());
    }

}
