package az.amin.techstore.user.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserSignInDto {
    private String userName;
    private String password;
}
