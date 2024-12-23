package com.example.EPM.parser;

import com.example.EPM.models.*;
import com.example.EPM.repository.*;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebParser {
    private final TimetableRepository timetableRepository;
    private final WeekRepository weekRepository;
    private final GroupDisciplineRepository groupDisciplineRepository;
    private final DisciplineRepository disciplineRepository;
    private final GroupTimeTableRepository groupTimeTableRepository;


    public static Map<Integer, String> parseFaculty() {
        Map<Integer, String> map_faculty = new HashMap<Integer, String>();
        String url = "https://m-raspisanie.asu-edu.ru/student/faculty";
        try {
            // Выполнение AJAX-запроса
            Document doc = Jsoup.connect(url).get();

            Elements options = doc.select("body");

            String jsonStr = options.text();

            JsonArray jsonArray = JsonParser.parseString(jsonStr).getAsJsonArray();

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject obj = jsonArray.get(i).getAsJsonObject();

                map_faculty.put(obj.get("id").getAsInt(), obj.get("name").getAsString());

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println(map_faculty);
        return map_faculty;
    }

    public static JsonArray parseSpecialty(String id_spec) {
        String url = "https://m-raspisanie.asu-edu.ru/student/specialty";
        JsonArray jsonArray = null;
        try {
            Document doc = Jsoup.connect(url)
                    .data("id_spec", id_spec)
                    .post();

            String jsonStr = doc.body().text();

            jsonArray = JsonParser.parseString(jsonStr).getAsJsonArray();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public static JsonArray parseCourse(String name_id) {
        String url = "https://m-raspisanie.asu-edu.ru/student/kurs";
        JsonArray jsonArray = null;
        try {
            Document doc = Jsoup.connect(url)
                    .data("val_spec", name_id)
                    .post();

            String jsonStr = doc.body().text();

            jsonArray = JsonParser.parseString(jsonStr).getAsJsonArray();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }


    public static String parseGroup(String val_spec, String kurs) {
        String url = "https://m-raspisanie.asu-edu.ru/student/grup";

        JsonArray jsonArray = null;
        try {
            Document doc = Jsoup.connect(url)
                    .data("val_spec", val_spec)
                    .data("kurs", kurs)
                    .post();

            String jsonStr = doc.body().text();


            jsonArray = JsonParser.parseString(jsonStr).getAsJsonArray();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonArray.get(0).getAsString();
    }


    public GroupTimeTable parseTimetable(String group_name, String teacher_name) {
        String url;
        if (group_name == null) {
            url = "https://m-raspisanie.asu-edu.ru/teacher/" + teacher_name;
        }else {
            url = "https://m-raspisanie.asu-edu.ru/student/" + group_name;
        }

        String[] week_temp = {".td_style2_ch", ".td_style2_zn"};

        GroupTimeTable groupTimeTable = new GroupTimeTable();
        List<TimeTable> list_groupTimeTable = new ArrayList<>();
        try {

            Document doc = Jsoup.connect(url).get();
            Elements days = doc.getElementsByClass("vot_den");

            for (String w : week_temp) {
                TimeTable timeTable = new TimeTable();
                timeTable.setType(w.equals(".td_style2_ch") ? "ЧИСЛИТЕЛЬ" : "ЗНАМЕНАТЕЛЬ");

                // Устанавливаем связь с GroupTimeTable
                timeTable.setGroupTimeTable(groupTimeTable);  // Связь с GroupTimeTable

                Week week = new Week();
                List<Week> list_week = new ArrayList<>();

                for (Element content : days) {
                    week = new Week();
                    week.setDay(content.selectFirst(".dennedeli").text());

                    // Получение всех пар в текущем дне
                    Elements dayContents = content.getElementsByClass("den-content");

                    GroupDiscipline groupDiscipline = new GroupDiscipline();
                    List<GroupDiscipline> list_groupdisciplines = new ArrayList<>();
                    for (Element daycontent : dayContents) {
                        groupDiscipline = new GroupDiscipline();
                        List<Discipline> list_disciplines = new ArrayList<>();
                        Discipline discipline = new Discipline();
                        groupDiscipline.setNumber(daycontent.selectFirst(".npara").text());
                        discipline.setTimePair(daycontent.selectFirst(".time-para").text());

                        Element ch = daycontent.selectFirst(w);
                        Element Eaud = ch.selectFirst(".nottable_aud");

                        if (Eaud != null) {
                            discipline.setAud(Eaud.text());
                        }
                        Element Egroup = ch.selectFirst(".nottable_grnum");
                        if (Egroup != null) {
                            discipline.setGroup_name(Egroup.text());
                        }
                        String bText = "";
                        Element b = ch.selectFirst(w + " b");
                        if (b != null) {
                            bText = b.text();
                        }

                        Element Edisc = ch.selectFirst(".naz_disc");
                        if (Edisc != null) {
                            discipline.setDisc(bText + " " + Edisc.text());
                        }
                        Element Eteacher = ch.selectFirst(".nottable_fio");
                        if (Eteacher != null) {
                            discipline.setTeacher(Eteacher.text());
                        }
                        discipline.setGroupDiscipline(groupDiscipline);
                        disciplineRepository.save(discipline); // Сохраняем дисциплину
                        list_disciplines.add(discipline);

                        Element container = ch.selectFirst(".nottable_lang");
                        if (container != null && !container.text().isEmpty()) {
                            for (Element element : container.children()) {
                                if (!element.is("br")) {
                                    discipline = new Discipline();
                                    if (element.hasClass("nottable_grnum")) {
                                        discipline.setGroup_name(element.text().trim());
                                    } else if (element.hasClass("nottable_fio")) {
                                        discipline.setTeacher(element.text().trim());
                                    } else if (element.hasClass("nottable_aud")) {
                                        discipline.setAud(element.text().trim());
                                    }
                                }
                            }

                            if (discipline.getTeacher() != null) {
                                discipline.setGroupDiscipline(groupDiscipline);
                                disciplineRepository.save(discipline); // Сохраняем дисциплину
                                list_disciplines.add(discipline);
                            }
                        }

                        groupDiscipline.setDiscipline(list_disciplines);
                        groupDiscipline.setWeek(week);
                        groupDisciplineRepository.save(groupDiscipline); // Сохраняем дисциплину группы
                        list_groupdisciplines.add(groupDiscipline);
                    }

                    week.setGroupDiscipline(list_groupdisciplines); // Добавляем дисциплины в неделю
                    week.setTimeTable(timeTable);
                    list_week.add(week);
                    weekRepository.save(week); // Сохраняем неделю
                }

                // Сохраняем расписание для текущей недели
                timeTable.setWeeks(list_week);
                timetableRepository.save(timeTable); // Сохраняем timeTable
                list_groupTimeTable.add(timeTable); // Добавляем timeTable в общий список
            }

            // Сохраняем все расписания для группы
            groupTimeTable.setTimeTables(list_groupTimeTable);
            groupTimeTableRepository.save(groupTimeTable); // Сохраняем groupTimeTable

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(groupTimeTable);
        return groupTimeTable;
}
    public GroupTimeTable parseTimetableTeacher(String teacher_name) {
        String url = "https://m-raspisanie.asu-edu.ru/teacher/" + teacher_name;
        String[] week_temp = {".td_style2_ch", ".td_style2_zn"};

        GroupTimeTable groupTimeTable = new GroupTimeTable();
        List<TimeTable> list_groupTimeTable = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements days = doc.getElementsByClass("vot_den");

            for (String w : week_temp) {
                TimeTable timeTable = new TimeTable();
                timeTable.setType(w.equals(".td_style2_ch") ? "ЧИСЛИТЕЛЬ" : "ЗНАМЕНАТЕЛЬ");

                // Устанавливаем связь с GroupTimeTable
                timeTable.setGroupTimeTable(groupTimeTable);  // Связь с GroupTimeTable

                Week week = new Week();
                List<Week> list_week = new ArrayList<>();

                for (Element content : days) {
                    week = new Week();
                    week.setDay(content.selectFirst(".dennedeli").text());

                    // Получение всех пар в текущем дне
                    Elements dayContents = content.getElementsByClass("den-content");

                    GroupDiscipline groupDiscipline = new GroupDiscipline();
                    List<GroupDiscipline> list_groupdisciplines = new ArrayList<>();
                    for (Element daycontent : dayContents) {
                        groupDiscipline = new GroupDiscipline();
                        List<Discipline> list_disciplines = new ArrayList<>();
                        Discipline discipline = new Discipline();
                        groupDiscipline.setNumber(daycontent.selectFirst(".npara").text());
                        discipline.setTimePair(daycontent.selectFirst(".time-para").text());

                        Element ch = daycontent.selectFirst(w);
                        Element Eaud = ch.selectFirst(".nottable_aud");

                        if (Eaud != null) {
                            discipline.setAud(Eaud.text());
                        }
                        Element Egroup = ch.selectFirst(".nottable_grnum");
                        if (Egroup != null) {
                            discipline.setGroup_name(Egroup.text());
                        }
                        String bText = "";
                        Element b = ch.selectFirst(w + " b");
                        if (b != null) {
                            bText = b.text();
                        }

                        Element Edisc = ch.selectFirst(".naz_disc");
                        if (Edisc != null) {
                            discipline.setDisc(bText + " " + Edisc.text());
                        }
                        Element Eteacher = ch.selectFirst(".nottable_fio");
                        if (Eteacher != null) {
                            discipline.setTeacher(Eteacher.text());
                        }
                        discipline.setGroupDiscipline(groupDiscipline);
                        disciplineRepository.save(discipline); // Сохраняем дисциплину
                        list_disciplines.add(discipline);

                        Element container = ch.selectFirst(".nottable_lang");
                        if (container != null && !container.text().isEmpty()) {
                            for (Element element : container.children()) {
                                if (!element.is("br")) {
                                    discipline = new Discipline();
                                    if (element.hasClass("nottable_grnum")) {
                                        discipline.setGroup_name(element.text().trim());
                                    } else if (element.hasClass("nottable_fio")) {
                                        discipline.setTeacher(element.text().trim());
                                    } else if (element.hasClass("nottable_aud")) {
                                        discipline.setAud(element.text().trim());
                                    }
                                }
                            }

                            if (discipline.getTeacher() != null) {
                                discipline.setGroupDiscipline(groupDiscipline);
                                disciplineRepository.save(discipline); // Сохраняем дисциплину
                                list_disciplines.add(discipline);
                            }
                        }

                        groupDiscipline.setDiscipline(list_disciplines);
                        groupDiscipline.setWeek(week);
                        groupDisciplineRepository.save(groupDiscipline); // Сохраняем дисциплину группы
                        list_groupdisciplines.add(groupDiscipline);
                    }

                    week.setGroupDiscipline(list_groupdisciplines); // Добавляем дисциплины в неделю
                    week.setTimeTable(timeTable);
                    list_week.add(week);
                    weekRepository.save(week); // Сохраняем неделю
                }

                // Сохраняем расписание для текущей недели
                timeTable.setWeeks(list_week);
                timetableRepository.save(timeTable); // Сохраняем timeTable
                list_groupTimeTable.add(timeTable); // Добавляем timeTable в общий список
            }

            // Сохраняем все расписания для группы
            groupTimeTable.setTimeTables(list_groupTimeTable);
            groupTimeTableRepository.save(groupTimeTable); // Сохраняем groupTimeTable

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(groupTimeTable);
        return groupTimeTable;
    }



}

