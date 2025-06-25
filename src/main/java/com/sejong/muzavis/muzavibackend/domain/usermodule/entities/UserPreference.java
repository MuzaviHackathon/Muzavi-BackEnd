package com.sejong.muzavis.muzavibackend.domain.usermodule.entities;

import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Entity
@Table(name = "user_preferences")
public class UserPreference {

    public UserPreference(User user, String category, String optionValue) {
        this.user = user;
        this.category = category;
        this.optionValue = optionValue;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String category;
    private String optionValue;
}
