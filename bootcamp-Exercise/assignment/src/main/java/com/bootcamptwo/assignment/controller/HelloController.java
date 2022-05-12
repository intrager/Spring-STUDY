package com.bootcamptwo.assignment.controller;

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

}
