package az.amin.techstore.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.user_authms.model.UserSignUpDto;
import org.example.user_authms.service.AuthService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;


    @RequestMapping("/sign-up")
    public void signUp(@RequestBody UserSignUpDto userSignUpDto){
        authService.signUp(userSignUpDto);
    }
}
