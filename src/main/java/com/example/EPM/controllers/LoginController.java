package com.example.EPM.controllers;

import com.example.EPM.service.PasswordEncoderDemo;
import com.example.EPM.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserService userService;
    private final PasswordEncoderDemo passwordEncoderDemo;
    @GetMapping("/login")
    public String login() {
//        PasswordEncoderDemo.main();
        passwordEncoderDemo.printS();

//        userService.createUser("user", "user", ROLE_STUDENT.name());
        return "login";
    }

}
