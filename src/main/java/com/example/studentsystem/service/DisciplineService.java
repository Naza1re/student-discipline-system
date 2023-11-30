package com.example.studentsystem.service;

import com.example.studentsystem.exception.DisciplineNotFoundException;
import com.example.studentsystem.model.Discipline;
import com.example.studentsystem.repository.DisciplineRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplineService {

    private final DisciplineRepository disciplineRepository;


    public DisciplineService(DisciplineRepository disciplineRepository) {
        this.disciplineRepository = disciplineRepository;
    }


    public List<Discipline> findAll(){
        return disciplineRepository.findAll();

    }
    public void saveDiscipline(Discipline discipline){
        disciplineRepository.save(discipline);
    }
    public Discipline getDisciplineById(Long id) throws DisciplineNotFoundException {
        Optional<Discipline> opt_discipline = disciplineRepository.findById(id);
        if(opt_discipline.isPresent()){
            return opt_discipline.get();
        }
        else
            throw new DisciplineNotFoundException("discipline with id '"+id+"' not found");
    }
    public void deleteDiscipline(Long id){
        Optional<Discipline> opt_discipline = disciplineRepository.findById(id);
        disciplineRepository.delete(opt_discipline.get());
    }

    public void updateDiscipline(Discipline discipline, Long disciplineId) throws DisciplineNotFoundException {
        Optional<Discipline> opt_discipline = disciplineRepository.findById(disciplineId);
        if(opt_discipline.isPresent()){
            if (discipline.getName() == null) {
                discipline.setName(opt_discipline.get().getName());
            } else {
                opt_discipline.get().setName(discipline.getName());
            }

            if (discipline.getTeacherName() == "") {
                discipline.setTeacherName(opt_discipline.get().getTeacherName());
            } else {
                opt_discipline.get().setTeacherName(discipline.getTeacherName());
            }

            if (discipline.getSpeciality() == null) {
                discipline.setSpeciality(opt_discipline.get().getSpeciality());
            } else {
                opt_discipline.get().setSpeciality(discipline.getSpeciality());
            }

            if (discipline.getCourse() == 0) {
                discipline.setCourse(opt_discipline.get().getCourse());
            } else {
                opt_discipline.get().setCourse(discipline.getCourse());
            }

            if (discipline.getSemester() == 0) {
                discipline.setSemester(opt_discipline.get().getSemester());
            } else {
                opt_discipline.get().setSemester(discipline.getSemester());
            }

            if (discipline.getStartDate() == null) {
                discipline.setStartDate(opt_discipline.get().getStartDate());
            } else {
                opt_discipline.get().setStartDate(discipline.getStartDate());
            }

            if (discipline.getEndDate() == null) {
                discipline.setEndDate(opt_discipline.get().getEndDate());
            } else {
                opt_discipline.get().setEndDate(discipline.getEndDate());
            }

            if (discipline.getTotalLectureHours() == 0) {
                discipline.setTotalLectureHours(opt_discipline.get().getTotalLectureHours());
            } else {
                opt_discipline.get().setTotalLectureHours(discipline.getTotalLectureHours());
            }

            if (discipline.getTotalPracticalHours() == 0) {
                discipline.setTotalPracticalHours(opt_discipline.get().getTotalPracticalHours());
            } else {
                opt_discipline.get().setTotalPracticalHours(discipline.getTotalPracticalHours());
            }

            if (discipline.getTotalLabHours() == 0) {
                discipline.setTotalLabHours(opt_discipline.get().getTotalLabHours());
            } else {
                opt_discipline.get().setTotalLabHours(discipline.getTotalLabHours());
            }

            if (discipline.getRequiredLecturePairsPerWeek() == 0) {
                discipline.setRequiredLecturePairsPerWeek(opt_discipline.get().getRequiredLecturePairsPerWeek());
            } else {
                opt_discipline.get().setRequiredLecturePairsPerWeek(discipline.getRequiredLecturePairsPerWeek());
            }

            if (discipline.getRequiredPracticalPairsPerWeek() == 0) {
                discipline.setRequiredPracticalPairsPerWeek(opt_discipline.get().getRequiredPracticalPairsPerWeek());
            } else {
                opt_discipline.get().setRequiredPracticalPairsPerWeek(discipline.getRequiredPracticalPairsPerWeek());
            }

            if (discipline.getRequiredLabPairsPerWeek() == 0) {
                discipline.setRequiredLabPairsPerWeek(opt_discipline.get().getRequiredLabPairsPerWeek());
            } else {
                opt_discipline.get().setRequiredLabPairsPerWeek(discipline.getRequiredLabPairsPerWeek());
            }
            disciplineRepository.save(opt_discipline.get());
        }
        else
            throw new DisciplineNotFoundException("discipline with id '"+disciplineId+"' not found");
    }
}
