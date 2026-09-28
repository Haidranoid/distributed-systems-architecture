package org.dsa.services.authenticationservice.slice.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.dsa.services.authenticationservice.fixture.AuthenticationFixtures;
import org.dsa.services.authenticationservice.repository.TokenRepository;
import org.dsa.shared.starter.test.integration.DataJpaIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TokenRepositoryIT extends DataJpaIntegrationTest {

  @Autowired TokenRepository tokenRepository;

  @Test
  void save_shouldPersistEntity() {
    var token = AuthenticationFixtures.accessToken();
    tokenRepository.save(token);

    var tokens = tokenRepository.findAll();

    assertThat(tokens).hasSize(1);
  }
}
