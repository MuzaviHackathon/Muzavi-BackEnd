package com.sejong.muzavis.muzavibackend.domain.usermodule.repository;

import com.sejong.muzavis.muzavibackend.domain.usermodule.entities.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
    List<UserCourse> findByUser_Id(Long userId);

    @Query("SELECT uc.courseCode FROM UserCourse uc WHERE uc.user.id = :userId")
    List<String> findCourseCodesByUserId(Long userId);
}
