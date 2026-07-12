package org.dsa.services.authenticationservice.service;

import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dsa.core.sharedstarter.constants.Permission;
import org.dsa.core.sharedstarter.exception.InvalidCredentialsException;
import org.dsa.core.sharedstarter.messaging.events.AccountCreatedEvent;
import org.dsa.core.sharedstarter.messaging.events.UserLoggedInEvent;
import org.dsa.core.sharedstarter.messaging.producers.KafkaEventPublisher;
import org.dsa.core.sharedstarter.messaging.topics.KafkaTopics;
import org.dsa.services.authenticationservice.constants.TokenType;
import org.dsa.services.authenticationservice.entity.Token;
import org.dsa.services.authenticationservice.mapper.AuthenticationMapper;
import org.dsa.services.authenticationservice.properties.Endpoints;
import org.dsa.services.authenticationservice.repository.TokenRepository;
import org.dsa.services.authenticationservice.request.LoginRequest;
import org.dsa.services.authenticationservice.request.SignupRequest;
import org.dsa.services.authenticationservice.response.AccountResponse;
import org.dsa.services.authenticationservice.response.AuthenticationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {

  private final JwtSignerService jwtSignerService;
  private final RestTemplate restTemplate;
  private final Endpoints endpoints;
  private final TokenRepository tokenRepository;
  private final AuthenticationMapper authMapper;
  private final KafkaEventPublisher kafkaEventPublisher;

  public AuthenticationResponse login(LoginRequest loginRequest) {
    var accountAuthenticated =
        restTemplate.postForObject(
            endpoints.accountsServiceInternalEndpoint() + "/verify-credentials",
            loginRequest,
            AccountResponse.class);

    if (accountAuthenticated == null) {
      throw new InvalidCredentialsException();
    }

    var roles = List.of(accountAuthenticated.role().name());

    var scopes =
        accountAuthenticated.role().getPermissions().stream()
            .map(Permission::getPermission)
            .toList();

    Map<String, Object> claims = new HashMap<>();
    claims.put("roles", roles);
    claims.put("scope", String.join(" ", scopes));

    var accessToken = jwtSignerService.generateAccessToken(accountAuthenticated.username(), claims);

    var refreshToken = jwtSignerService.generateRefreshToken(accountAuthenticated.username());

    var authResponseDto =
        authMapper.toAuthResponseDto(accountAuthenticated, accessToken, refreshToken);

    kafkaEventPublisher.publishEvent(
        KafkaTopics.USER_LOGGED_IN,
        accountAuthenticated.id().toString(),
        UserLoggedInEvent.builder()
            .accountId(accountAuthenticated.id())
            .username(accountAuthenticated.username())
            .email(accountAuthenticated.email())
            .build());

    return authResponseDto;
  }

  public AuthenticationResponse signup(SignupRequest signupRequest) {
    var accountCreated =
        restTemplate.postForObject(
            endpoints.accountsServiceInternalEndpoint(), signupRequest, AccountResponse.class);

    if (accountCreated == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request body");
    }

    var accessToken =
        jwtSignerService.generateAccessToken(accountCreated.username(), new HashMap<>());
    var refreshToken = jwtSignerService.generateRefreshToken(accountCreated.username());

    saveAccountToken(accountCreated.id(), accessToken);

    var authResponseDto = authMapper.toAuthResponseDto(accountCreated, accessToken, refreshToken);

    kafkaEventPublisher.publishEvent(
        KafkaTopics.ACCOUNT_CREATED,
        accountCreated.id().toString(),
        AccountCreatedEvent.builder()
            .accountId(accountCreated.id())
            .username(accountCreated.username())
            .email(accountCreated.email())
            .build());

    return authResponseDto;
  }

  /*

  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

      final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
      if (authHeader == null || !authHeader.startsWith("Bearer ")) {
          return;
      }

      final String refreshToken = authHeader.split(" ")[1].trim();
      if (jwtService.isExpired(refreshToken)) {
          return;
      }

      final String username = jwtService.decode(refreshToken).getSubject();
      if (username == null) {
          return;
      }

      // var dbAccount = accountHttpClient.find(username);

      var account = restTemplate.getForObject(
              endpoints.accountServiceEndpoint() + "/" + username,
              AuthAccountDto.class
      );

      if (account == null) {
          return;
      }

      if (!(username.equals(account.username()))) {
          return;
      }

      var accessToken = jwtService.generateAccessToken(account.username());

      revokeAllAccountTokensById(account.id());
      saveAccountToken(account.id(), accessToken);

      var refreshTokenRequestResponse = AuthResponseDto.builder()
              .accessToken(accessToken)
              .refreshToken(refreshToken)
              .accountResponse(account)
              .build();

      new ObjectMapper().writeValue(response.getOutputStream(), refreshTokenRequestResponse);
  }

  */

  private void saveAccountToken(Long accountId, String jwtToken) {
    var token =
        Token.builder()
            .accountId(accountId)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();

    tokenRepository.save(token);
  }

  /*
  private void revokeAllAccountTokensById(Long accountId) {
      var validUserTokens = tokenRepository.findAllValidTokensByAccountId(accountId);

      if (validUserTokens.isEmpty()) {
          return;
      }

      validUserTokens.forEach(token -> {
          token.setExpired(true);
          token.setRevoked(true);
      });

      tokenRepository.saveAll(validUserTokens);
  }

   */
}
