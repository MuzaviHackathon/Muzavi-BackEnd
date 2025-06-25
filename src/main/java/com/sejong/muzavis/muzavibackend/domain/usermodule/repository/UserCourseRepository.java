package com.sejong.muzavis.muzavibackend.domain.usermodule.repository;

import com.sejong.muzavis.muzavibackend.domain.usermodule.entities.UserCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
    List<UserCourse> findByUser_Id(Long userId);
}
