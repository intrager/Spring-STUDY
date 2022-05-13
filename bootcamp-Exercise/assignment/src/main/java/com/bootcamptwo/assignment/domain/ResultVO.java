package com.bootcamptwo.assignment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor // 모든 프로퍼티를 포함한 생성자를 대신 생성해줌
@Data
public class ResultVO {
    private Integer code;
    private String message;
}
