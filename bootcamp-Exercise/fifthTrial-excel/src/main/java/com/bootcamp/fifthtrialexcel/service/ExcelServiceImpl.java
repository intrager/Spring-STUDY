package com.bootcamp.fifthtrialexcel.service;

import com.bootcamp.fifthtrialexcel.config.ExcelReadOption;
import com.bootcamp.fifthtrialexcel.domain.Excel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelServiceImpl implements ExcelService {

    @Resource(name="excelMapper")
    private ExcelMapper excelDAO;


    @Override
    public void uploadExcel(File destFile) throws Exception {
        ExcelReadOption excelReadOption = new ExcelReadOption();
        excelReadOption.setFilePath(destFile.getAbsolutePath());    // 파일 경로 추가
        excelReadOption.setOutputColumns("A", "B", "C", "D", "E", "F", "G");    // 추출할 컬럼명 추가
        excelReadOption.setStartRow(2); // 시작행(헤더 부분 제외)

        List<Map<String, String>> excelContent = ExcelRead.read(excelReadOption);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("excelContent", excelContent);

        try {
            excelDAO.create((Excel) param);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
