package com.sejong.muzavis.muzavibackend.domain.usermodule.entities;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String major;
    private Integer semester_taken;

    @OneToMany(mappedBy = "user")
    private List<UserPreference> preferences;

    @Builder
    public User(String name, String major, Integer semester_taken) {
        this.name = name;
        this.major = major;
        this.semester_taken = semester_taken;
    }
}
