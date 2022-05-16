package com.trials.firsttrialexcel.excel;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component("sampleXlsx")
public class SampleXlsView extends AbstractXlsView {
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

        /**
         *   .xls -> HSSFWorkbook
         *   .xlsx -> XSSFWorkbook
         */
        response.setHeader("Content-Disposition", "attachment;filename=\"bootcamp.xlsx\"");

        CellStyle numberCellStyle = workbook.createCellStyle();
        DataFormat numberDataFormat = workbook.createDataFormat();
        numberCellStyle.setDataFormat(numberDataFormat.getFormat("#,##0"));

        Sheet sheet = workbook.createSheet("assignment_sheet");

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
        }
        // Excel File Output
        try {
            workbook.write(response.getOutputStream());
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}