package org.dsa.services.accountsservice.integration.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dsa.core.sharedstarter.testing.integration.SpringBootIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

class SecurityFilterChainIT extends SpringBootIntegrationTest {

  @Autowired MockMvc mvc;

  @Test
  void unauthenticatedUser_shouldReceive401() throws Exception {
    mvc.perform(get("/api/v1/internal/accounts")).andExpect(status().isInternalServerError());
  }
}
