package com.sejong.muzavis.muzavibackend.domain.classmodule.repository;

import com.sejong.muzavis.muzavibackend.domain.classmodule.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByName(String name);
}
