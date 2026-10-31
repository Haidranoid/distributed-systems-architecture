package org.dsa.services.accountsservice;

import org.dsa.shared.starter.test.annotations.IntegrationEnvironment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@IntegrationEnvironment
class AccountServiceApplicationTests {

  @Test
  @DisplayName("accounts-service smoke test")
  void contextLoads() {}
}
