package org.dsa.services.authenticationservice.common.fixtures;

import org.dsa.services.authenticationservice.common.entities.TokenEntity;

public class AuthenticationEntityFixtures {

    public static TokenEntity accessToken() {
        return TokenEntity.builder()
                .build();
    }
}
