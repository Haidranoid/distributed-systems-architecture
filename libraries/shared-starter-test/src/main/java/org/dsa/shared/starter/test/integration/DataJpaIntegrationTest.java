package org.dsa.shared.starter.test.integration;

import org.dsa.shared.starter.test.annotations.IntegrationEnvironment;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
@IntegrationEnvironment
public abstract class DataJpaIntegrationTest {}
