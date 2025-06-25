package com.sejong.muzavis.muzavibackend.domain.usermodule.entities;

import jakarta.persistence.*;

import java.util.List;

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
}
