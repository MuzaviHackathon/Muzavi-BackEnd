package com.sejong.muzavis.muzavibackend.domain.usermodule.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class RecApiResponseDto {
    private Map<String, Double> rawScores;
    private Map<String, Double> probabilities;
    private Integer assignedCluster;
    private Map<String, Double> clusterProfile;
}

