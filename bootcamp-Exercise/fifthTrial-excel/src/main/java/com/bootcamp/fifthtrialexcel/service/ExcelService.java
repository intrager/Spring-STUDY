package com.bootcamp.fifthtrialexcel.service;

import com.bootcamp.fifthtrialexcel.domain.Excel;

import java.util.List;

public interface ExcelService {
    int create(Excel excelDAO);
    Excel readById(Excel excelDAO);
    List<Excel> readAll();
    int update(Excel excelDAO);
    int delete(Excel excelDAO);
}
