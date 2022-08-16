package com.study.devstudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {

    @GetMapping(path="/get", params="register")
    public String registerForm() {
        log.info("registerForm");
        return "board/register";
    } // http://localhost:8081/board/register

    @PostMapping(path = "/post", params = "register")
    public String register() {
        log.info("register");
        return "board/list";
    }

    @GetMapping(path = "/get", params = "modify")
    public String modifyForm() {
        log.info("modifyForm");
        return "board/modify";
    } // http://localhost:8081/board/modify

    @PostMapping(path = "/post", params = "modify")
    public String modify() {
        log.info("modify");
        return "board/list";
    }

    @GetMapping(path = "/get", params = "remove")
    public String removeForm() {
        log.info("removeForm");
        return "board/remove";
    } // http://localhost:8081/board/remove

    @PostMapping(path = "/post", params = "remove")
    public String remove() {
        log.info("remove");
        return "board/list";
    }

    @GetMapping(path = "/get", params = "list")
    public String list() {
        log.info("list");
        return "board/list";
    } // http://localhost:8081/board/list

    @RequestMapping(path = "/get", params = "read")
    public String read() {
        log.info("read");
        return "board/read";
    } // http://localhost:8081/board/read/

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
