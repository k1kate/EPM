package com.example.EPM.controllers;


import com.example.EPM.models.Role;
import com.example.EPM.models.Student;
import com.example.EPM.models.Teacher;
import com.example.EPM.models.User;
import com.example.EPM.repository.StudentRepository;
import com.example.EPM.repository.UserRepository;
import com.example.EPM.service.ParserService;
import com.example.EPM.service.StudentService;
import com.example.EPM.service.TeacherService;
import com.example.EPM.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;


@Controller
@RequiredArgsConstructor //автоматически генерирует конструктор с обязательными аргументами
public class MainController {
    private final ParserService parserService;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final StudentService studentService;
    private final UserService userService;
    private final TeacherService teacherService;


    @GetMapping("/")
    public String mainPage(Principal principal, Model model) {
        if (principal == null) {
            return "redirect:/login";
        }

        String username = principal.getName();
        User user = userService.findUser(username);
        model.addAttribute("currentPage", "main");
        Student student = studentService.findStudentByUsername(username);
        System.out.println(user);
        System.out.println(user.getRole().getRoleName().equals(Role.RoleName.ROLE_STUDENT.name()));

        if (user.getRole().getRoleName().equals(Role.RoleName.ROLE_STUDENT.name())) {
            student = studentService.findStudentByUsername(username);
            model.addAttribute("weekData", student);
            System.out.println(student);
//            model.addAttribute("id_student", student.getId());
        } else if (user.getRole().getRoleName().equals(Role.RoleName.ROLE_TEACHER.name())) {
            Teacher teacher = teacherService.findTeacherByUsername(username);
            model.addAttribute("weekData", teacher);
//            model.addAttribute("id_student", teacher.getId());
        }

        model.addAttribute("user", user);



        return "mainpage";

    }

    @GetMapping("/courses")
    public String coursesPage(Principal principal, Model model) {
        String name = principal.getName();
        User user = userService.findUser(name);
        model.addAttribute("user", user);
        return "courses";
    }

    @GetMapping("/messages")
    public String messagesPage(Principal principal, Model model) {
        String name = principal.getName();
        User user = userService.findUser(name);
        model.addAttribute("user", user);
        return "messages";
    }

    @GetMapping("/news")
    public String newsPage(Principal principal, Model model) {
        String name = principal.getName();
        User user = userService.findUser(name);
        model.addAttribute("user", user);
        return "news";
    }

    @GetMapping("/user")
    public String userPage(Principal principal, Model model) {
        String name = principal.getName();

        Student student = studentService.findStudentByUsername(name);

        model.addAttribute("student", student);
        return "user";

    }


    @PostMapping("/user/upload-avatar")
    public String uploadAvatar(Principal principal, @RequestParam("avatar") MultipartFile avatar) throws IOException {
        String username = principal.getName();

        User user = userService.findUser(username);

        user.setAvatar(avatar.getBytes());
        userRepository.save(user);


        return "redirect:/user";
    }


    @GetMapping("/user/avatar")
    @ResponseBody
    public byte[] getAvatar(@RequestParam Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user not found"));

        byte[] av = user.getAvatar();
        return av;
    }


}

