package com.sejong.muzavis.muzavibackend.domain.usermodule.service;

import com.sejong.muzavis.muzavibackend.domain.usermodule.dto.CourseRow;
import com.sejong.muzavis.muzavibackend.domain.usermodule.dto.UserDto;
import com.sejong.muzavis.muzavibackend.domain.usermodule.entities.User;
import com.sejong.muzavis.muzavibackend.domain.usermodule.entities.UserCourse;
import com.sejong.muzavis.muzavibackend.domain.usermodule.repository.UserCourseRepository;
import com.sejong.muzavis.muzavibackend.domain.usermodule.repository.UserRepository;
import com.sejong.muzavis.muzavibackend.domain.usermodule.utils.ExcelParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserCourseRepository userCourseRepository;
    private final ExcelParser excelParser;

    public User registerUser(UserDto userDto){
        User user = User.builder()
                .name(userDto.getName())
                .major(userDto.getMajor())
                .semester_taken(userDto.getSemesterTaken())
                .build();

        return userRepository.save(user);
    }

    public void uploadCompletedCourseFile(Long userId, MultipartFile file){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음: " + userId));

        List<CourseRow> rows = excelParser.parseCourseRows(file);

        for (CourseRow row : rows) {
            // row.courseCode(), row.courseTitle() 사용
            userCourseRepository.save(
                    new UserCourse(user, row.courseCode(), row.courseTitle())
            );
        }
    }
}
