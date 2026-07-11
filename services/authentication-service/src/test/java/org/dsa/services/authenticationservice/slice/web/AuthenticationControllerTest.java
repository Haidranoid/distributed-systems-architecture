package org.dsa.services.authenticationservice.slice.web;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.dsa.core.sharedstarter.common.constants.Role;
import org.dsa.core.sharedstarter.common.exceptions.InvalidCredentialsException;
import org.dsa.core.sharedstarter.testing.annotations.WebSliceEnvironment;
import org.dsa.services.authenticationservice.controller.AuthenticationController;
import org.dsa.services.authenticationservice.controller.advice.GlobalControllerAdvice;
import org.dsa.services.authenticationservice.fixture.AuthenticationDtoFixtures;
import org.dsa.services.authenticationservice.service.AuthenticationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

@WebSliceEnvironment(AuthenticationController.class)
@Import(GlobalControllerAdvice.class)
public class AuthenticationControllerTest {

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @MockitoBean private AuthenticationService authenticationService;

  @Test
  @DisplayName("POST /api/v1/auth/login returns 200 when credentials are valid")
  public void login_whenCredentialsAreValid_shouldReturn202() throws Exception {
    var loginDto = AuthenticationDtoFixtures.loginWithUsernameAndPassword();
    var accountLoggedIn = AuthenticationDtoFixtures.loginAuthResponseDto(1L);

    when(authenticationService.login(loginDto)).thenReturn(accountLoggedIn);

    mockMvc
        .perform(
            post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto)))
        .andExpect(status().isAccepted())
        .andExpect(
            jsonPath("$.accessToken")
                .value("access-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj"))
        .andExpect(
            jsonPath("$.refreshToken")
                .value("refresh-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj"))
        .andExpect(jsonPath("$.account.id").value(1L))
        .andExpect(jsonPath("$.account.email").value("admin@email.com"))
        .andExpect(jsonPath("$.account.role").value(Role.ADMIN.name()));
  }

  @Test
  @DisplayName("POST /api/v1/auth/login returns 400 when credentials are invalid")
  void login_whenCredentialsAreInvalid_shouldReturn400() throws Exception {
    var loginDto = AuthenticationDtoFixtures.loginWithUsernameAndPassword();

    when(authenticationService.login(loginDto)).thenThrow(new InvalidCredentialsException());

    mockMvc
        .perform(
            post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDto)))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("POST /api/v1/auth/signup returns 201 when account was created successfully")
  public void signup_whenAccountWasCreated_shouldReturn200() throws Exception {
    var signupDto = AuthenticationDtoFixtures.managerSignupDto();
    var accountCreated = AuthenticationDtoFixtures.signupAuthResponseDto(1L);

    when(authenticationService.signup(signupDto)).thenReturn(accountCreated);

    mockMvc
        .perform(
            post("/api/v1/auth/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(signupDto)))
        .andExpect(status().isCreated())
        .andExpect(
            jsonPath("$.accessToken")
                .value("access-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj"))
        .andExpect(
            jsonPath("$.refreshToken")
                .value("refresh-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj"))
        .andExpect(jsonPath("$.account.id").value(1L))
        .andExpect(jsonPath("$.account.email").value("manager@email.com"))
        .andExpect(jsonPath("$.account.role").value(Role.MANAGER.name()));
  }
}
