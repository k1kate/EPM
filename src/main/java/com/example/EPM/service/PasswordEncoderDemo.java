package com.example.EPM.service;

import com.example.EPM.models.*;
import com.example.EPM.parser.WebParser;
import com.example.EPM.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordEncoderDemo {
    private final UserRepository userRepository;
    private final FacultyRepository facultyRepository;
    private final SpecialtyRepository specialtyRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final WebParser webParser;
    private final ParserService parserService;

    public void main() {



    }

    public void fc() {
        User user = userRepository.findByUsername("user")
                .orElseThrow(() -> new RuntimeException("User not found"));
        Faculty faculty = facultyRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        Specialty specialty = specialtyRepository.findById(16) //7
                .orElseThrow(() -> new RuntimeException("Specialty not found"));
        Student student = new Student();
        student.setUser(user);
        student.setFaculty(faculty);
        student.setSpecialty(specialty);
        Course course = courseRepository.findById(26)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        student.setCourse(course);
        String group = webParser.parseGroup(specialty.getIdSpecialty(), course.getCourseNumber());
        student.setGroupName(group);
        studentRepository.save(student);


    }

    public void printS() {

    }

}