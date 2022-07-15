package com.bootcamp.seventhtrialexcel.service;

import com.bootcamp.seventhtrialexcel.config.MyException;
import com.bootcamp.seventhtrialexcel.domain.Excel;
import com.bootcamp.seventhtrialexcel.mapper.ExcelMapper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Autowired
    private ExcelMapper excelMapper;

    @Override
    public void addExcel(Excel excel) {
        excelMapper.addExcel(excel);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    @Override
    public boolean batchImport(String fileName, MultipartFile file) throws Exception {
        boolean notNull = false;
        List<Excel> excelList = new ArrayList<>();
        if(!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$"))
            throw new MyException("The upload file format is incorrect.");

        boolean isExcel2003 = true;
        if(fileName.matches("^.+\\.(?i)(xlsx)$"))
            isExcel2003 = false;

        InputStream is = file.getInputStream();
        Workbook wb = null;
        if(isExcel2003) wb = new HSSFWorkbook(is);
        else wb = new XSSFWorkbook(is);

        Sheet sheet = wb.getSheetAt(0);
        if(sheet != null) notNull = true;

        Excel excel;
        // 'i = 2' means to loop from the third row. If your third row starts with data.
        for(int i = 2; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);  // Get the row object through the sheet form object
            if(row == null) continue;

            /**
             * The value of 'sheet.getLastRowNum()' is 10, so there are at least 10 data in the Excel sheet.
             * Otherwise an error NullPointerException will be reported.
             */
            excel = new Excel();
            // When looping, get the cell of each row for judgment
            if(row.getCell(0).getCellType() != 1)
                throw new MyException("Import failed (line" + (i + 1) + ", please set the excel name in text format");

            // Get the value of the first cell in each row
            String workCode = row.getCell(0).getStringCellValue();
            // Determine whether it is empty

            // Get the value of the second cell of each row
            row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
            String workName = row.getCell(1).getStringCellValue();

            // Get the value of the second cell of each row
            row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
            String company = row.getCell(2).getStringCellValue();

            // Get the value of the second cell of each row
            row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
            String manager = row.getCell(3).getStringCellValue();

            // Get the value of the second cell of each row
            row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
            String rank = row.getCell(4).getStringCellValue();

            // Get the value of the second cell of each row
            row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
            String category = row.getCell(5).getStringCellValue();

            // Get the value of the second cell of each row
            row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
            String phone = row.getCell(6).getStringCellValue();

            // A complete loop an object once
            excel.setWorkCode(workCode);
            excel.setWorkName(workName);
            excel.setCompany(company);
            excel.setManager(manager);
            excel.setRank(rank);
            excel.setCategory(category);
            excel.setPhone(phone);
            excelList.add(excel);
        }

        for(Excel record : excelList) {
            excelMapper.addExcel(record);
        }

        return notNull;
    }
}
