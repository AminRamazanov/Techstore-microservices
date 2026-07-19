package az.amin.techstore.user.service;

public interface UserService {
    void signUp(SignUpDto signUpDto);
    void signIn(SignInDto signInDto);
    void recovery(RecoveryDto recoveryDto);
}
