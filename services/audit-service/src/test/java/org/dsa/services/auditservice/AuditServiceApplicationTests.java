package org.dsa.services.auditservice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AuditServiceApplicationTests {

    @Test
    @DisplayName("audit-service smoke test")
    void contextLoads() {
        //TODO: add validation at startup to check if Kafka is up
    }

}
