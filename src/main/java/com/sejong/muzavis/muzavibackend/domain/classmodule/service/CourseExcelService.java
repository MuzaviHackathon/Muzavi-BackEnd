package com.sejong.muzavis.muzavibackend.domain.classmodule.service;

import com.sejong.muzavis.muzavibackend.domain.classmodule.entities.Course;
import com.sejong.muzavis.muzavibackend.domain.classmodule.entities.Professor;
import com.sejong.muzavis.muzavibackend.domain.classmodule.repository.CourseRepository;
import com.sejong.muzavis.muzavibackend.domain.classmodule.repository.ProfessorRepository;
import com.sejong.muzavis.muzavibackend.domain.classmodule.repository.TrackSubjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CourseExcelService {

    /* ─── 필요한 열 인덱스 ─── */
    private static final int COL_DEPARTMENT  = 2;
    private static final int COL_COURSE_CODE = 3;
    private static final int COL_NAME        = 5;
    private static final int COL_CLASSIF     = 6;
    private static final int COL_CREDIT      = 9;
    private static final int COL_DAY_TIME    = 14;
    private static final int COL_ROOM        = 15;
    private static final int COL_PROFESSOR   = 16;

    private static final int DATA_START_ROW = 1;   // 엑셀 2행부터 데이터

    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;
    private final TrackSubjectRepository trackSubjectRepository;

    public void importCoursesFromExcel1(InputStream input, int year, Course.Semester semester) {

        /* 0. 트랙 과목 세트 */
        Set<String> trackNames = new HashSet<>();
        trackSubjectRepository.findAll().forEach(t -> trackNames.add(t.getSubjectName().trim()));
        log.info("TrackSubject rows = {}", trackNames.size());

        DataFormatter fmt = new DataFormatter();

        /* 통계 */
        int rows = 0, ok = 0, skipTrack = 0, skipCredit = 0, skipReq = 0, skipDup = 0;

        try (Workbook wb = new XSSFWorkbook(input)) {
            Sheet sheet = wb.getSheetAt(0);

            for (int r = DATA_START_ROW; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;
                rows++;

                /* 1. 과목명 & 트랙 검사 */
                String name = fmt.formatCellValue(row.getCell(COL_NAME)).trim();
                boolean match = trackNames.stream()
                        .anyMatch(ts -> name.equalsIgnoreCase(ts) || name.contains(ts));
                if (!match) { skipTrack++; continue; }

                /* 2. 필드 추출 */
                String code   = fmt.formatCellValue(row.getCell(COL_COURSE_CODE)).trim();
                String dept   = fmt.formatCellValue(row.getCell(COL_DEPARTMENT )).trim();
                String clsf   = fmt.formatCellValue(row.getCell(COL_CLASSIF   )).trim();
                String profNm = fmt.formatCellValue(row.getCell(COL_PROFESSOR )).trim();
                String time   = fmt.formatCellValue(row.getCell(COL_DAY_TIME  )).trim();
                String room   = fmt.formatCellValue(row.getCell(COL_ROOM      )).trim();

                /* 3. 학점 파싱 (Double 허용) */
                int credit;
                try {
                    credit = (int) Math.round(
                            Double.parseDouble(fmt.formatCellValue(row.getCell(COL_CREDIT)).trim()));
                } catch (NumberFormatException e) {
                    skipCredit++; continue;
                }

                /* 4. 필수 & 중복 체크 */
                if (name.isEmpty() || code.isEmpty()) { skipReq++; continue; }
                if (courseRepository.findByNameAndCourseCode(name, code).isPresent()) { skipDup++; continue; }

                /* 5. 교수 확보 */
                Professor prof = professorRepository.findByName(profNm)
                        .orElseGet(() -> {
                            Professor p = new Professor();
                            p.setName(profNm);
                            p.setDepartment(dept);
                            return professorRepository.save(p);
                        });

                /* 6. Course 저장 */
                Course c = new Course();
                c.setName(name); c.setCourseCode(code); c.setDepartment(dept);
                c.setClassification(parseClassification(clsf));
                c.setCredit(credit); c.setSemester(semester);
                c.setDayTime(time); c.setRoom(room);
                c.setProfessor(prof); c.setRatingAvg(0.0);

                courseRepository.save(c);
                ok++;
                log.debug("INSERT R{}  {}", r, name);
            }

        } catch (Exception e) {
            log.error("엑셀 파싱 실패", e);
            throw new RuntimeException("엑셀 파싱 오류: " + e.getMessage(), e);
        }

        /* 최종 집계 */
        log.info("Excel rows:{}  saved:{}  "
                        + "skip[track:{} credit:{} required:{} duplicate:{}]",
                rows, ok, skipTrack, skipCredit, skipReq, skipDup);
    }

    private static final int COL_DAY_TIME1    = 13;
    private static final int COL_ROOM1        = 14;
    private static final int COL_PROFESSOR1   = 15;

    public void importCoursesFromExcel2(InputStream input, int year, Course.Semester semester) {

        /* 0. 트랙 과목 세트 */
        Set<String> trackNames = new HashSet<>();
        trackSubjectRepository.findAll().forEach(t -> trackNames.add(t.getSubjectName().trim()));
        log.info("TrackSubject rows = {}", trackNames.size());

        DataFormatter fmt = new DataFormatter();

        /* 통계 */
        int rows = 0, ok = 0, skipTrack = 0, skipCredit = 0, skipReq = 0, skipDup = 0;

        try (Workbook wb = new XSSFWorkbook(input)) {
            Sheet sheet = wb.getSheetAt(0);

            for (int r = DATA_START_ROW; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;
                rows++;

                /* 1. 과목명 & 트랙 검사 */
                String name = fmt.formatCellValue(row.getCell(COL_NAME)).trim();
                boolean match = trackNames.stream()
                        .anyMatch(ts -> name.equalsIgnoreCase(ts) || name.contains(ts));
                if (!match) { skipTrack++; continue; }

                /* 2. 필드 추출 */
                String code   = fmt.formatCellValue(row.getCell(COL_COURSE_CODE)).trim();
                String dept   = fmt.formatCellValue(row.getCell(COL_DEPARTMENT )).trim();
                String clsf   = fmt.formatCellValue(row.getCell(COL_CLASSIF   )).trim();
                String profNm = fmt.formatCellValue(row.getCell(COL_PROFESSOR1 )).trim();
                String time   = fmt.formatCellValue(row.getCell(COL_DAY_TIME1  )).trim();
                String room   = fmt.formatCellValue(row.getCell(COL_ROOM1     )).trim();

                /* 3. 학점 파싱 (Double 허용) */
                int credit;
                try {
                    credit = (int) Math.round(
                            Double.parseDouble(fmt.formatCellValue(row.getCell(COL_CREDIT)).trim()));
                } catch (NumberFormatException e) {
                    skipCredit++; continue;
                }

                /* 4. 필수 & 중복 체크 */
                if (name.isEmpty() || code.isEmpty()) { skipReq++; continue; }
                if (courseRepository.findByNameAndCourseCode(name, code).isPresent()) { skipDup++; continue; }

                /* 5. 교수 확보 */
                Professor prof = professorRepository.findByName(profNm)
                        .orElseGet(() -> {
                            Professor p = new Professor();
                            p.setName(profNm);
                            p.setDepartment(dept);
                            return professorRepository.save(p);
                        });

                /* 6. Course 저장 */
                Course c = new Course();
                c.setName(name); c.setCourseCode(code); c.setDepartment(dept);
                c.setClassification(parseClassification(clsf));
                c.setCredit(credit); c.setSemester(semester);
                c.setDayTime(time); c.setRoom(room);
                c.setProfessor(prof); c.setRatingAvg(0.0);

                courseRepository.save(c);
                ok++;
                log.debug("INSERT R{}  {}", r, name);
            }

        } catch (Exception e) {
            log.error("엑셀 파싱 실패", e);
            throw new RuntimeException("엑셀 파싱 오류: " + e.getMessage(), e);
        }

        /* 최종 집계 */
        log.info("Excel rows:{}  saved:{}  "
                        + "skip[track:{} credit:{} required:{} duplicate:{}]",
                rows, ok, skipTrack, skipCredit, skipReq, skipDup);
    }

    /* 이수구분 → enum */
    private Course.Classification parseClassification(String s) {
        if (s.contains("전필")) return Course.Classification.전필;
        if (s.contains("전선")) return Course.Classification.전선;
        if (s.contains("교양")) return Course.Classification.교양;
        if (s.contains("교선")) return Course.Classification.교선;
        return null;
    }
}
