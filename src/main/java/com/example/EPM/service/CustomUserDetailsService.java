package com.example.EPM.service;

import com.example.EPM.models.Role;
import com.example.EPM.models.Student;
import com.example.EPM.models.Teacher;
import com.example.EPM.models.User;
import com.example.EPM.repository.StudentRepository;
import com.example.EPM.repository.TeacherRepository;
import com.example.EPM.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;

    private ParserService parserService;
    private StudentService studentService;
    private TeacherService teacherService;
    private final TeacherRepository teacherRepository;

    public CustomUserDetailsService(UserRepository userRepository, ParserService parserService, StudentService studentService, StudentRepository studentRepository, TeacherService teacherService, TeacherRepository teacherRepository) {
        this.parserService = parserService;
        this.studentService = studentService;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.teacherService = teacherService;
        this.teacherRepository = teacherRepository;


    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));


        if (!user.isTimeTableLoaded()) {

            if (user.getRole().getRoleName().equals(Role.RoleName.ROLE_STUDENT.name())) {
                Student student = studentService.findStudentByUsername(user.getUsername());
                student.setGroupTimeTable(parserService.getTimeTable(student, null));
                studentRepository.save(student);

            }else if (user.getRole().getRoleName().equals(Role.RoleName.ROLE_TEACHER.name())){
                Teacher teacher = teacherService.findTeacherByUsername(user.getUsername());
                teacher.setGroupTimeTable(parserService.getTimeTable(null, teacher));
                teacherRepository.save(teacher);
            }

//            user.setTimeTableLoaded(true);

        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                true, // учетные данные не истекают
                true,
                true,// учетные данные не заблокированы
                List.of(new SimpleGrantedAuthority(user.getRole().getRoleName()))
        );
    }
}
