package org.dsa.shared.starter.testing.integration;

import org.dsa.shared.starter.testing.annotations.IntegrationEnvironment;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

@SpringBootTest
@IntegrationEnvironment
@AutoConfigureMockMvc
public abstract class SpringBootIntegrationTest {}
