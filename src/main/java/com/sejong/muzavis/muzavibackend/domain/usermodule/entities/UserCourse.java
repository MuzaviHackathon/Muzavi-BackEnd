package com.sejong.muzavis.muzavibackend.domain.usermodule.entities;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name = "user_course")
public class UserCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String courseCode;
    private String courseName;

    public UserCourse(User user, String courseCode, String courseName) {
        this.user = user;
        this.courseCode = courseCode;
        this.courseName = courseName;
    }
}
