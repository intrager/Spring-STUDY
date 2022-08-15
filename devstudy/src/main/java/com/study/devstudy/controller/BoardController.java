package com.study.devstudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping("/register")
    public String registerForm() {
        log.info("registerForm");
        return "success";
    } // http://localhost:8081/board/register

    @PostMapping("/register")
    public String register() {
        log.info("register");
        return "success";
    }

    @GetMapping("/modify")
    public String modifyForm() {
        log.info("modifyForm");
        return "success";
    } // http://localhost:8081/board/modify

    @PostMapping("/modify")
    public String modify() {
        log.info("modify");
        return "success";
    }

    @PostMapping("/remove")
    public String remove() {
        log.info("remove");
        return "success";
    }

    @GetMapping("/list")
    public String list() {
        log.info("list");
        return "success";
    } // http://localhost:8081/board/list

    /**
     * 위의 @PathVariable을 빼면 {bno}가 매핑이 안 됨
     * 만약, 경로 변수명{bno}과 메서드 파라미터명 bno이 일치하지 않으면
     * @PathVariable옆에 ("bno")이런 식으로 속성값을 놓으면 됨
     */
    @RequestMapping("/read/{bno}")
    public String read(@PathVariable("bno") int bno) {
        log.info("read bno : " + bno);
        return "board/read";
    } // http://localhost:8081/board/read/100
}
