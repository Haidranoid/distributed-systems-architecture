package org.dsa.services.authenticationservice.unit.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.dsa.services.authenticationservice.dto.AuthAccountDto;
import org.dsa.services.authenticationservice.dto.AuthResponseDto;
import org.dsa.services.authenticationservice.fixture.AuthenticationDtoFixtures;
import org.dsa.services.authenticationservice.mapper.AuthenticationMapper;
import org.junit.jupiter.api.Test;

public class AuthenticationMapperTest {

  private final org.dsa.services.authenticationservice.mapper.AuthenticationMapper
      authenticationMapper = new AuthenticationMapper();

  @Test
  void toAuthResponseDto_shouldMapAllFields_fromAuthAccountDto() {
    String accessToken = AuthenticationDtoFixtures.randomAccessToken();
    String refreshToken = AuthenticationDtoFixtures.randomRefreshToken();
    AuthAccountDto authAccountDto = AuthenticationDtoFixtures.authAccountDto(1L);
    AuthResponseDto authResponseDto =
        authenticationMapper.toAuthResponseDto(authAccountDto, accessToken, refreshToken);

    assertThat(authResponseDto.accessToken())
        .isEqualTo("access-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj");
    assertThat(authResponseDto.refreshToken())
        .isEqualTo("refresh-token-añsldfjañeifaisdfjalsdkjfasldfjasñldfkjasñldfkj");
    assertThat(authResponseDto.account().username()).isEqualTo("admin");
    assertThat(authResponseDto.account().email()).isEqualTo("admin@email.com");
  }
}
