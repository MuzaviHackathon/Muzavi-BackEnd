package com.sejong.muzavis.muzavibackend.domain.usermodule.controller;

import com.sejong.muzavis.muzavibackend.domain.usermodule.dto.PreferenceSaveRequestDto;
import com.sejong.muzavis.muzavibackend.domain.usermodule.service.UserPreferenceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserPreferenceController {
    private final UserPreferenceService preferenceService;

    @PostMapping("/{userId}/preferences")
    public ResponseEntity<Void> saveUserPreferences(
            @PathVariable Long userId,
            @RequestBody @Valid PreferenceSaveRequestDto body) {

        preferenceService.savePreferences(userId, body);
        return ResponseEntity.ok().build();  // 200 OK
    }
}
