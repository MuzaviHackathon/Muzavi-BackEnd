package com.sejong.muzavis.muzavibackend.domain.classmodule.controller;

import com.sejong.muzavis.muzavibackend.domain.classmodule.dto.CourseDto;
import com.sejong.muzavis.muzavibackend.domain.classmodule.entities.Course;
import com.sejong.muzavis.muzavibackend.domain.classmodule.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("/not-taken/{userId}")
    public ResponseEntity<List<CourseDto>> getNotTakenCourses(@PathVariable Long userId) {
        List<Course> notTaken = courseService.getUnattendedCourses(userId);
        List<CourseDto> dtos = notTaken.stream()
                .map(CourseDto::new)
                .toList();
        return ResponseEntity.ok(dtos);
    }
}
