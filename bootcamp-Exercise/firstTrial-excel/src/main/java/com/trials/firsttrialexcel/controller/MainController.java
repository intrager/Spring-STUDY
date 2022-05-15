package com.trials.firsttrialexcel.controller;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping("")
    public String index() {
        return "/main/index";
    }

    @GetMapping(value = "/excel/download", produces = MediaType.ALL_VALUE)
    public void excelDownload(HttpServletResponse response) throws IOException {
        /**
         *   .xls -> HSSFWorkbook
         *   .xlsx -> XSSFWorkbook
         */
        Workbook wb = new XSSFWorkbook();
        Sheet sheet = wb.createSheet("첫 번째 시트");
        Row row = null;
        Cell cell = null;
        int rowNum = 0;

        // Header
        row = sheet.createRow(rowNum++);
        cell = row.createCell(0);
        cell.setCellValue("업무코드");
        cell = row.createCell(1);
        cell.setCellValue("업무명");
        cell = row.createCell(2);
        cell.setCellValue("회사");
        cell = row.createCell(3);
        cell.setCellValue("담당자");
        cell = row.createCell(4);
        cell.setCellValue("직급");
        cell = row.createCell(5);
        cell.setCellValue("종류");
        cell = row.createCell(6);
        cell.setCellValue("전화번호");

        // body
        for (int i = 0; i < row.getLastCellNum(); i++) {
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue(i + "_code");
            cell = row.createCell(1);
            cell.setCellValue(i + "_name");
            cell = row.createCell(2);
            cell.setCellValue(i + "_company");
            cell = row.createCell(3);
            cell.setCellValue(i + "_manager");
            cell = row.createCell(4);
            cell.setCellValue(i + "_rank");
            cell = row.createCell(5);
            cell.setCellValue(i + "_category");
            cell = row.createCell(6);
            cell.setCellValue(i + "_phone");

            // 컨텐츠 타입과 파일명 지정
            response.setContentType("ms-vnd/excel");
            response.setHeader("Content-Disposition", "attachment;filename=\"bootcamp.xlsx\"");

            // Excel File Output
            wb.write(response.getOutputStream());
            wb.close();
        }
    }
}

/*
    org.springframework.http.converter.HttpMessageNotWritableException: No converter for [class java.util.LinkedHashMap] with preset Content-Type 'ms-vnd/excel']
    --> @GETMapping 옆에 produces = MediaType.APPLICATION_JSON_VALUE 추가

    Cannot render error page for request [/excel/download] as the response has already been committed. As a result, the response may have the wrong status code.
    -->
 */