package org.dsa.shared.starter.testing.annotations;

import java.lang.annotation.*;
import org.dsa.shared.starter.testing.config.ContainersTestConfig;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ActiveProfiles("it")
@Import(ContainersTestConfig.class)
public @interface IntegrationEnvironment {}
