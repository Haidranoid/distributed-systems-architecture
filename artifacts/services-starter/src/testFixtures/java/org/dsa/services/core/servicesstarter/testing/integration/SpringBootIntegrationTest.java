package org.dsa.services.core.servicesstarter.testing.integration;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.dsa.services.core.servicesstarter.testing.annotations.IntegrationEnvironment;

@SpringBootTest
@IntegrationEnvironment
@AutoConfigureMockMvc
public abstract class SpringBootIntegrationTest {
}
