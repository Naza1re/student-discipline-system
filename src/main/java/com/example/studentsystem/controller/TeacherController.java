package com.example.studentsystem.controller;

import com.example.studentsystem.exception.DisciplineNotFoundException;
import com.example.studentsystem.exception.TitleNotFoundException;
import com.example.studentsystem.model.Discipline;
import com.example.studentsystem.model.Title;
import com.example.studentsystem.service.DisciplineService;
import com.example.studentsystem.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final DisciplineService disciplineService;
    private final TitleService titleService;



    @GetMapping()
    public String getTeacherPage(Model model){
        List<Discipline> allDisciplines = disciplineService.findAll();
        model.addAttribute("dis",allDisciplines);
        return "for-teacher";
    }

    @GetMapping("/create-discipline")
    public String createDisciplinePage(){
        return "create-discipline";
    }


    @PostMapping("/create-discipline")
    public String createDiscipline(@ModelAttribute Discipline discipline){
        disciplineService.saveDiscipline(discipline);
        return "redirect:/main";
    }


    @GetMapping("/{discipline_id}/edit-delete")
    public String editDeletePage(Model model, @PathVariable Long discipline_id) throws DisciplineNotFoundException {
        Discipline discipline = disciplineService.getDisciplineById(discipline_id);
        model.addAttribute("dis",discipline);
        return "edit-delete-discipline";
    }
    @PostMapping("/{discipline_id}/edit")
    public String editDelete(@ModelAttribute Discipline discipline, @PathVariable Long discipline_id) throws DisciplineNotFoundException {
       disciplineService.updateDiscipline(discipline,discipline_id);
       return "redirect:/main";
    }
    @PostMapping("/{descipline_id}/delete")
    private String deletDiscipline(@PathVariable Long descipline_id){
        disciplineService.deleteDiscipline(descipline_id);
        return "redirect:/main";
    }
    @GetMapping("/{discipline_id}/titles")
    public String getTitlesOfDiscipline(@PathVariable Long discipline_id,Model model) throws DisciplineNotFoundException {
        List<Title> titles = titleService.titlesOfDiscipline(discipline_id);
        Discipline discipline = disciplineService.getDisciplineById(discipline_id);
        model.addAttribute("dis",discipline);
        model.addAttribute("titles",titles);
        return "titles";
    }
    @GetMapping("/{discipline_id}/titles/add")
    public String addTitlePage(Model model, @PathVariable Long discipline_id) throws DisciplineNotFoundException {
        Discipline discipline = disciplineService.getDisciplineById(discipline_id);
        model.addAttribute("dis",discipline);
        return "add-title";
    }
    @PostMapping("/{discipline_id}/titles/add")
    public String addTitle(@ModelAttribute Title title, @PathVariable Long discipline_id) throws DisciplineNotFoundException {
       titleService.createTitle(discipline_id,title);
        return "main";
    }

    @GetMapping("/{discipline_id}/titles/{title_id}/edit-delete")
    public String editDeletePage(Model model, @PathVariable Long discipline_id, @PathVariable Long title_id) throws DisciplineNotFoundException {
        Title title = titleService.findTitleById(title_id);
        model.addAttribute("title",title);
        Discipline discipline = disciplineService.getDisciplineById(discipline_id);
        model.addAttribute("dis",discipline);
        return "edit-delete-title";
    }
    @PostMapping("/{discipline_id}/titles/{title_id}/edit-delete")
    public String editDelete(@PathVariable Long title_id, @ModelAttribute Title title) throws TitleNotFoundException {
        titleService.updateTitle(title,title_id);
        return "redirect:/main";
    }
    @PostMapping("/{descipline_id}/titles/{title_id}/delete")
    private String deletTitle(@PathVariable Long title_id){
        titleService.deleteTitle(title_id);
        return "redirect:/main";
    }

}
