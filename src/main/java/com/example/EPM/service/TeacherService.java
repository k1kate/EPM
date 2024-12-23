package com.example.EPM.service;

import com.example.EPM.models.Teacher;
import com.example.EPM.models.User;
import com.example.EPM.repository.TeacherRepository;
import com.example.EPM.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final UserRepository userRepository;
    private final TeacherRepository teacherRepository;

    public Teacher findTeacherByUsername(String name) {
        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new RuntimeException("not found"));


        Long id_user = user.getId();
        Teacher teacher = teacherRepository.findByUser_Id(Math.toIntExact(id_user));

        return teacher;
    }


}