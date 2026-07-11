package org.dsa.core.sharedstarter.testing.annotations;

import org.dsa.core.sharedstarter.testing.config.ContainersTestConfig;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited

@ActiveProfiles("it")
@Import(ContainersTestConfig.class)

public @interface IntegrationEnvironment {
}
