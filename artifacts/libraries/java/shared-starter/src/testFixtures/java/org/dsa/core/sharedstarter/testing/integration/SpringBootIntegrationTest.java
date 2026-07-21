package org.dsa.core.sharedstarter.testing.integration;

import org.dsa.core.sharedstarter.testing.annotations.IntegrationEnvironment;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

@SpringBootTest
@IntegrationEnvironment
@AutoConfigureMockMvc
public abstract class SpringBootIntegrationTest {}
