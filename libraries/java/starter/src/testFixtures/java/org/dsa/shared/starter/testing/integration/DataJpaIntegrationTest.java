package org.dsa.shared.starter.testing.integration;

import org.dsa.shared.starter.testing.annotations.IntegrationEnvironment;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
@IntegrationEnvironment
public abstract class DataJpaIntegrationTest {}
