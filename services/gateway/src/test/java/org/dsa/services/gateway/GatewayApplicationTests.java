package org.dsa.services.gateway;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("dev")
class GatewayApplicationTests {

    @DisplayName("gateway smoke test")
    @Test
    void contextLoads() {
    }

}
