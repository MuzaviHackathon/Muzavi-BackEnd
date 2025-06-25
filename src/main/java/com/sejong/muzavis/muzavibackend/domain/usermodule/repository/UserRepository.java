package com.sejong.muzavis.muzavibackend.domain.usermodule.repository;

import com.sejong.muzavis.muzavibackend.domain.usermodule.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
