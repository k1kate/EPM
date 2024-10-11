package com.example.EPM.service;

import com.example.EPM.models.Faculty;
import com.example.EPM.models.Specialty;
import com.example.EPM.parser.WebParser;
import com.example.EPM.repository.FacultyRepository;
import com.example.EPM.repository.SpecialtyRepository;
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

    public List<Specialty> getSpecialty() {
        Map<Integer, List<String>> specialtyMap = webParser.parseSpecialty();
        List<Specialty> list_specialty = new ArrayList<>();

        for (Map.Entry<Integer, List<String>> entry : specialtyMap.entrySet() ) {
            Integer key = entry.getKey();
            List<String> value = entry.getValue();
            for(String name: value) {
                if (!specialtyRepository.existsBynameSpecialty(name)); {
                    Specialty specialty = new Specialty();
                    specialty.setNameSpecialty(name);
                    Faculty fac_specialty = facultyRepository.findById(key).orElse(null);
                    specialty.setFaculty(fac_specialty);
                    specialtyRepository.save(specialty);
                    list_specialty.add(specialty);
                }
            }


        }
        System.out.println(list_specialty);
        System.out.println(list_specialty.size());
        return list_specialty;
    }
}