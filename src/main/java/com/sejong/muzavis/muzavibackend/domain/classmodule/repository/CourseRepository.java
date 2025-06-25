package com.sejong.muzavis.muzavibackend.domain.classmodule.repository;

import com.sejong.muzavis.muzavibackend.domain.classmodule.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findByName(String name);
    Optional<Course> findByNameAndCourseCode(String name, String courseCode);
    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.professor WHERE c.courseCode NOT IN :codes")
    List<Course> findAllExcludingTaken(@Param("codes") List<String> codes);

}
