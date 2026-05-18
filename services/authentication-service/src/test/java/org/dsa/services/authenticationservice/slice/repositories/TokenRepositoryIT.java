package org.dsa.services.authenticationservice.slice.repositories;

import org.dsa.services.authenticationservice.common.fixtures.AuthenticationEntityFixtures;
import org.dsa.services.authenticationservice.repositories.TokensRepository;
import org.dsa.services.core.servicesstarter.testing.integration.DataJpaIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class TokenRepositoryIT extends DataJpaIntegrationTest {

    @Autowired
    TokensRepository tokensRepository;

    @Test
    void save_shouldPersistEntity(){
        var token = AuthenticationEntityFixtures.accessToken();
        tokensRepository.save(token);

        var tokens = tokensRepository.findAll();

        assertThat(tokens).hasSize(1);
    }
}
