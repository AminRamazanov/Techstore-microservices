package az.amin.techstore.wallet.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "payment")
public class RabbitPublisherProperties {
    private String exchange;
    private String successRoutingKey;
    private String failedRoutingKey;
}
