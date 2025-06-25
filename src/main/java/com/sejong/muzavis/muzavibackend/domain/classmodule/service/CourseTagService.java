package com.sejong.muzavis.muzavibackend.domain.classmodule.service;

import com.sejong.muzavis.muzavibackend.domain.classmodule.entities.CourseTag;
import com.sejong.muzavis.muzavibackend.domain.classmodule.repository.CourseRepository;
import com.sejong.muzavis.muzavibackend.domain.classmodule.repository.CourseTagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class CourseTagService {

    private final CourseRepository courseRepository;
    private final CourseTagRepository courseTagRepository;

    @Transactional
    public void importTagsFromCsv(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                if (row++ == 0) continue; // 헤더 건너뛰기

                String[] columns = line.split(",", -1);
                if (columns.length < 4) continue;

                String courseName = columns[1].trim();
                String rawTags = columns[3].trim();

                // Course가 존재하는 경우에만 진행
                courseRepository.findByName(courseName).ifPresent(course -> {
                    // ['tag1', 'tag2', ...] 형태에서 태그 추출
                    String cleaned = rawTags.replaceAll("[\\[\\]']", "");
                    String[] tags = cleaned.split(",");

                    for (String tag : tags) {
                        String trimmedTag = tag.trim();
                        if (!trimmedTag.isEmpty()) {
                            CourseTag tagEntity = new CourseTag();
                            tagEntity.setCourse(course);
                            tagEntity.setTagName(trimmedTag);
                            courseTagRepository.save(tagEntity);
                        }
                    }
                });
            }

        } catch (IOException e) {
            throw new RuntimeException("CSV 파싱 오류: " + e.getMessage(), e);
        }
    }
}
