package org.dsa.services.core.servicesstarter.autoconfig;

import org.dsa.services.core.servicesstarter.properties.AppProperties;
import org.dsa.services.core.servicesstarter.properties.JwtProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@AutoConfiguration
@EnableConfigurationProperties({AppProperties.class, JwtProperties.class})
public class ServicesStarterAutoConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
