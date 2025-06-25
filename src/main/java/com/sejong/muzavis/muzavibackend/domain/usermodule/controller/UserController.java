package com.sejong.muzavis.muzavibackend.domain.usermodule.controller;

import com.sejong.muzavis.muzavibackend.domain.usermodule.dto.UserDto;
import com.sejong.muzavis.muzavibackend.domain.usermodule.entities.User;
import com.sejong.muzavis.muzavibackend.domain.usermodule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Long> registerUser(@RequestBody UserDto userDto) {
        User savedUser = userService.registerUser(userDto);
        return ResponseEntity.ok(savedUser.getId());
    }

    @PostMapping("/{userId}/upload-file")
    public ResponseEntity<Void> uploadCompletedCourses(
            @PathVariable Long userId,
            //키 이름 'file'로 맞춰야함
            @RequestPart("file") MultipartFile file){

        userService.uploadCompletedCourseFile(userId, file);
        return ResponseEntity.ok().build();
    }
}
