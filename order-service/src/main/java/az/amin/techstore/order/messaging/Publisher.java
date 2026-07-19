package az.amin.techstore.order.messaging;

import lombok.RequiredArgsConstructor;
import org.example.orderms.dao.OutboxEvent;
import org.example.orderms.properties.RabbitPublisherProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Publisher {
    private final RabbitTemplate rabbitTemplate;
    private final RabbitPublisherProperties rabbitPublisherProperties;

    public void publishEvents(OutboxEvent outboxEvent){
        rabbitTemplate.convertAndSend(
                rabbitPublisherProperties.getExchange(),
                rabbitPublisherProperties.getRoutingKey(),
                outboxEvent.getPayload()
        );
    }
}
