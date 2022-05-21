package com.bootcamp.seventhtrialexcel.mapper;

import com.bootcamp.seventhtrialexcel.domain.Excel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExcelMapper {
    void addExcel(Excel excel);
}
