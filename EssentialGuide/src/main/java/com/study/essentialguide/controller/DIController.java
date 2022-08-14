package com.study.essentialguide.controller;

import com.study.essentialguide.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DIController {
    /**
     * 생성자를 통한 의존성 주입
     * 레퍼런스 객체 없이 객체를 초기화할 수 없게 설계할 수 있음
     */
    private final MyService myService;

    @Autowired
    public DIController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/di/hello")
    public String getHello() {
        return myService.getHello();
    }

}
