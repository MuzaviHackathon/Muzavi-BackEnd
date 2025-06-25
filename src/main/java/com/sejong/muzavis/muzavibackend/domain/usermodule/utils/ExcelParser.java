package com.sejong.muzavis.muzavibackend.domain.usermodule.utils;

import com.sejong.muzavis.muzavibackend.domain.usermodule.dto.CourseRow;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelParser {

    private static final int START_ROW   = 4;  // 5행부터
    private static final int COL_CODE    = 3;  // D열 = 학수번호
    private static final int COL_TITLE   = 4;  // E열 = 교과목명

    private final DataFormatter formatter = new DataFormatter();

    /** 학수번호 + 과목명을 함께 추출 */
    public List<CourseRow> parseCourseRows(MultipartFile file) {
        List<CourseRow> result = new ArrayList<>();

        try (Workbook wb = WorkbookFactory.create(file.getInputStream())) {
            Sheet sheet = wb.getSheetAt(0);

            for (int r = START_ROW; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;

                // 1) 학수번호(D열)
                Cell codeCell = row.getCell(COL_CODE, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                // 2) 과목명(E열)
                Cell titleCell = row.getCell(COL_TITLE, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);

                if (codeCell == null || titleCell == null) continue;

                String code  = formatter.formatCellValue(codeCell).trim();
                String title = formatter.formatCellValue(titleCell).trim();

                if (!code.isEmpty() && !title.isEmpty()) {
                    result.add(new CourseRow(code, title));
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException("엑셀 파싱 실패", e);
        }

        return result;
    }
}