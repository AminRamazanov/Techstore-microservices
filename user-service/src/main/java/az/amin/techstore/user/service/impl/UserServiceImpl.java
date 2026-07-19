package az.amin.techstore.user.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.user_authms.entity.repository.UserRepository;
import org.example.user_authms.service.UserService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    @Override
    public void signUp(SignUpDto signUpDto) {

    }

    @Override
    public void signIn(SignInDto signInDto) {

    }

    @Override
    public void recovery(RecoveryDto recoveryDto) {

    }
}
