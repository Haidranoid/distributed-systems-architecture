package org.dsa.core.sharedstarter.autoconfig;

import org.dsa.core.sharedstarter.messaging.producers.KafkaEventPublisher;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.kafka.autoconfigure.KafkaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@AutoConfiguration(after = KafkaAutoConfiguration.class)
public class KafkaAutoConfig {

  @Bean
  @ConditionalOnMissingBean
  public KafkaEventPublisher kafkaEventPublisher(KafkaTemplate<String, Object> kafkaTemplate) {
    return new KafkaEventPublisher(kafkaTemplate);
  }
}
