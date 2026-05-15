package org.dsa.services.core.servicesstarter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.application")
public record AppProperties(
        String name
) {
}
