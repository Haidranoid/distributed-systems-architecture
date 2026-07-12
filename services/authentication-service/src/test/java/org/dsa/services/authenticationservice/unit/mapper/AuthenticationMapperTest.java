package org.dsa.services.authenticationservice.unit.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.dsa.services.authenticationservice.fixture.AuthenticationDtoFixtures;
import org.dsa.services.authenticationservice.mapper.AuthenticationMapper;
import org.dsa.services.authenticationservice.response.AccountResponse;
import org.dsa.services.authenticationservice.response.AuthenticationResponse;
import org.junit.jupiter.api.Test;

public class AuthenticationMapperTest {

  private final org.dsa.services.authenticationservice.mapper.AuthenticationMapper
      authenticationMapper = new AuthenticationMapper();

  @Test
  void toAuthResponseDto_shouldMapAllFields_fromAuthAccountDto() {
    String accessToken = AuthenticationDtoFixtures.randomAccessToken();
    String refreshToken = AuthenticationDtoFixtures.randomRefreshToken();
    AccountResponse accountResponse = AuthenticationDtoFixtures.authAccountDto(1L);
    AuthenticationResponse authenticationResponse =
        authenticationMapper.toAuthResponseDto(accountResponse, accessToken, refreshToken);

    assertThat(authenticationResponse.accessToken())
        .isEqualTo("access-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj");
    assertThat(authenticationResponse.refreshToken())
        .isEqualTo("refresh-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj");
    assertThat(authenticationResponse.accountResponse().username()).isEqualTo("admin");
    assertThat(authenticationResponse.accountResponse().email()).isEqualTo("admin@email.com");
  }
}
