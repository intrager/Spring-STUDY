package org.study.ex1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 별도의 화면 없이 데이터 전송
public class SampleController {

    @GetMapping("/hello")
    public String[] hello() {
        return new String[]{"Han", "Bruce"};
    }
}
