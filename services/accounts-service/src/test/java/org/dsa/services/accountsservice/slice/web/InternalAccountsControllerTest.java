package org.dsa.services.accountsservice.slice.web;

import org.dsa.services.accountsservice.controllers.GlobalControllerAdvice;
import org.dsa.services.accountsservice.controllers.InternalAccountsController;
import org.dsa.services.accountsservice.common.fixtures.AccountDtoFixtures;
import org.dsa.services.accountsservice.services.impl.AccountServiceImpl;
import org.dsa.core.sharedstarter.testing.annotations.WebSliceEnvironment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebSliceEnvironment(InternalAccountsController.class)
@Import(GlobalControllerAdvice.class)
public class InternalAccountsControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private AccountServiceImpl accountService;

    @Test
    @DisplayName("POST /api/v1/accounts returns 200 when account is created")
    public void createAccount_whenAccountIsCreated_shouldReturn200() throws Exception {
        var createAccountDto = AccountDtoFixtures.createAdminAccountDto();
        var accountCreated = AccountDtoFixtures.adminAccountResponseDto(1L);

        when(accountService.create(createAccountDto))
                .thenReturn(accountCreated);

        mvc.perform(
                        post("/api/v1/internal/accounts")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(createAccountDto))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value(createAccountDto.username()))
                .andExpect(jsonPath("$.email").value(createAccountDto.email()));
    }

    @Test
    @DisplayName("POST /api/v1/accounts/authenticate-login returns 200 when credentials are valid")
    public void authenticateLogin_whenCredentialsAreValid_shouldReturn200() throws Exception {
        var authenticateAccountDto = AccountDtoFixtures.authenticateAccountDto();
        var accountAuthenticated = AccountDtoFixtures.adminAccountResponseDto(1L);

        when(accountService.verifyCredentials(authenticateAccountDto))
                .thenReturn(accountAuthenticated);

        mvc.perform(
                        post("/api/v1/internal/accounts/authenticate-login")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(authenticateAccountDto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.username").value(authenticateAccountDto.username()));
    }
}
