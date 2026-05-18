package org.dsa.services.authenticationservice.integration.security;

import org.dsa.services.core.servicesstarter.testing.integration.SpringBootIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class SecurityFilterChainIT extends SpringBootIntegrationTest {

    @Autowired
    MockMvc mvc;

    @Test
    void unauthenticatedUser_shouldReceive401() throws Exception {
        mvc.perform(get("/api/v1/auth/me"))
                .andExpect(status().isInternalServerError());
    }
}
