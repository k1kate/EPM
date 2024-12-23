package com.example.EPM.service;

import com.example.EPM.models.*;
import com.example.EPM.parser.WebParser;
import com.example.EPM.repository.FacultyRepository;
import com.example.EPM.repository.SpecialtyRepository;
import com.example.EPM.repository.StudentRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParserService {
    private final FacultyRepository facultyRepository;
    private final SpecialtyRepository specialtyRepository;
    private final WebParser webParser;
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    public List<Faculty> getFaculty() {
        Map<Integer, String> facultyMap = webParser.parseFaculty();
        List<Faculty> list_faculty = new ArrayList<>();


        for (Map.Entry<Integer, String> entry : facultyMap.entrySet() ) {

            Integer key = entry.getKey();
            String value = entry.getValue();
            if (!facultyRepository.existsBynameFaculty(value)) {
                Faculty faculty = new Faculty();
                faculty.setId(key);
                faculty.setNameFaculty(value);
                facultyRepository.save(faculty);
                list_faculty.add(faculty);
            }

        }

        return list_faculty;
    }

    public void getSpecialty() {

        List<Specialty> list_specialty = new ArrayList<>();
        List<Faculty> faculties = facultyRepository.findAll();
        for(Faculty f: faculties){
            JsonArray jsonArray = webParser.parseSpecialty(String.valueOf(f.getId()));
            for (JsonElement jsonElement : jsonArray) {
                String name = jsonElement.getAsJsonObject().get("name").getAsString();
                String id_name = jsonElement.getAsJsonObject().get("id").getAsString();

                if (!specialtyRepository.existsBynameSpecialty(name))
                {
                    Specialty specialty = new Specialty();
                    specialty.setNameSpecialty(name);
                    Faculty fac_specialty = facultyRepository.findById(f.getId()).orElse(null);
                    specialty.setFaculty(fac_specialty);
                    specialty.setIdSpecialty(id_name);

                    JsonArray courses = webParser.parseCourse(id_name);

                    List<Course> arrCourse = new ArrayList<>();
                    for(JsonElement jsonCourse: courses) {
                        String id = jsonCourse.getAsJsonObject().get("kurs").getAsString();
                        Course course = new Course();
                        course.setCourseNumber(id);
                        course.setSpecialty(specialty);
                        arrCourse.add(course);

                    }

                    specialty.setCourse(arrCourse);

                    specialtyRepository.save(specialty);
                    list_specialty.add(specialty);
                }

            }

        }


    }

    public GroupTimeTable getTimeTable(Student student, Teacher teacher) {
        GroupTimeTable groupTimeTable;
        if (teacher==null) {
            groupTimeTable = webParser.parseTimetable(student.getGroupName(),null);
        } else {
            groupTimeTable = webParser.parseTimetable(null,teacher.getFullName());
        }

        return groupTimeTable;



    }





}


