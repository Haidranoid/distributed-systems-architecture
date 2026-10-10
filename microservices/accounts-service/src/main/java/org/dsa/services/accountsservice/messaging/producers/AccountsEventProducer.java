package org.dsa.services.accountsservice.messaging.producers;

import org.dsa.shared.core.messaging.contracts.KafkaEvent;
import org.dsa.shared.core.messaging.contracts.KafkaProducerTopic;
import org.dsa.shared.core.messaging.events.AccountEvent;
import org.dsa.shared.core.messaging.producers.KafkaEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AccountsEventProducer extends KafkaEventPublisher<AccountEvent> {

    public AccountsEventProducer(KafkaTemplate<String, KafkaEvent> kafkaTemplate) {
        super(KafkaProducerTopic.ACCOUNTS_SERVICE_TOPIC, kafkaTemplate);
    }

    @Override
    protected String generateEventKey(AccountEvent event) {
        return event.accountId().toString();
    }
}
