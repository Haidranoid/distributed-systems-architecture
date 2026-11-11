package org.dsa.services.authenticationservice.unit.mapper;

import org.dsa.services.authenticationservice.dto.response.AccountResponse;
import org.dsa.services.authenticationservice.dto.response.AuthenticationResponse;
import org.dsa.services.authenticationservice.fixture.AuthenticationDtoFixtures;
import org.dsa.services.authenticationservice.mapper.AuthenticationMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AuthenticationMapperTest {

  private final org.dsa.services.authenticationservice.mapper.AuthenticationMapper
      authenticationMapper = new AuthenticationMapper();

  @Test
  void mapToAuthResponseDto_shouldMapAllFields_fromAuthAccountDto() {
    String accessToken = AuthenticationDtoFixtures.randomAccessToken();
    String refreshToken = AuthenticationDtoFixtures.randomRefreshToken();
    AccountResponse accountResponse = AuthenticationDtoFixtures.authAccountDto(1L);
    AuthenticationResponse authenticationResponse =
        authenticationMapper.mapToAuthResponseDto(accountResponse, accessToken, refreshToken);

    assertThat(authenticationResponse.accessToken())
        .isEqualTo("access-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj");
    assertThat(authenticationResponse.refreshToken())
        .isEqualTo("refresh-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj");
    assertThat(authenticationResponse.accountResponse().username()).isEqualTo("admin");
    assertThat(authenticationResponse.accountResponse().email()).isEqualTo("admin@email.com");
  }
}
