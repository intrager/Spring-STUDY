package com.bootcamptwo.assignment.controller;

import com.bootcamptwo.assignment.domain.ResultVO;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello test";
    }

    @GetMapping("/hello2")
    public String hello2() {
        return "Hello GET test";
    }

    @GetMapping("/hello3")  // GET hello3?name=bruce
    public String hello3(@RequestParam("name") String name) {
        return "Hello " + name;
    }

    @GetMapping("/hello31/{name}")  // GET hello31/bruce
    public String hello31(@PathVariable String name) {
        return "Hello " + name;
    }

    @PostMapping("/hello32")    // POST hello32?name=bruce
    public String hello32(@RequestParam("name") String name) {
        return "Hello " + name;
    }

    @PostMapping("/hello4")     // POST : x-www-form-urlencoded --> name bruce
    public String hello4(@RequestParam String name) {
        return "Hello " + name;
    }

    /*
        객체를 리턴하게 되면 Spring Framework에서 jackson mapper 라이브러리가
        자바 객체를 json으로 변환해주는 역할을 수행하게됨
     */
    @PostMapping("/hello5")     // POST : x-www-form-urlencoded --> name bruce
    public ResultVO hello5(@RequestParam("name") String name) {
        ResultVO result = new ResultVO(0, name);
        return result;
    }

    /*
        @RequestBody ResultVO result는 Request의 Body를 받아서 그것을 ResultVO 객체로 변환한다는 의미
     */
    @PostMapping("/hello6")
    public ResultVO hello6(@RequestBody ResultVO result) {
        return result;
    }


}
