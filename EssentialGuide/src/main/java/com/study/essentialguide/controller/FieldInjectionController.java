package com.study.essentialguide.controller;

import com.study.essentialguide.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FieldInjectionController {
    /**
     * 필드 객체 선언을 통한 의존성 주입
     */
    @Autowired
    private MyService myService;
}
