package org.dsa.services.authenticationservice.fixtures;

import org.dsa.core.sharedstarter.common.constants.Role;
import org.dsa.services.authenticationservice.constant.TokenType;
import org.dsa.services.authenticationservice.dto.AuthAccountDto;
import org.dsa.services.authenticationservice.dto.AuthResponseDto;
import org.dsa.services.authenticationservice.dto.LoginDto;
import org.dsa.services.authenticationservice.dto.SignupDto;
import org.dsa.services.authenticationservice.entity.Token;

public class AuthenticationDtoFixtures {

  public static AuthAccountDto authAccountDto(Long id) {
    return AuthAccountDto.builder()
        .id(id)
        .username("admin")
        .email("admin@email.com")
        .firstName("Steve")
        .lastName("Rogers")
        .role(Role.ADMIN)
        .build();
  }

  public static AuthAccountDto loginAuthAccountDto(Long id) {
    return AuthAccountDto.builder()
        .id(id)
        .username("admin")
        .email("admin@email.com")
        .firstName("Steve")
        .lastName("Rogers")
        .role(Role.ADMIN)
        .build();
  }

  public static AuthAccountDto signupAuthAccountDto(Long id) {
    return AuthAccountDto.builder()
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

  public static LoginDto loginWithUsernameAndPassword() {
    return LoginDto.builder().username("admin").password("<PASSWORD>").build();
  }

  public static SignupDto managerSignupDto() {
    return SignupDto.builder()
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

  public static AuthResponseDto loginAuthResponseDto(Long id) {
    return AuthResponseDto.builder()
        .accessToken("access-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj")
        .refreshToken("refresh-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj")
        .account(
            AuthAccountDto.builder()
                .id(id)
                .username("admin")
                .email("admin@email.com")
                .firstName("Steve")
                .lastName("Rogers")
                .role(Role.ADMIN)
                .build())
        .build();
  }

  public static AuthResponseDto signupAuthResponseDto(Long id) {
    return AuthResponseDto.builder()
        .accessToken("access-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj")
        .refreshToken("refresh-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj")
        .account(
            AuthAccountDto.builder()
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
