package com.example.studentsystem.controller;

import com.example.studentsystem.model.Discipline;
import com.example.studentsystem.model.Title;
import com.example.studentsystem.service.DisciplineService;
import com.example.studentsystem.service.TitleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class DisciplineController {
    private final DisciplineService disciplineService;
    private final TitleService titleService;

    public DisciplineController(DisciplineService disciplineService, TitleService titleService) {
        this.disciplineService = disciplineService;
        this.titleService = titleService;
    }

    @GetMapping("/main")
    public String mainPage(){
        return "main";
    }
    @GetMapping("/disciplines")
    public String disciplines(Model model){
        List<Discipline> alldisciplines = disciplineService.findAll();
        model.addAttribute("dis",alldisciplines);
        return "discipline";
    }
    @GetMapping("/{discipline_id}/titles")
    public String getTitlesOfDiscipline(@PathVariable Long discipline_id,Model model){
        List<Title> titles = titleService.titlesOfDiscipline(discipline_id);
        model.addAttribute("titles",titles);
        return "title";
    }
}
