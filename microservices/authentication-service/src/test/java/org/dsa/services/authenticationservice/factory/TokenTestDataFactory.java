package org.dsa.services.authenticationservice.factory;

import net.datafaker.Faker;
import org.dsa.services.authenticationservice.constants.TokenType;
import org.dsa.services.authenticationservice.entity.Token;

public class TokenTestDataFactory {

  private static final Faker faker = new Faker();

  public static Token randomToken() {
    return Token.builder()
        .id(faker.number().randomNumber())
        .token(faker.hashing().sha256())
        .tokenType(TokenType.BEARER)
        .accountId(faker.number().randomNumber())
        .build();
  }
}
