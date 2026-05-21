package org.dsa.core.sharedstarter.testing.integration;

import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.dsa.core.sharedstarter.testing.annotations.IntegrationEnvironment;

@DataJpaTest
@IntegrationEnvironment
public abstract class DataJpaIntegrationTest {
}
