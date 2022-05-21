package com.bootcamp.seventhtrialexcel.domain;

import lombok.Data;

@Data
public class Excel {
    private Long seq;
    private String workCode;
    private String workName;
    private String company;
    private String manager;
    private String rank;
    private String category;
    private String phone;
}
