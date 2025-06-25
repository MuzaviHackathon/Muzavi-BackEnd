package com.sejong.muzavis.muzavibackend.domain.classmodule.controller;

import com.sejong.muzavis.muzavibackend.domain.classmodule.entities.Course;
import com.sejong.muzavis.muzavibackend.domain.classmodule.service.CourseExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
public class CourseUploadController {

    private final CourseExcelService courseExcelService;

    @PostMapping("/upload1")
    public ResponseEntity<String> uploadExcelFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("year") int year,
            @RequestParam("semester") String semesterText
    ) {
        try {
            Course.Semester semester = parseSemester(semesterText);
            courseExcelService.importCoursesFromExcel1(file.getInputStream(), year, semester);
            return ResponseEntity.ok("강의 정보 업로드 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("업로드 실패: " + e.getMessage());
        }
    }

    @PostMapping("/upload2")
    public ResponseEntity<String> uploadExcelFile1(
            @RequestParam("file") MultipartFile file,
            @RequestParam("year") int year,
            @RequestParam("semester") String semesterText
    ) {
        try {
            Course.Semester semester = parseSemester(semesterText);
            courseExcelService.importCoursesFromExcel2(file.getInputStream(), year, semester);
            return ResponseEntity.ok("강의 정보 업로드 성공");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("업로드 실패: " + e.getMessage());
        }
    }

    private Course.Semester parseSemester(String text) {
        return switch (text.trim()) {
            case "1" -> Course.Semester._1학기;
            case "2" -> Course.Semester._2학기;
            case "여름" -> Course.Semester.여름학기;
            case "겨울" -> Course.Semester.겨울학기;
            default -> throw new IllegalArgumentException("학기 파라미터 오류: " + text);
        };
    }
}
