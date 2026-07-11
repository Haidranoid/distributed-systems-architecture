package org.dsa.core.sharedstarter.testing.integration;

import org.dsa.core.sharedstarter.testing.annotations.IntegrationEnvironment;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
@IntegrationEnvironment
public abstract class DataJpaIntegrationTest {
}
