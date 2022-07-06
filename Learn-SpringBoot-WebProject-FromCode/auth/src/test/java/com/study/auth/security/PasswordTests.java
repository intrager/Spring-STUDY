package com.study.auth.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTests {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncode() {
        String password = "1q2w3e4r";
        String enPw = passwordEncoder.encode(password);

        System.out.println("enPw : " + enPw);

        boolean matchResult = passwordEncoder.matches(password, enPw);

        System.out.println("matchResult: " + matchResult);
    }
}
/*
첫 번째 실행
enPw : $2a$10$qattGM7T.QuhifGVg6.1JeAI92yee53p0pCnDRsfvJo12QhRlEcEO
matchResult: true

두 번째 실행
enPw : $2a$10$0Lq.XKfrqxIPoLxFm0Zxz.ZpXUg9zHsECEYPRAW9F1xjePS6Y.lu2
matchResult: true
 */
