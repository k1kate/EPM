package com.example.EPM.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebParser {
    public static Map<Integer, String> parseFaculty() {
        Map<Integer, String> map_faculty = new HashMap<Integer, String>();
        String url = "https://m-raspisanie.asu.edu.ru/student/faculty"; // Замените на скопированный URL
        try {
            // Выполнение AJAX-запроса
            Document doc = Jsoup.connect(url).get();

            Elements options = doc.select("body");

            String jsonStr = options.text();

            JsonArray jsonArray = JsonParser.parseString(jsonStr).getAsJsonArray();

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject obj = jsonArray.get(i).getAsJsonObject();

                map_faculty.put(obj.get("id").getAsInt(),obj.get("name").getAsString());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(map_faculty);
        return map_faculty;
    }

    public static Map<Integer, List<String>> parseSpecialty() {
        Map<Integer, List<String>> map_specialty = new HashMap<Integer, List<String>>();
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
                map_specialty.computeIfAbsent(obj.get("kod_spec").getAsInt(), k -> new ArrayList<>()).add(obj.get("name").getAsString());


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map_specialty;
    }
}
