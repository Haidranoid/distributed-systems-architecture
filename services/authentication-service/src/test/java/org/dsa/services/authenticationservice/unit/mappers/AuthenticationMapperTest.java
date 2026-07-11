package org.dsa.services.authenticationservice.unit.mappers;

import static org.assertj.core.api.Assertions.assertThat;

import org.dsa.services.authenticationservice.common.dtos.AuthAccountDto;
import org.dsa.services.authenticationservice.common.dtos.AuthResponseDto;
import org.dsa.services.authenticationservice.common.fixtures.AuthenticationDtoFixtures;
import org.dsa.services.authenticationservice.mappers.AuthenticationMapper;
import org.dsa.services.authenticationservice.mappers.impl.AuthenticationMapperImpl;
import org.junit.jupiter.api.Test;

public class AuthenticationMapperTest {

  private final AuthenticationMapper authenticationMapper = new AuthenticationMapperImpl();

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
