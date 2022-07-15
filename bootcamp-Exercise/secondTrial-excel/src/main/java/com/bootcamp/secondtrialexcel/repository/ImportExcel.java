package com.bootcamp.secondtrialexcel.repository;

import com.bootcamp.secondtrialexcel.domain.Excel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Repository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class ImportExcel {
    private final static String excel2003 = ".xls";
    private final static String excel2007 = ".xlsx";

    public List<Map<String, String>> getListByExcel(InputStream in, String fileName) throws Exception {
        List<Map<String, String>> list = null;

        // Excel
        Workbook workbook = this.getWorkbook(in, fileName);
        if(null == workbook) {
            throw new Exception("Empty Excel file");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;

        list = new ArrayList<Map<String, String>>();

        for(int i = 0; i < workbook.getNumberOfSheets(); i++) {
            sheet = workbook.getSheetAt(i);
            if(sheet == null) continue;

            for(int j = sheet.getFirstRowNum(); j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if(row == null || row.getFirstCellNum() == j) continue;

                List<Map<String, String>> li = new ArrayList<Map<String, String>>();
                for(int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    list.add((Map<String, String>) cell);
                }
                list.add((Map<String, String>) li);
            }
        }

        return list;
    }

    // excel
    public Workbook getWorkbook(InputStream inStr, String fileName) throws Exception {
        Workbook wb = null;

        String fileType = fileName.substring(fileName.lastIndexOf("."));

        if(excel2003.equals(fileType)) {
            wb = new HSSFWorkbook(inStr);
        } else if(excel2007.equals(fileType)) {
            wb = new XSSFWorkbook(inStr);
        } else {
            throw new Exception(" Nope ");
        }
        return wb;
    }
}
