package com.sejong.muzavis.muzavibackend.domain.usermodule.service;

import com.sejong.muzavis.muzavibackend.domain.usermodule.dto.RecApiResponseDto;
import com.sejong.muzavis.muzavibackend.domain.usermodule.dto.TrackProbabilityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {
    private final WebClient recApiClient;

    public List<TrackProbabilityDto> fetchProbabilities(Long userId) {

        RecApiResponseDto resp = recApiClient.get()
                .uri("/users/{id}/recommendation", userId)
                .retrieve()
                .bodyToMono(RecApiResponseDto.class)
                .block();  // 간단히 블로킹, 필요 시 비동기 처리

        if (resp == null || resp.getProbabilities() == null)
            throw new IllegalStateException("추천 API 응답 오류");

        return resp.getProbabilities().entrySet().stream()
                .map(e -> new TrackProbabilityDto(
                        e.getKey(),
                        (int) Math.round(e.getValue() * 100)))  // 0.325 ➜ 33%
                .toList();
    }
}
