package az.amin.techstore.gateway.jwtFilter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("customGatewayProperties")
@ConfigurationProperties(prefix = "gateway")
@Getter
@Setter
public class GatewayProperties {
    private List<String> publicPaths;
}
