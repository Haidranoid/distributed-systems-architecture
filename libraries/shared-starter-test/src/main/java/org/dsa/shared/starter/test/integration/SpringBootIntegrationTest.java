package org.dsa.shared.starter.test.integration;

import org.dsa.shared.starter.test.annotations.IntegrationEnvironment;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

@SpringBootTest
@IntegrationEnvironment
@AutoConfigureMockMvc
public abstract class SpringBootIntegrationTest {}
