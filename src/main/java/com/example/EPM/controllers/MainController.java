package com.example.EPM.controllers;


import com.example.EPM.service.ParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequiredArgsConstructor
public class MainController {
    private final ParserService parserService;

    @GetMapping("/")
    public String protectedPage() {
        // model.addAttribute("user", "sss");
//        parserService.getFaculty();
//        parserService.getSpecialty();

        return "mainpage";
    }


}

