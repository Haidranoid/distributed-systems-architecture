package org.dsa.services.core.servicesstarter.testing.annotations;

import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.dsa.services.core.servicesstarter.testing.config.ContainersTestConfig;

import java.lang.annotation.*;


@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited

@ActiveProfiles("it")
@Import(ContainersTestConfig.class)

public @interface IntegrationEnvironment {
}
