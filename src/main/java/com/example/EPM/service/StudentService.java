package com.example.EPM.service;

import com.example.EPM.models.Student;
import com.example.EPM.models.User;
import com.example.EPM.repository.StudentRepository;
import com.example.EPM.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public Student findStudentByUsername(String name) {
        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new RuntimeException("not found"));


        Long id_user = user.getId();
        Student student = studentRepository.findByUser_Id(id_user);

        return student;
    }


}