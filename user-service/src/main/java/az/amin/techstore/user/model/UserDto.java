package az.amin.techstore.user.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;
import org.example.user_authms.enums.RoleName;

@Setter
@Getter
public class UserDto {
    private Long id;

    private String fullName;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleName role;

    private boolean enabled;
}
