package com.study.devstudy.domain;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@ToString
@Data
public class Board implements Serializable { // 클래스는 직렬화되어야 한다.
//    private static final long serialVersionUID = 1L;

    private int bno;
    private String title;
    private String contents;
    private String writer;
    private LocalDateTime regDate;
}
