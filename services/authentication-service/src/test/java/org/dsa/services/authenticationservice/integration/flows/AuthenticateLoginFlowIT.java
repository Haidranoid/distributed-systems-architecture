package org.dsa.services.authenticationservice.integration.flows;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dsa.core.sharedstarter.testing.integration.SpringBootIntegrationTest;
import org.dsa.services.authenticationservice.fixture.AuthenticationDtoFixtures;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

public class AuthenticateLoginFlowIT extends SpringBootIntegrationTest {

  @Autowired MockMvc mvc;
  @Autowired ObjectMapper objectMapper;

  // @Test
  void authenticateLoginFlow_shouldReturnTheAccountSession() throws Exception {
    var loginDto = AuthenticationDtoFixtures.loginWithUsernameAndPassword();

    mvc.perform(
            post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto)))
        .andExpect(status().isAccepted());
  }
}
