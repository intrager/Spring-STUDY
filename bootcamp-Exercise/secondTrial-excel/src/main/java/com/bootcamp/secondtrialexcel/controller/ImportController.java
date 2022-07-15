package com.bootcamp.secondtrialexcel.controller;

import com.bootcamp.secondtrialexcel.domain.Excel;
import com.bootcamp.secondtrialexcel.repository.ImportExcel;
import com.bootcamp.secondtrialexcel.service.ExcelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
public class ImportController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @Autowired
    private ImportExcel importExcel;
    @Autowired
    private ExcelMapper excelMapper;

//    public ImportController(ImportExcel importExcel, ExcelMapper excelMapper) {
//        this.importExcel = importExcel;
//        this.excelMapper = excelMapper;
//    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public @ResponseBody String uploadExcel(HttpServletRequest request) throws Exception {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        InputStream inputStream = null;
        List<Map<String, String>> list = null;
        MultipartFile file = multipartRequest.getFile("filename");

        if(file.isEmpty()) return "index";

        inputStream = file.getInputStream();
        list = importExcel.getListByExcel(inputStream, file.getOriginalFilename());
        inputStream.close();

        for(int i = 0; i < list.size(); i++) {
            List input = Collections.singletonList(list.get(i));
            excelMapper.insert(String.valueOf(input.get(0)), String.valueOf(input.get(1)),
                    String.valueOf(input.get(2)), String.valueOf(input.get(3)),
                    String.valueOf(input.get(4)), String.valueOf(input.get(5)),
                    String.valueOf(input.get(6)));
        }

        return "index";
    }
}
