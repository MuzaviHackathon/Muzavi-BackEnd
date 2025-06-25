package com.sejong.muzavis.muzavibackend.domain.classmodule.repository;

import com.sejong.muzavis.muzavibackend.domain.classmodule.entities.TrackSubject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackSubjectRepository extends JpaRepository<TrackSubject, Long> {
    List<TrackSubject> findAll();
}
