package com.study.auth.controller;

import com.study.auth.security.dto.AuthMemberDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/sample/")
public class SampleController {
    @GetMapping("/all")
    public void exAll() {
        log.info("exAll-------------");
    }

    @GetMapping("/member")
    public void exMember(@AuthenticationPrincipal AuthMemberDTO authMemberDTO) {
        log.info("exMember----------");

        log.info("-------------------------");
        log.info(authMemberDTO);    // 7월7일 00:55 "오 이렇게 로그를 찍어볼 수 있겠구나"
    }

    @GetMapping("/admin")
    public void exAdmin() {
        log.info("exAdmin-----------");
    }
}
