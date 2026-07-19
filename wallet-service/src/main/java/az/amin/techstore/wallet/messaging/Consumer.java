package az.amin.techstore.wallet.messaging;

import az.amin.techstore.wallet.config.RabbitConfig;
import az.amin.techstore.wallet.model.PaymentStartCommand;
import az.amin.techstore.wallet.service.WalletService;
import com.rabbitmq.client.Channel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {
    private final ObjectMapper objectMapper;
    private final WalletService walletService;
    private final RabbitTemplate rabbitTemplate;


    @RabbitListener(queues = "payment.start.queue")
    public void walletProcess(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();

        try {
            PaymentStartCommand paymentStartCommand =
                    objectMapper.readValue(
                            message.getBody(),
                            PaymentStartCommand.class
                    );

            walletService.handlePayment(paymentStartCommand);

            channel.basicAck(deliveryTag, false);

        } catch (Exception e) {

            int retryCount = getRetryCount(message);

            if (retryCount >= 3) {

                try {

                    rabbitTemplate.convertAndSend(
                            RabbitConfig.PAYMENT_DLX,
                            RabbitConfig.PAYMENT_START_DLQ_KEY,
                            message.getBody()
                    );

                    channel.basicAck(deliveryTag, false);
                    return;
                } catch (Exception exc) {

                    log.error("Cannot publish to Dlq", exc);
                    channel.basicNack(deliveryTag, false, true);
                    return;
                }
            }
            channel.basicNack(deliveryTag, false, true);
        }
    }

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