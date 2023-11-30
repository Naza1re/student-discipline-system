package com.example.studentsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "topics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "discipline_id")
    private Discipline discipline;

    private String name;

    private int lectureHours;

    private int practicalHours;

    private int labHours;

    private boolean mandatory;

    public boolean isAutonomous(int selfStudyHours, int totalHours, double autonomyThreshold) {
        return selfStudyHours <= autonomyThreshold * totalHours;
    }

    @PrePersist
    public void prePersist() {
        calculateAutonomy();
    }

    private void calculateAutonomy() {
        int totalLectureHours = this.lectureHours;
        int totalPracticalHours = this.practicalHours;
        int totalLabHours = this.labHours;

        int totalSelfStudyLectureHours = this.discipline.getTotalLectureHours();
        int totalSelfStudyPracticalHours = this.discipline.getTotalPracticalHours();
        int totalSelfStudyLabHours = this.discipline.getTotalLabHours();

        double autonomyThreshold = 0.15;

        this.mandatory = isAutonomous(totalSelfStudyLectureHours, totalLectureHours, autonomyThreshold) &&
                isAutonomous(totalSelfStudyPracticalHours, totalPracticalHours, autonomyThreshold) &&
                isAutonomous(totalSelfStudyLabHours, totalLabHours, autonomyThreshold);
    }

}