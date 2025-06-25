package com.sejong.muzavis.muzavibackend.domain.classmodule.dto;

import com.sejong.muzavis.muzavibackend.domain.classmodule.entities.Course;
import lombok.Getter;

@Getter
public class CourseDto {
    private String name;
    private String courseCode;
    private String department;
    private String professorName;

    public CourseDto(Course course) {
        this.name = course.getName();
        this.courseCode = course.getCourseCode();
        this.department = course.getDepartment();
        this.professorName = course.getProfessor() != null
                ? course.getProfessor().getName() : null;    }
}
