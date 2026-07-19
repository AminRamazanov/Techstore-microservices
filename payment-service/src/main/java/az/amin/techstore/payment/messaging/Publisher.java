package az.amin.techstore.payment.messaging;

import lombok.RequiredArgsConstructor;
import org.example.paymentms.dao.OutboxEvent;
import org.example.paymentms.properties.RabbitPublisherProperties;
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
