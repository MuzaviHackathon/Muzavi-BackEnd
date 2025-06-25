package com.sejong.muzavis.muzavibackend.domain.usermodule.dto;

import com.sejong.muzavis.muzavibackend.domain.usermodule.entities.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String name;
    private String major;
    private Integer semesterTaken;   // Java 스타일로 카멜 케이스 권장


    public User toEntity() {

        return User.builder()
                .name(name)
                .major(major)
                .semester_taken(semesterTaken)
                .build();
    }

    public static UserDto fromEntity(User entity) {
        return UserDto.builder()
                .name(entity.getName())
                .major(entity.getMajor())
                .semesterTaken(entity.getSemester_taken())
                .build();
    }
}