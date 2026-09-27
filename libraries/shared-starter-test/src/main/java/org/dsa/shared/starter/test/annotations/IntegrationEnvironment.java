package org.dsa.shared.starter.test.annotations;

import java.lang.annotation.*;
import org.dsa.shared.starter.test.config.ContainersTestConfig;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ActiveProfiles("it")
@Import(ContainersTestConfig.class)
public @interface IntegrationEnvironment {}
