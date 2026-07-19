package az.amin.techstore.gateway.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtUtil {
    @Value("${secretKey}")
    private String secret;

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Long getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.get("userId", Long.class);
    }


    public String getUsername(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public List<String> getRoles(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        return claims.get("roles", List.class);
    }

}
