package org.dsa.services.core.servicesstarter.testing.integration;

import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.dsa.services.core.servicesstarter.testing.annotations.IntegrationEnvironment;

@DataJpaTest
@IntegrationEnvironment
public abstract class DataJpaIntegrationTest {
}
