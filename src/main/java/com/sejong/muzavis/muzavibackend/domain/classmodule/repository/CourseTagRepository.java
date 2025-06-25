package com.sejong.muzavis.muzavibackend.domain.classmodule.repository;

import com.sejong.muzavis.muzavibackend.domain.classmodule.entities.CourseTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTagRepository extends JpaRepository<CourseTag, Long> {
}
