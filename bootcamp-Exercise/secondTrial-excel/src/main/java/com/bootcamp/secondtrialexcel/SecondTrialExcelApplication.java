package com.bootcamp.secondtrialexcel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecondTrialExcelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecondTrialExcelApplication.class, args);
    }

}

/*
    https://intrepidgeeks.com/tutorial/springboot-uses-poi-to-import-excel-file-data-into-mysql-database
    --> class org.apache.poi.xssf.usermodel.XSSFCell cannot be cast to class java.util.List
    (org.apache.poi.xssf.usermodel.XSSFCell is in unnamed module of loader 'app'; java.util.List is in module java.base of loader 'bootstrap')
 */