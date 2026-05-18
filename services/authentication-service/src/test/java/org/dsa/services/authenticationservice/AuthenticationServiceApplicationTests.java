package org.dsa.services.authenticationservice;

import org.dsa.services.core.servicesstarter.testing.annotations.IntegrationEnvironment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@IntegrationEnvironment
class AuthenticationServiceApplicationTests {

    @Test
    @DisplayName("authentication-service smoke test")
    void contextLoads() {
    }

}
