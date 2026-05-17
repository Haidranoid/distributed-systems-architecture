package org.dsa.services.authenticationservice.common.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.endpoints")
public record Endpoints(
        String accountsServiceEndpoint,
        String accountsServiceInternalEndpoint
) {
}
