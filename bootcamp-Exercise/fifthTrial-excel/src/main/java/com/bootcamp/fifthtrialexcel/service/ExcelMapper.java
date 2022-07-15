package com.bootcamp.fifthtrialexcel.service;

import com.bootcamp.fifthtrialexcel.domain.Excel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
public interface ExcelMapper {
    void create(Excel excel) throws Exception;
}
