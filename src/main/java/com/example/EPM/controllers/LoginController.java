package com.example.EPM.controllers;

import com.example.EPM.repository.RoleRepository;
import com.example.EPM.repository.TeacherRepository;
import com.example.EPM.repository.UserRepository;
import com.example.EPM.service.ParserService;
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
    private final ParserService parserService;
    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @GetMapping("/login")
    public String login() {


//        User user = userService.createUser("teacher", "teacher", ROLE_TEACHER.name());
//        Teacher teacher = new Teacher();
//        teacher.setNameTeacher("г");
//        teacher.setPatronymicTeacher("гг");
//        teacher.setSurnameTeacher("ггг");
//        teacher.setUser(user);
//        teacherRepository.save(teacher);

//        User user = userRepository.getById(9L);
//        Role role = roleRepository.findByRoleName(ROLE_STUDENT.name())
//                .orElseThrow(() -> new RuntimeException("Role not found"));
//        user.setRole(role);
//        userRepository.save(user);
//
//        user = userRepository.getById(13L);
//        role = roleRepository.findByRoleName(ROLE_TEACHER.name())
//                .orElseThrow(() -> new RuntimeException("Role not found"));
//        user.setRole(role);
//        userRepository.save(user);



        return "login";
    }

}
