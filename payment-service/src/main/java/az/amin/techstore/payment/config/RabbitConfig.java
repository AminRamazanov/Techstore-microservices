package az.amin.techstore.payment.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String ORDER_EXCHANGE = "order.exchange";
    public static final String ORDER_DLX = "order.dlx";

    public static final String ORDER_CREATED_QUEUE = "order.created.queue";
    public static final String ORDER_CREATED_RETRY_QUEUE = "order.created.retry.queue";
    public static final String ORDER_CREATED_DLQ = "order.created.dlq.queue";

    public static final String ORDER_CREATED_ROUTING_KEY = "order.created.key";
    public static final String ORDER_CREATED_RETRY_ROUTING_KEY = "order.created.retry.key";
    public static final String ORDER_CREATED_DLQ_ROUTING_KEY = "order.created.dlq.key";

    @Bean
    TopicExchange orderExchange() {
        return new TopicExchange(ORDER_EXCHANGE);
    }

    @Bean
    TopicExchange deadLetterExchange() {
        return new TopicExchange(ORDER_DLX);
    }

    @Bean
    Queue orderCreatedQueue() {
        return QueueBuilder.durable(ORDER_CREATED_QUEUE)
                .deadLetterExchange(ORDER_DLX)
                .deadLetterRoutingKey(ORDER_CREATED_RETRY_ROUTING_KEY)
                .build();
    }

    @Bean
    Queue orderCreatedRetryQueue() {
        return QueueBuilder.durable(ORDER_CREATED_RETRY_QUEUE)
                .ttl(5000)
                .deadLetterExchange(ORDER_EXCHANGE)
                .deadLetterRoutingKey(ORDER_CREATED_ROUTING_KEY)
                .build();
    }

    @Bean
    Queue orderCreatedDlq() {
        return QueueBuilder.durable(ORDER_CREATED_DLQ)
                .build();
    }

    @Bean
    Binding orderBinding() {
        return BindingBuilder.bind(orderCreatedQueue())
                .to(orderExchange())
                .with(ORDER_CREATED_ROUTING_KEY);
    }

    @Bean
    Binding retryBinding() {
        return BindingBuilder.bind(orderCreatedRetryQueue())
                .to(deadLetterExchange())
                .with(ORDER_CREATED_RETRY_ROUTING_KEY);
    }

    @Bean
    Binding dlqBinding() {
        return BindingBuilder.bind(orderCreatedDlq())
                .to(deadLetterExchange())
                .with(ORDER_CREATED_DLQ_ROUTING_KEY);
    }
}