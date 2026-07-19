package az.amin.techstore.user.service;

import org.example.user_authms.model.UserSignUpDto;

public interface AuthService {
    void signUp(UserSignUpDto userSignUpDto);

    void signIn(UserSignUpDto userSignUpDto);
}
