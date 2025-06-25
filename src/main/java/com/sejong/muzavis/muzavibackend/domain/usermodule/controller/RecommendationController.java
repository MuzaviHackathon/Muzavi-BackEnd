package com.sejong.muzavis.muzavibackend.domain.usermodule.controller;

import com.sejong.muzavis.muzavibackend.domain.usermodule.dto.TrackProbabilityDto;
import com.sejong.muzavis.muzavibackend.domain.usermodule.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @GetMapping("/{userId}/probabilities")
    public ResponseEntity<List<TrackProbabilityDto>>
    getTrackProbabilities(@PathVariable Long userId) {

        return ResponseEntity.ok(recommendationService.fetchProbabilities(userId));
    }
}