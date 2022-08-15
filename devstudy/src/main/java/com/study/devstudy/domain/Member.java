package com.study.devstudy.domain;

import lombok.*;

@ToString
public class Member {
    private String uid;
    private String pwd;
    private String name;

    @Builder
    public Member(String uid, String pwd) {
        super();
        this.uid = uid;
        this.pwd = pwd;
    }
}

