package com.study.devstudy.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of="sno")
public class Student {
    private int sno;
    private String name;
}
