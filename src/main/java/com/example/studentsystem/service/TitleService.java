package com.example.studentsystem.service;

import com.example.studentsystem.exception.DisciplineNotFoundException;
import com.example.studentsystem.exception.TitleNotFoundException;
import com.example.studentsystem.exception.UserNotFoundException;
import com.example.studentsystem.model.Discipline;
import com.example.studentsystem.model.Title;
import com.example.studentsystem.model.User;
import com.example.studentsystem.repository.DisciplineRepository;
import com.example.studentsystem.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TitleService {

    private final TitleRepository titleRepository;
    private final DisciplineRepository disciplineRepository;

    public List<Title> titlesOfDiscipline(Long id){
        return titleRepository.findAllByDisciplineId(id);
    }

    public void createTitle(Long disciplineId, Title title) throws DisciplineNotFoundException {
        Optional<Discipline> opt_discipline = disciplineRepository.findById(disciplineId);
        if(opt_discipline.isPresent()){
            title.setDiscipline(opt_discipline.get());
            titleRepository.save(title);
        }
        else
            throw new DisciplineNotFoundException("discipline with id '"+disciplineId+"' not found");
    }

    public Title findTitleById(Long titleId) {
        Optional<Title> opt_title = titleRepository.findById(titleId);
        return opt_title.get();
    }

    public void updateTitle(Title title, Long titleId) throws TitleNotFoundException {

            Optional<Title> opt_title = titleRepository.findById(titleId);
            if(opt_title.isPresent()){
                if(title.getName().equals("")){
                    opt_title.get().setName(opt_title.get().getName());
                }
                else
                    opt_title.get().setName(title.getName());
                opt_title.get().setLabHours(title.getLabHours());
                opt_title.get().setLectureHours(title.getLectureHours());
                opt_title.get().setPracticalHours(title.getPracticalHours());

            }
            else
                throw new TitleNotFoundException("title with id '"+titleId +"' not found");

    }

    public void deleteTitle(Long titleId) {
        Optional<Title> opt_title = titleRepository.findById(titleId);
        titleRepository.delete(opt_title.get());
    }
}
