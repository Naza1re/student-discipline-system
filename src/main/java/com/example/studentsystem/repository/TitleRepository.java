package com.example.studentsystem.repository;

import com.example.studentsystem.model.Title;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TitleRepository extends JpaRepository<Title,Long> {
    List<Title> findAllByDisciplineId(Long id);
}
