package com.sejong.muzavis.muzavibackend.domain.classmodule.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "professors")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String department;
}
