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

    private String name; //이름
    private String major; //학과
    private Integer semester_taken; //이수 학기
    private String returnSemester; //재학 예정 학기
    private String studentNumber; //학번
    private String preferredJob; //희망 진로 분야

    @OneToMany(mappedBy = "user")
    private List<UserPreference> preferences;

    @Builder
    public User(String name, String major, Integer semester_taken, String returnSemester, String studentNumber, String preferredJob) {
        this.name = name;
        this.major = major;
        this.semester_taken = semester_taken;
        this.returnSemester = returnSemester;
        this.studentNumber = studentNumber;
        this.preferredJob = preferredJob;
    }
}
