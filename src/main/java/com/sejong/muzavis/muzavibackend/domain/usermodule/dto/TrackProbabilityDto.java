package com.sejong.muzavis.muzavibackend.domain.usermodule.dto;

// 프런트로 돌려줄 DTO (확률 %만 포함)
public record TrackProbabilityDto(String track, int percent) { }