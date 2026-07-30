package org.dsa.services.authenticationservice.fixture;

import org.dsa.services.authenticationservice.entity.Token;

public class AuthenticationFixtures {

  public static Token accessToken() {
    return Token.builder().build();
  }
}
