package com.sejong.muzavis.muzavibackend.domain.classmodule.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    private String name;
    private String department;

    @Enumerated(EnumType.STRING)
    private Classification classification;

    private Integer credit;
    private String courseCode;

    @Enumerated(EnumType.STRING)
    private Semester semester;

    private Double ratingAvg;
    private String dayTime;
    private String room;

    public enum Classification {
        전필, 전선, 교양, 교선
    }

    public enum Semester {
        _1학기, _2학기, 여름학기, 겨울학기
    }
}
