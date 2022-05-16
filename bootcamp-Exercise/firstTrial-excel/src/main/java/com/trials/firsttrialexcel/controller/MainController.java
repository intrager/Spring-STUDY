package com.trials.firsttrialexcel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class MainController {
    @GetMapping("")
    public String index() {
        return "/main/index";
    }

    @GetMapping(path = "/excel/download", produces = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
    public String excelDownload() {
        return "sampleXlsx";
    }
}

