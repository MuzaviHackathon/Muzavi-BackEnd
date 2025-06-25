package com.sejong.muzavis.muzavibackend.domain.usermodule.service;

import com.sejong.muzavis.muzavibackend.domain.usermodule.common.PreferenceDictionary;
import com.sejong.muzavis.muzavibackend.domain.usermodule.dto.PreferenceDto;
import com.sejong.muzavis.muzavibackend.domain.usermodule.dto.PreferenceSaveRequestDto;
import com.sejong.muzavis.muzavibackend.domain.usermodule.entities.User;
import com.sejong.muzavis.muzavibackend.domain.usermodule.entities.UserPreference;
import com.sejong.muzavis.muzavibackend.domain.usermodule.repository.UserPreferenceRepository;
import com.sejong.muzavis.muzavibackend.domain.usermodule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class UserPreferenceService {
    private final UserRepository userRepository;
    private final UserPreferenceRepository userPreferenceRepository;

    public void savePreferences(Long userId, PreferenceSaveRequestDto request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자 없음: " + userId));

        // 1) 기존 선호도 제거(덮어쓰기 전략)
        userPreferenceRepository.deleteByUser_Id(userId);

        // 2) 검증 & 매핑
        List<UserPreference> entities = request.preferences().stream()
                .filter(this::isValid)          // 사전 검증
                .map(dto -> new UserPreference(user, dto.category(), dto.option()))
                .toList();

        // 3) 벌크 저장
        userPreferenceRepository.saveAll(entities);
    }

    private boolean isValid(PreferenceDto dto) {
        return PreferenceDictionary.CATEGORIES
                .getOrDefault(dto.category(), Set.of())
                .contains(dto.option());
    }

}
