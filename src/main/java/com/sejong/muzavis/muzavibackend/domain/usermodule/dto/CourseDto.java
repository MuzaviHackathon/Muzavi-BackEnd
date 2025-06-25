package com.sejong.muzavis.muzavibackend.domain.usermodule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/** GET /users/{id}/courses 응답 전용 DTO */
@Getter
@AllArgsConstructor
public class CourseDto {
    private String courseCode;
    private String courseName;
}