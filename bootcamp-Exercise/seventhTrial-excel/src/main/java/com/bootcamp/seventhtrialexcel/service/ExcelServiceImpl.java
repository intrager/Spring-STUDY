package com.bootcamp.seventhtrialexcel.service;

import com.bootcamp.seventhtrialexcel.domain.Excel;
import com.bootcamp.seventhtrialexcel.mapper.ExcelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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


    }
}
