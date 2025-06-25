package com.sejong.muzavis.muzavibackend.domain.classmodule.controller;

import com.sejong.muzavis.muzavibackend.domain.classmodule.service.CourseTagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course-tags")
public class CourseTagController {

    private final CourseTagService courseTagService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadTags(@RequestPart("file") MultipartFile file) {
        try {
            courseTagService.importTagsFromCsv(file);
            return ResponseEntity.ok("태그 업로드 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("업로드 실패: " + e.getMessage());
        }
    }
}
