package org.dsa.services.authenticationservice.unit.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dsa.core.sharedstarter.common.constants.Permission;
import org.dsa.core.sharedstarter.common.exceptions.InvalidCredentialsException;
import org.dsa.core.sharedstarter.messaging.events.AccountCreatedEvent;
import org.dsa.core.sharedstarter.messaging.events.UserLoggedInEvent;
import org.dsa.core.sharedstarter.messaging.producers.KafkaEventPublisher;
import org.dsa.core.sharedstarter.messaging.topics.KafkaTopics;
import org.dsa.services.authenticationservice.constant.TokenType;
import org.dsa.services.authenticationservice.dto.AuthAccountDto;
import org.dsa.services.authenticationservice.fixture.AuthenticationDtoFixtures;
import org.dsa.services.authenticationservice.mapper.AuthenticationMapper;
import org.dsa.services.authenticationservice.property.Endpoints;
import org.dsa.services.authenticationservice.repository.TokenRepository;
import org.dsa.services.authenticationservice.service.AuthenticationService;
import org.dsa.services.authenticationservice.util.JwtSignerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

  @Mock private JwtSignerService jwtSignerService;
  @Mock private RestTemplate restTemplate;
  @Mock private Endpoints endpoints;
  @Mock private TokenRepository tokenRepository;
  @Mock private AuthenticationMapper authMapper;
  @Mock private KafkaEventPublisher kafkaEventPublisher;

  @InjectMocks private AuthenticationService authenticationService;

  @Test
  void login_whenCredentialsAreValid_shouldReturnAuthResponseDto() {
    var loginDto = AuthenticationDtoFixtures.loginWithUsernameAndPassword();
    var accountAuthenticated = AuthenticationDtoFixtures.loginAuthAccountDto(1L);
    var accessToken = AuthenticationDtoFixtures.randomAccessToken();
    var refreshToken = AuthenticationDtoFixtures.randomRefreshToken();
    var authResponseDto = AuthenticationDtoFixtures.loginAuthResponseDto(1L);

    var roles = List.of(accountAuthenticated.role().name());
    var scopes =
        accountAuthenticated.role().getPermissions().stream()
            .map(Permission::getPermission)
            .toList();

    Map<String, Object> claims = new HashMap<>();
    claims.put("roles", roles);
    claims.put("scope", String.join(" ", scopes));

    when(restTemplate.postForObject(
            endpoints.accountsServiceInternalEndpoint() + "/authenticate-login",
            loginDto,
            AuthAccountDto.class))
        .thenReturn(accountAuthenticated);

    when(jwtSignerService.generateAccessToken(accountAuthenticated.username(), claims))
        .thenReturn(accessToken);

    when(jwtSignerService.generateRefreshToken(accountAuthenticated.username()))
        .thenReturn(refreshToken);

    when(authMapper.toAuthResponseDto(accountAuthenticated, accessToken, refreshToken))
        .thenReturn(authResponseDto);

    Mockito.doNothing()
        .when(kafkaEventPublisher)
        .publishEvent(
            KafkaTopics.USER_LOGGED_IN,
            accountAuthenticated.id().toString(),
            UserLoggedInEvent.builder()
                .accountId(accountAuthenticated.id())
                .username(accountAuthenticated.username())
                .email(accountAuthenticated.email())
                .build());

    var session = authenticationService.login(loginDto);

    assertEquals(accessToken, session.accessToken());
    assertEquals(refreshToken, session.refreshToken());
    assertEquals(loginDto.username(), session.account().username());
  }

  @Test
  void login_whenCredentialsAreInvalid_shouldThrowException() {
    var loginDto = AuthenticationDtoFixtures.loginWithUsernameAndPassword();

    when(restTemplate.postForObject(
            endpoints.accountsServiceInternalEndpoint() + "/authenticate-login",
            loginDto,
            AuthAccountDto.class))
        .thenThrow(
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid username or password"));

    var exception =
        assertThrows(ResponseStatusException.class, () -> authenticationService.login(loginDto));

    assertEquals("400 BAD_REQUEST \"Invalid username or password\"", exception.getMessage());
  }

  @Test
  void login_whenAuthenticationReturnsNullValue_shouldThrowException() {
    var loginDto = AuthenticationDtoFixtures.loginWithUsernameAndPassword();

    when(restTemplate.postForObject(
            endpoints.accountsServiceInternalEndpoint() + "/authenticate-login",
            loginDto,
            AuthAccountDto.class))
        .thenReturn(null);

    var exception =
        assertThrows(
            InvalidCredentialsException.class, () -> authenticationService.login(loginDto));

    assertEquals("Invalid credentials", exception.getMessage());
  }

  @Test
  void signup_whenUserIsNotDuplicated_shouldReturnAuthResponseDto() {
    var signupDto = AuthenticationDtoFixtures.managerSignupDto();
    var accountCreated = AuthenticationDtoFixtures.signupAuthAccountDto(1L);
    var accessToken = AuthenticationDtoFixtures.randomAccessToken();
    var refreshToken = AuthenticationDtoFixtures.randomRefreshToken();
    var authResponseDto = AuthenticationDtoFixtures.signupAuthResponseDto(1L);

    when(restTemplate.postForObject(
            endpoints.accountsServiceInternalEndpoint(), signupDto, AuthAccountDto.class))
        .thenReturn(accountCreated);

    when(jwtSignerService.generateAccessToken(accountCreated.username(), new HashMap<>()))
        .thenReturn(accessToken);

    when(jwtSignerService.generateRefreshToken(accountCreated.username())).thenReturn(refreshToken);

    when(authMapper.toAuthResponseDto(accountCreated, accessToken, refreshToken))
        .thenReturn(authResponseDto);

    // TODO: verify method params
    Mockito.doNothing()
        .when(kafkaEventPublisher)
        .publishEvent(
            KafkaTopics.ACCOUNT_CREATED,
            accountCreated.id().toString(),
            AccountCreatedEvent.builder()
                .accountId(accountCreated.id())
                .username(accountCreated.username())
                .email(accountCreated.email())
                .build());

    var authResponse = authenticationService.signup(signupDto);

    assertEquals(accessToken, authResponse.accessToken());
    assertEquals(refreshToken, authResponse.refreshToken());
    assertEquals(signupDto.username(), authResponse.account().username());
  }

  @Test
  void signup_whenUserIsNotDuplicated_shouldSaveAccountToken() {
    var signupDto = AuthenticationDtoFixtures.managerSignupDto();
    var accountCreated = AuthenticationDtoFixtures.signupAuthAccountDto(1L);
    var accessToken = AuthenticationDtoFixtures.randomAccessToken();
    var refreshToken = AuthenticationDtoFixtures.randomRefreshToken();
    var authResponseDto = AuthenticationDtoFixtures.signupAuthResponseDto(1L);

    when(restTemplate.postForObject(
            endpoints.accountsServiceInternalEndpoint(), signupDto, AuthAccountDto.class))
        .thenReturn(accountCreated);

    when(jwtSignerService.generateAccessToken(accountCreated.username(), new HashMap<>()))
        .thenReturn(accessToken);

    when(jwtSignerService.generateRefreshToken(accountCreated.username())).thenReturn(refreshToken);

    when(authMapper.toAuthResponseDto(accountCreated, accessToken, refreshToken))
        .thenReturn(authResponseDto);

    // TODO: verify method params
    Mockito.doNothing()
        .when(kafkaEventPublisher)
        .publishEvent(
            KafkaTopics.ACCOUNT_CREATED,
            accountCreated.id().toString(),
            AccountCreatedEvent.builder()
                .accountId(accountCreated.id())
                .username(accountCreated.username())
                .email(accountCreated.email())
                .build());

    authenticationService.signup(signupDto);

    verify(tokenRepository)
        .save(
            argThat(
                tokenEntity ->
                    tokenEntity.getAccountId().equals(accountCreated.id())
                        && tokenEntity.getToken().equals(accessToken)
                        && tokenEntity.getTokenType().equals(TokenType.BEARER)
                        && !tokenEntity.isExpired()
                        && !tokenEntity.isRevoked()));
  }

  @Test
  void signup_whenUserIsDuplicated_shouldThrowException() {
    var signupDto = AuthenticationDtoFixtures.managerSignupDto();

    when(restTemplate.postForObject(
            endpoints.accountsServiceInternalEndpoint(), signupDto, AuthAccountDto.class))
        .thenThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request body"));

    var exception =
        assertThrows(ResponseStatusException.class, () -> authenticationService.signup(signupDto));

    assertEquals("400 BAD_REQUEST \"Invalid request body\"", exception.getMessage());
  }

  @Test
  void signup_whenAccountVerificationReturnsNullValue_shouldThrowException() {
    var signupDto = AuthenticationDtoFixtures.managerSignupDto();

    when(restTemplate.postForObject(
            endpoints.accountsServiceInternalEndpoint(), signupDto, AuthAccountDto.class))
        .thenReturn(null);

    var exception =
        assertThrows(ResponseStatusException.class, () -> authenticationService.signup(signupDto));

    assertEquals("400 BAD_REQUEST \"Invalid request body\"", exception.getMessage());
  }

  /*
  @Test
  void refreshToken_whenRefreshTokenIsValid_shouldReturnAuthResponseDto() {
      //TODO
  }

  @Test
  void refreshToken_whenRefreshTokenIsValid_shouldRevokePreviousTokens() {
      //TODO
  }

  @Test
  void refreshToken_whenRefreshTokenIsExpired_shouldDoNothing() {
      //TODO
  }
  */
}
