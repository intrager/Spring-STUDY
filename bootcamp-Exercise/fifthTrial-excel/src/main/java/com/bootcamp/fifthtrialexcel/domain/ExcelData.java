package com.bootcamp.fifthtrialexcel.domain;

import lombok.Data;

@Data
public class ExcelData {
    private Long seq;
    private String workCode;
    private String workName;
    private String company;
    private String manager;
    private String rank;
    private String category;
    private String phone;
}
