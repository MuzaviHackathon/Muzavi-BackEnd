package com.sejong.muzavis.muzavibackend.domain.classmodule.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "courese_tags")
public class CourseTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    private String tagName;
    private Integer frequency;
}
