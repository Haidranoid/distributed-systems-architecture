package org.dsa.services.authenticationservice.fixture;

import org.dsa.core.sharedstarter.constants.Role;
import org.dsa.services.authenticationservice.constants.TokenType;
import org.dsa.services.authenticationservice.entity.Token;
import org.dsa.services.authenticationservice.request.LoginRequest;
import org.dsa.services.authenticationservice.request.SignupRequest;
import org.dsa.services.authenticationservice.response.AccountResponse;
import org.dsa.services.authenticationservice.response.AuthenticationResponse;

public class AuthenticationDtoFixtures {

  public static AccountResponse authAccountDto(Long id) {
    return AccountResponse.builder()
        .id(id)
        .username("admin")
        .email("admin@email.com")
        .firstName("Steve")
        .lastName("Rogers")
        .role(Role.ADMIN)
        .build();
  }

  public static AccountResponse loginAuthAccountDto(Long id) {
    return AccountResponse.builder()
        .id(id)
        .username("admin")
        .email("admin@email.com")
        .firstName("Steve")
        .lastName("Rogers")
        .role(Role.ADMIN)
        .build();
  }

  public static AccountResponse signupAuthAccountDto(Long id) {
    return AccountResponse.builder()
        .id(id)
        .username("manager")
        .firstName("Pedro")
        .lastName("Pascal")
        .email("admin@email.com")
        .role(Role.MANAGER)
        .build();
  }

  public static Token signupTokenPersisted(Long accountId, String jwtToken) {
    return Token.builder()
        .accountId(accountId)
        .token(jwtToken)
        .tokenType(TokenType.BEARER)
        .expired(false)
        .revoked(false)
        .build();
  }

  public static LoginRequest loginWithUsernameAndPassword() {
    return LoginRequest.builder().username("admin").password("<PASSWORD>").build();
  }

  public static SignupRequest managerSignupDto() {
    return SignupRequest.builder()
        .username("manager")
        .firstName("Pedro")
        .lastName("Pascal")
        .email("manager@email.com")
        .password("<PASSWORD>")
        .role(Role.MANAGER)
        .build();
  }

  // TODO: improve it with taker
  public static String randomAccessToken() {
    return "access-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj";
  }

  // TODO: improve it with taker
  public static String randomRefreshToken() {
    return "refresh-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj";
  }

  public static AuthenticationResponse loginAuthResponseDto(Long id) {
    return AuthenticationResponse.builder()
        .accessToken("access-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj")
        .refreshToken("refresh-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj")
        .accountResponse(
            AccountResponse.builder()
                .id(id)
                .username("admin")
                .email("admin@email.com")
                .firstName("Steve")
                .lastName("Rogers")
                .role(Role.ADMIN)
                .build())
        .build();
  }

  public static AuthenticationResponse signupAuthResponseDto(Long id) {
    return AuthenticationResponse.builder()
        .accessToken("access-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj")
        .refreshToken("refresh-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj")
        .accountResponse(
            AccountResponse.builder()
                .id(id)
                .username("manager")
                .email("manager@email.com")
                .firstName("Pedro")
                .lastName("Pascal")
                .role(Role.MANAGER)
                .build())
        .build();
  }
}
