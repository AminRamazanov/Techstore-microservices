package az.amin.techstore.payment.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "wallet")
public class RabbitPublisherProperties {
    private String exchange;
    private String routingKey;
}
