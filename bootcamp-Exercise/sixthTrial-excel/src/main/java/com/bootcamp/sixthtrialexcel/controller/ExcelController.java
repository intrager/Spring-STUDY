package com.bootcamp.sixthtrialexcel.controller;

import org.bouncycastle.asn1.ocsp.BasicOCSPResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/excel")
public class ExcelController {

    @RequestMapping(value = "/excel")
    public String excelIndex(HttpServletRequest request, ModelMap model) throws Exception {
        return "/excel/uploadExcel";
    }
}
