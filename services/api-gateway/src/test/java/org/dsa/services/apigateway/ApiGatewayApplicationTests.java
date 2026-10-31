package org.dsa.services.apigateway;

import org.dsa.shared.starter.test.annotations.IntegrationEnvironment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@IntegrationEnvironment
class ApiGatewayApplicationTests {

  @Test
  @DisplayName("api-gateway smoke test")
  void contextLoads() {}
}
