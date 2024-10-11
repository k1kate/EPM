package com.example.EPM.service;

import com.example.EPM.models.Faculty;
import com.example.EPM.models.Specialty;
import com.example.EPM.models.Student;
import com.example.EPM.models.User;
import com.example.EPM.repository.FacultyRepository;
import com.example.EPM.repository.SpecialtyRepository;
import com.example.EPM.repository.StudentRepository;
import com.example.EPM.repository.UserRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PasswordEncoderDemo {
    private final UserRepository userRepository;
    private final FacultyRepository facultyRepository;
    private final SpecialtyRepository specialtyRepository;
    private final StudentRepository studentRepository;

    public void main() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "admin"; // Замените на ваш пароль
        String encodedPassword = passwordEncoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }

    public void fc() {
        User user = userRepository.findByUsername("user")
                .orElseThrow(() -> new RuntimeException("User not found"));
        Faculty faculty = facultyRepository.findById(1)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        Specialty specialty = specialtyRepository.findById(7)
                .orElseThrow(() -> new RuntimeException("Specialty not found"));
        Student student = new Student();
        student.setUser(user);
        student.setFaculty(faculty);
        student.setSpecialty(specialty);
        studentRepository.save(student);


    }

    public void printS() {
        String url = "https://m-raspisanie.asu.edu.ru/student/specialty"; // Замените на скопированный URL
        try {
            // Выполнение AJAX-запроса
            Document doc = Jsoup.connect(url).get();

            Elements options = doc.select("body");

            String jsonStr = options.text();

            JsonArray jsonArray = JsonParser.parseString(jsonStr).getAsJsonArray();

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject obj = jsonArray.get(i).getAsJsonObject();
                System.out.println(obj);
            }
        }
        catch (
            IOException e) {
                e.printStackTrace();
            }
    }
}