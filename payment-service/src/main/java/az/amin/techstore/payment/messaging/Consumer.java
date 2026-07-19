package az.amin.techstore.payment.messaging;

import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.paymentms.config.RabbitConfig;
import org.example.paymentms.model.events.OrderCreatedEvent;
import org.example.paymentms.model.events.PaymentSuccessEvent;
import org.example.paymentms.service.PaymentService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class Consumer {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;

    @RabbitListener(queues = "order.created.queue")
    public void handleOrderCreatedEvent(Message message, Channel channel) throws IOException {

        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {

            OrderCreatedEvent orderCreatedEvent =
                    objectMapper.readValue(
                            message.getBody(),
                            OrderCreatedEvent.class);

//             3. BUSINESS LOGIC
            paymentService.processPayment(orderCreatedEvent);

            channel.basicAck(deliveryTag, false);

        } catch (Exception ex) {

            int retryCount = getRetryCount(message);

            if (retryCount >= 3) {

                try {

                    rabbitTemplate.convertAndSend(
                            RabbitConfig.ORDER_DLX,
                            RabbitConfig.ORDER_CREATED_DLQ_ROUTING_KEY,
                            message.getBody());

                    channel.basicAck(deliveryTag, false);
                    return;

                } catch (Exception e) {

                    log.error("Cannot publish to DLQ", e);

                    channel.basicNack(deliveryTag, false, true);
                    return;
                }
            }

            channel.basicNack(deliveryTag, false, false);
        }
    }

    @RabbitListener(queues = "payment.success.queue")
    public void handlePaymentSuccessEvent(Message message, Channel channel) throws IOException {

        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {

            PaymentSuccessEvent paymentSuccessEvent =
                    objectMapper.readValue(
                            message.getBody(),
                            PaymentSuccessEvent.class);

//             3. BUSINESS LOGIC
            paymentService.processPayment(paymentSuccessEvent);

            channel.basicAck(deliveryTag, false);

        } catch (Exception ex) {

            int retryCount = getRetryCount(message);

            if (retryCount >= 3) {

                try {

                    rabbitTemplate.convertAndSend(
                            RabbitConfig.ORDER_DLX,
                            RabbitConfig.ORDER_CREATED_DLQ_ROUTING_KEY,
                            message.getBody());

                    channel.basicAck(deliveryTag, false);
                    return;

                } catch (Exception e) {

                    log.error("Cannot publish to DLQ", e);

                    channel.basicNack(deliveryTag, false, true);
                    return;
                }
            }

            channel.basicNack(deliveryTag, false, false);
        }
    }


    @SuppressWarnings("unchecked")
    private int getRetryCount(Message message) {

        Object header = message.getMessageProperties()
                .getHeaders()
                .get("x-death");

        if (!(header instanceof List<?> deaths) || deaths.isEmpty()) {
            return 0;
        }

        Map<String, Object> first = (Map<String, Object>) deaths.get(0);

        return ((Long) first.get("count")).intValue();
    }
}
