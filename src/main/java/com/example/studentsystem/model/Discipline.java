package com.example.studentsystem.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "disciplin")
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String teacherName;

    private String speciality;

    private int course;

    private int semester;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    private int totalLectureHours;

    private int totalPracticalHours;

    private int totalLabHours;

    private int requiredLecturePairsPerWeek;

    private int requiredPracticalPairsPerWeek;

    private int requiredLabPairsPerWeek;

    @Column(name = "title")
    @OneToMany(mappedBy = "discipline", cascade = CascadeType.ALL)
    private List<Title> titles;


    @PrePersist
    public void prePersist() {
        if (this.totalLectureHours > 0 && this.requiredLecturePairsPerWeek == 0) {
            this.requiredLecturePairsPerWeek = calculateRequiredPairsPerWeek(this.totalLectureHours);
        }

        if (this.totalPracticalHours > 0 && this.requiredPracticalPairsPerWeek == 0) {
            this.requiredPracticalPairsPerWeek = calculateRequiredPairsPerWeek(this.totalPracticalHours);
        }

        if (this.totalLabHours > 0 && this.requiredLabPairsPerWeek == 0) {
            this.requiredLabPairsPerWeek = calculateRequiredPairsPerWeek(this.totalLabHours);
        }
    }

    private int calculateRequiredPairsPerWeek(int totalHours) {
        System.out.println(totalHours);
        int totalPairs = totalHours / 2;
        int totalWeeks = calculateWeeksBetweenDates(this.startDate, this.endDate);
        if (totalWeeks > 0) {
            return totalPairs / totalWeeks;
        } else {
            return 0;
        }
    }

    private int calculateWeeksBetweenDates(Date startDate, Date endDate) {
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();

        long differenceMillis = endTime - startTime;

        long differenceWeeks = differenceMillis / (7 * 24 * 60 * 60 * 1000);

        return (int) differenceWeeks;
    }



}
