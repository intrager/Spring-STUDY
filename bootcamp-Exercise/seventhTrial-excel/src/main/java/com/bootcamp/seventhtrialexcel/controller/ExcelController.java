package com.bootcamp.seventhtrialexcel.controller;

import com.bootcamp.seventhtrialexcel.service.ExcelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/excel")
public class ExcelController {

    private final ExcelService excelService;

    public ExcelController(ExcelService excelService) {
        this.excelService = excelService;
    }

    @GetMapping("")
    public String indexPage() {
        return "/excel/uploadExcel";
    }

    @PostMapping("/import")
    public String importExcel(@RequestParam(value = "fileName")MultipartFile file, HttpSession session) {
        boolean a = false;
        String fileName = file.getOriginalFilename();

        try {
            a = excelService.batchImport(fileName, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/excel/uploadExcel";
    }
}