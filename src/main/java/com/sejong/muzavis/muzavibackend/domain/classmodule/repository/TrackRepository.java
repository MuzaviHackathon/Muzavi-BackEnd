package com.sejong.muzavis.muzavibackend.domain.classmodule.repository;

import com.sejong.muzavis.muzavibackend.domain.classmodule.entities.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrackRepository extends JpaRepository<Track, Long> {
    Optional<Track> findByName(String name);
    Optional<Track> findByNameAndDepartment(String name, String department);
}
