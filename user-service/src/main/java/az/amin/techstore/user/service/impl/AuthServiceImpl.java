package az.amin.techstore.user.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.user_authms.entity.repository.UserRepository;
import org.example.user_authms.mapping.UserMapper;
import org.example.user_authms.model.UserSignUpDto;
import org.example.user_authms.service.AuthService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    @Override
    public void signUp(UserSignUpDto userSignUpDto) {

    }

    @Override
    public void signIn(UserSignUpDto userSignUpDto) {

    }
}
