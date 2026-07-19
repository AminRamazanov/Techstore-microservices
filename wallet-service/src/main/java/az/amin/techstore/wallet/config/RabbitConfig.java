package az.amin.techstore.wallet.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    public static final String PAYMENT_EXC = "payment.exc";
    public static final String PAYMENT_DLX = "payment.dlx";

    public static final String PAYMENT_START_QUEUE = "payment.start.queue";
    public static final String PAYMENT_START_RETRY_QUEUE = "payment.start.retry.queue";
    public static final String PAYMENT_START_DLQ = "payment.start.dlq";

    public static final String PAYMENT_SUCCESS_QUEUE = "payment.success.queue";
    public static final String PAYMENT_SUCCESS_RETRY_QUEUE = "payment.success.retry.queue";
    public static final String PAYMENT_SUCCESS_DLQ = "payment.success.dlq";

    public static final String PAYMENT_FAILED_QUEUE = "payment.failed.queue";
    public static final String PAYMENT_FAILED_RETRY_QUEUE = "payment.failed.retry.queue";
    public static final String PAYMENT_FAILED_DLQ = "payment.failed.dlq";

    public static final String PAYMENT_START_KEY = "payment.start.key";
    public static final String PAYMENT_START_RETRY_KEY = "payment.start.retry.key";
    public static final String PAYMENT_START_DLQ_KEY = "payment.start.dlq.key";

    public static final String PAYMENT_SUCCESS_KEY = "payment.success.key";
    public static final String PAYMENT_SUCCESS_RETRY_KEY = "payment.success.retry.key";
    public static final String PAYMENT_SUCCESS_DLQ_KEY = "payment.success.dlq.key";

    public static final String PAYMENT_FAILED_KEY = "payment.failed.key";
    public static final String PAYMENT_FAILED_RETRY_KEY = "payment.failed.retry.key";
    public static final String PAYMENT_FAILED_DLQ_KEY = "payment.failed.dlq.key";


    @Bean
    public TopicExchange paymentExc() {
        return new TopicExchange(PAYMENT_EXC);
    }

    @Bean
    public TopicExchange paymentDlx() {
        return new TopicExchange(PAYMENT_DLX);
    }

    @Bean
    public Queue paymentStartQueue() {
        return QueueBuilder.durable(PAYMENT_START_QUEUE)
                .deadLetterExchange(PAYMENT_DLX)
                .deadLetterRoutingKey(PAYMENT_START_RETRY_KEY)
                .build();
    }

    @Bean
    public Queue paymentSuccessQueue() {
        return QueueBuilder.durable(PAYMENT_SUCCESS_QUEUE)
                .deadLetterExchange(PAYMENT_DLX)
                .deadLetterRoutingKey(PAYMENT_SUCCESS_RETRY_KEY)
                .build();
    }

    @Bean
    public Queue paymentFailedQueue() {
        return QueueBuilder.durable(PAYMENT_FAILED_QUEUE)
                .deadLetterExchange(PAYMENT_DLX)
                .deadLetterRoutingKey(PAYMENT_FAILED_RETRY_KEY)
                .build();
    }

    @Bean
    public Queue paymentStartRetryQueue() {
        return QueueBuilder.durable(PAYMENT_START_RETRY_QUEUE)
                .ttl(5000)
                .deadLetterExchange(PAYMENT_EXC)
                .deadLetterRoutingKey(PAYMENT_START_KEY)
                .build();
    }

    @Bean
    public Queue paymentSuccessRetryQueue() {
        return QueueBuilder.durable(PAYMENT_SUCCESS_RETRY_QUEUE)
                .ttl(5000)
                .deadLetterExchange(PAYMENT_EXC)
                .deadLetterRoutingKey(PAYMENT_SUCCESS_KEY)
                .build();
    }

    @Bean
    public Queue paymentFailedRetryQueue() {
        return QueueBuilder.durable(PAYMENT_FAILED_RETRY_QUEUE)
                .ttl(5000)
                .deadLetterExchange(PAYMENT_EXC)
                .deadLetterRoutingKey(PAYMENT_FAILED_KEY)
                .build();
    }

    @Bean
    public Queue paymentStartDlq() {
        return QueueBuilder.durable(PAYMENT_START_DLQ).build();
    }

    @Bean
    public Queue paymentSuccessDlq() {
        return QueueBuilder.durable(PAYMENT_SUCCESS_DLQ).build();
    }

    @Bean
    public Queue paymentFailedDlq() {
        return QueueBuilder.durable(PAYMENT_FAILED_DLQ).build();
    }

    @Bean
    public Binding paymentStartBinding() {
        return BindingBuilder.bind(paymentStartQueue())
                .to(paymentExc())
                .with(PAYMENT_START_KEY);
    }

    @Bean
    public Binding paymentSuccessBinding() {
        return BindingBuilder.bind(paymentSuccessQueue())
                .to(paymentExc())
                .with(PAYMENT_SUCCESS_KEY);
    }

    @Bean
    public Binding paymentFailedBinding() {
        return BindingBuilder.bind(paymentFailedQueue())
                .to(paymentExc())
                .with(PAYMENT_FAILED_KEY);
    }

    @Bean
    public Binding paymentStartRetryBinding() {
        return BindingBuilder.bind(paymentStartRetryQueue())
                .to(paymentDlx())
                .with(PAYMENT_START_RETRY_KEY);
    }

    @Bean
    public Binding paymentSuccessRetryBinding() {
        return BindingBuilder.bind(paymentSuccessRetryQueue())
                .to(paymentDlx())
                .with(PAYMENT_SUCCESS_RETRY_KEY);
    }

    @Bean
    public Binding paymentFailedRetryBinding() {
        return BindingBuilder.bind(paymentFailedRetryQueue())
                .to(paymentDlx())
                .with(PAYMENT_FAILED_RETRY_KEY);
    }

    @Bean
    public Binding paymentStartDlqBinding() {
        return BindingBuilder.bind(paymentStartDlq())
                .to(paymentDlx())
                .with(PAYMENT_START_DLQ_KEY);
    }

    @Bean
    public Binding paymentSuccessDlqBinding() {
        return BindingBuilder.bind(paymentSuccessDlq())
                .to(paymentDlx())
                .with(PAYMENT_SUCCESS_DLQ_KEY);
    }

    @Bean
    public Binding paymentFailedDlqBinding() {
        return BindingBuilder.bind(paymentFailedDlq())
                .to(paymentDlx())
                .with(PAYMENT_FAILED_DLQ_KEY);
    }
}
