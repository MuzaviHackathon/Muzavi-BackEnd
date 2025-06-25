package com.sejong.muzavis.muzavibackend.domain.usermodule.repository;

import com.sejong.muzavis.muzavibackend.domain.usermodule.entities.UserPreference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {
    /** 중복 방지를 위해 먼저 삭제하고 다시 저장하는 전략 */
    void deleteByUser_Id(Long userId);

    List<UserPreference> findByUser_Id(Long userId);
}
