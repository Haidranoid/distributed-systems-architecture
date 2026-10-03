package org.dsa.shared.starter.autoconfig;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.kafka.autoconfigure.KafkaAutoConfiguration;

@AutoConfiguration(after = KafkaAutoConfiguration.class)
public class KafkaAutoConfig {

  // @Bean
  // @ConditionalOnMissingBean
  // public KafkaEventPublisher kafkaEventPublisher(KafkaTemplate<String, KafkaEvent> kafkaTemplate)
  // {
  //  return new KafkaEventPublisher(kafkaTemplate);
  // }
}
