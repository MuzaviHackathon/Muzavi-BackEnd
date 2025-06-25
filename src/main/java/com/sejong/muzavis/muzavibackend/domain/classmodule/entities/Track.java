package com.sejong.muzavis.muzavibackend.domain.classmodule.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "tracks")
public class Track {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String department;

    @OneToMany(mappedBy = "track", cascade = CascadeType.ALL)
    private List<TrackSubject> trackCourses;
}
