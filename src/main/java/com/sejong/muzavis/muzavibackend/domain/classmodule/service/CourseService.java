package com.sejong.muzavis.muzavibackend.domain.classmodule.service;

import com.sejong.muzavis.muzavibackend.domain.classmodule.entities.Course;
import com.sejong.muzavis.muzavibackend.domain.classmodule.repository.CourseRepository;
import com.sejong.muzavis.muzavibackend.domain.usermodule.repository.UserCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserCourseRepository userCourseRepository;

    public List<Course> getUnattendedCourses(Long userId) {
        // 1. 유저가 들은 과목 코드 가져오기
        List<String> takenCourseCodes = userCourseRepository.findCourseCodesByUserId(userId);

        // 2. 들은 과목을 제외한 전체 과목 가져오기
        return courseRepository.findAllExcludingTaken(takenCourseCodes);
    }
}
