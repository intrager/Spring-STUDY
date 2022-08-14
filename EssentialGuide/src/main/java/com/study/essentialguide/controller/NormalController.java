package com.study.essentialguide.controller;

import com.study.essentialguide.service.MyService;
import com.study.essentialguide.service.MyServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NormalController {
    /**
     * 일반적인 자바 코드에서의 객체 사용법
     */
    private MyService service = new MyServiceImpl();

    @GetMapping("/not-di/hello")
    public String getHello() {
        return service.getHello();
    }
}
