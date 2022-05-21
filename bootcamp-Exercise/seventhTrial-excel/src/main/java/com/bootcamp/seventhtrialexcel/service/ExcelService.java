package com.bootcamp.seventhtrialexcel.service;

import com.bootcamp.seventhtrialexcel.domain.Excel;
import org.springframework.web.multipart.MultipartFile;

public interface ExcelService {
    void addExcel(Excel excel);
    boolean batchImport(String fileName, MultipartFile file) throws Exception;
}