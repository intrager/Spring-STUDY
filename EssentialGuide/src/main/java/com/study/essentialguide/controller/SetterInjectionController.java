package com.study.essentialguide.controller;

import com.study.essentialguide.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SetterInjectionController {
    /**
     * setter 메서드를 통한 의존성 주입
     */
    MyService myService;

    @Autowired
    public void setMyService(MyService myService) {
        this.myService = myService;
    }
}
