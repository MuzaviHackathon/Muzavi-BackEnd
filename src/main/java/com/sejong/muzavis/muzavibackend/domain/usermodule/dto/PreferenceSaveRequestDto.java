package com.sejong.muzavis.muzavibackend.domain.usermodule.dto;

import jakarta.validation.Valid;

import java.util.List;

public record PreferenceSaveRequestDto(List<@Valid PreferenceDto> preferences) {
}
