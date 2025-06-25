package com.sejong.muzavis.muzavibackend.domain.usermodule.controller;

import com.sejong.muzavis.muzavibackend.domain.usermodule.dto.CourseDto;
import com.sejong.muzavis.muzavibackend.domain.usermodule.service.UserCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserCourseController {
    private final UserCourseService userCourseService;

    /** GET /users/{id}/courses */
    @GetMapping("/{userId}/courses")
    public ResponseEntity<List<CourseDto>> fetchCompletedCourses(@PathVariable Long userId) {
        List<CourseDto> body = userCourseService.getCompletedCourses(userId);
        return ResponseEntity.ok(body);   // 200 OK + JSON 배열
    }
}
