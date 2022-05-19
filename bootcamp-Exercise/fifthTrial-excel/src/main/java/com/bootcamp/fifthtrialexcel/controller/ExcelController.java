package com.bootcamp.fifthtrialexcel.controller;

import com.bootcamp.fifthtrialexcel.domain.ExcelData;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ExcelController {

    @GetMapping("/excel")
    public String enterExcelPage() {
        return "excel/excel";
    }

    @PostMapping("/excel/read")
    public String readExcel(@RequestParam("file") MultipartFile file, Model model) throws IOException {
        List<ExcelData> dataList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if(!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀 파일만 업로드 해주세요");
        }

        Workbook workbook = null;

        if(extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if(extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet sheet = workbook.getSheetAt(0);

        // 행의 개수를 for문으로 돌림
        for(int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);

            ExcelData data = new ExcelData();

            // data.setSeq((long) row.getCell(0).getNumericCellValue());
            data.setWorkCode(row.getCell(0).getStringCellValue());
            data.setWorkName(row.getCell(1).getStringCellValue());
            data.setCompany(row.getCell(2).getStringCellValue());
            data.setManager(row.getCell(3).getStringCellValue());
            data.setRank(row.getCell(4).getStringCellValue());
            data.setCategory(row.getCell(5).getStringCellValue());
            data.setPhone(row.getCell(6).getStringCellValue());

            dataList.add(data);
        }
        model.addAttribute("list", dataList);

        return "excel/excelList";
    }
}
