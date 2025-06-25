package com.sejong.muzavis.muzavibackend.domain.usermodule.service;

import com.sejong.muzavis.muzavibackend.domain.usermodule.dto.CourseDto;
import com.sejong.muzavis.muzavibackend.domain.usermodule.repository.UserCourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class UserCourseService {
    private final UserCourseRepository userCourseRepository;

    /** userId → CourseDto 목록 반환 */
    public List<CourseDto> getCompletedCourses(Long userId) {
        return userCourseRepository.findByUser_Id(userId).stream()
                .map(uc -> new CourseDto(uc.getCourseCode(), uc.getCourseName()))
                .toList();
    }
}
