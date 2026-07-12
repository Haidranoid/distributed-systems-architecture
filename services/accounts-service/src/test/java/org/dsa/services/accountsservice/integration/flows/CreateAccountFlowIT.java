package org.dsa.services.accountsservice.integration.flows;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dsa.core.sharedstarter.testing.integration.SpringBootIntegrationTest;
import org.dsa.services.accountsservice.fixture.AccountDtoFixtures;
import org.dsa.services.accountsservice.response.AccountResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

public class CreateAccountFlowIT extends SpringBootIntegrationTest {

  @Autowired private MockMvc mvc;
  @Autowired private ObjectMapper objectMapper;

  @Test
  void createAccountFlow_shouldPersistAndReturnAccount() throws Exception {
    // creates account --------------------------------------------------
    var createAccountDto = AccountDtoFixtures.createAdminAccountDto();

    var response =
        mvc.perform(
                post("/api/v1/internal/accounts")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(createAccountDto)))
            .andExpect(status().isCreated())
            .andReturn();

    // extract response body
    var responseBody = response.getResponse().getContentAsString();

    // serialize body json to dto
    var createdAccountDto = objectMapper.readValue(responseBody, AccountResponse.class);

    // get the account created --------------------------------------------
    mvc.perform(get("/api/v1/accounts/{userId}", createdAccountDto.id()))
        .andExpect(status().isOk());
  }
}
