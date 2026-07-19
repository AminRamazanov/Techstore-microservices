package az.amin.techstore.wallet.messaging;

import az.amin.techstore.wallet.dao.OutboxEvent;
import az.amin.techstore.wallet.properties.RabbitPublisherProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Publisher {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitPublisherProperties rabbitPublisherProperties;

    public void publishEvents(OutboxEvent outboxEvent) {
        rabbitTemplate.convertAndSend(
                rabbitPublisherProperties.getExchange(),
                outboxEvent.getRoutingKey(),
                outboxEvent.getPayload()
        );
    }
}
