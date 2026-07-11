package org.dsa.services.accountsservice.slice.web;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dsa.core.sharedstarter.common.exceptions.AccountNotFoundException;
import org.dsa.core.sharedstarter.testing.annotations.WebSliceEnvironment;
import org.dsa.services.accountsservice.common.fixtures.AccountDtoFixtures;
import org.dsa.services.accountsservice.controller.AccountsController;
import org.dsa.services.accountsservice.controller.advice.GlobalControllerAdvice;
import org.dsa.services.accountsservice.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

@WebSliceEnvironment(AccountsController.class)
@Import(GlobalControllerAdvice.class)
public class AccountsControllerTest {

  @Autowired private MockMvc mvc;
  @Autowired private ObjectMapper objectMapper;

  @MockitoBean private AccountService accountService;

  // @WithMockUser(Role = "ADMIN")
  @Test
  @DisplayName("GET /api/v1/accounts returns 200 when accounts list is not empty")
  public void getAllAccounts_whenAccountsIsNotEmpty_shouldReturn200() throws Exception {
    var accountsList = AccountDtoFixtures.twoAccountResponseDto();

    when(accountService.findAll()).thenReturn(accountsList);

    mvc.perform(get("/api/v1/accounts"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$", hasSize(2)));
  }

  @Test
  @DisplayName("GET /api/v1/accounts returns 200 when accounts list is empty")
  public void getAllAccounts_whenNoAccounts_shouldReturn200() throws Exception {
    var accountsEmptyList = AccountDtoFixtures.emptyAccountResponseDtoList();

    when(accountService.findAll()).thenReturn(accountsEmptyList);

    mvc.perform(get("/api/v1/accounts"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$").isArray())
        .andExpect(jsonPath("$", hasSize(0)));
  }

  @Test
  @DisplayName("GET /api/v1/accounts/{userId} returns 200 when account exists")
  public void getAccountById_whenAccountExists_shouldReturn200() throws Exception {
    // Arrange
    var accountDto = AccountDtoFixtures.adminAccountResponseDto(1L);

    when(accountService.findById(1L)).thenReturn(accountDto);

    // Act + Assert
    mvc.perform(get("/api/v1/accounts/{userId}", 1L)).andExpect(status().isOk());
  }

  @Test
  @DisplayName("GET /api/v1/accounts/{userId} returns 404 when account was not found")
  public void getAccountById_whenAccountDoesNotExist_shouldReturn404() throws Exception {
    var userId = 1L;
    when(accountService.findById(userId)).thenThrow(new AccountNotFoundException("id " + userId));

    mvc.perform(get("/api/v1/accounts/{userId}", userId))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Account not found: id " + userId))
        .andExpect(jsonPath("$.timestamp").exists());

    /*
     var result = mockMvcTester.get()
                    .uri("/api/v1/accounts/{userId}")
                    .contentType(MediaType.APPLICATION_JSON)
                    .exchange();

    assertThat(result).hasStatus(HttpStatus.INTERNAL_SERVER_ERROR);
    assertThat(result).bodyJson().extractingPath("$.message").isEqualTo("Account not found");
    assertThat(result).bodyJson().extractingPath("$.timestamp").isNotEmpty();
     */
  }

  @Test
  @DisplayName("PATCH /api/v1/accounts/{userId} returns 200 when account exists")
  public void updateAccount_whenAccountExists_shouldReturn200() throws Exception {
    var updateAccountDto = AccountDtoFixtures.updateAccountDtoOne();
    var updatedAccountDto = AccountDtoFixtures.updatedAccountDtoOne(1L);

    when(accountService.update(1L, updateAccountDto)).thenReturn(updatedAccountDto);

    mvc.perform(
            patch("/api/v1/accounts/{userId}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateAccountDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.username").value(updatedAccountDto.username()));
  }

  @Test
  @DisplayName("PATCH /api/v1/accounts/{userId} returns 404 when account was not found")
  public void updateAccount_whenAccountDoesNotExist_shouldReturn404() throws Exception {
    var userId = 1;
    var updateAccountDto = AccountDtoFixtures.updateAccountDtoOne();

    when(accountService.update(1L, updateAccountDto))
        .thenThrow(new AccountNotFoundException("id " + userId));

    mvc.perform(
            patch("/api/v1/accounts/{userId}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateAccountDto)))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Account not found: id " + userId))
        .andExpect(jsonPath("$.timestamp").exists());
  }

  @Test
  @DisplayName("DELETE /api/v1/accounts/{userId} returns 200 when account exists")
  public void deleteAccount_whenAccountExists_shouldReturn200() throws Exception {
    Mockito.doNothing().when(accountService).delete(1L);

    mvc.perform(delete("/api/v1/accounts/{userId}", 1L).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

  @Test
  @DisplayName("DELETE /api/v1/accounts/{userId} returns 404 when account was not found")
  public void deleteAccount_whenAccountDoesNotExist_shouldReturn404() throws Exception {
    var userId = 1L;

    Mockito.doThrow(new AccountNotFoundException("id " + userId))
        .when(accountService)
        .delete(userId);

    mvc.perform(delete("/api/v1/accounts/{userId}", userId).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.message").value("Account not found: id " + userId))
        .andExpect(jsonPath("$.timestamp").exists());
  }

  @Test
  @DisplayName("PATCH /api/v1/accounts/{userId}/change-password returns 200 when account exists")
  public void changePassword_whenAccountExists_shouldReturn200() throws Exception {
    var changePasswordDto = AccountDtoFixtures.changePasswordAccountDto();

    mvc.perform(
            patch("/api/v1/accounts/{userId}/change-password", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(changePasswordDto)))
        .andExpect(status().isNoContent());

    // verify(accountService)
    //       .changePassword(any());
  }
}
