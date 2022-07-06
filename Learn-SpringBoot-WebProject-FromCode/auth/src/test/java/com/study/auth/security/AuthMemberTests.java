package com.study.auth.security;

import com.study.auth.entity.AuthMemberEntity;
import com.study.auth.entity.AuthMemberRole;
import com.study.auth.repository.AuthMemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class AuthMemberTests {
    @Autowired
    private AuthMemberRepository authMemberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void insertDummies() {
        // 1 ~ 80 : USER
        // 81 ~ 90 : USER, MANAGER
        // 91 ~ 100 : USER, MANAGER, ADMIN
        IntStream.rangeClosed(1, 100).forEach(i -> {
            AuthMemberEntity authMemberEntity = AuthMemberEntity.builder()
                    .email("user" + i + "@study.org")
                    .name(i + "번 사용자")
                    .fromSocial(false)
                    .password(passwordEncoder.encode("1234"))
                    .build();
            // Default Role
            authMemberEntity.addMemberRole(AuthMemberRole.USER);

            if(i > 80) {
                authMemberEntity.addMemberRole(AuthMemberRole.MEMBER);
            }
            if(i > 90) {
                authMemberEntity.addMemberRole(AuthMemberRole.ADMIN);
            }
            authMemberRepository.save(authMemberEntity);
        });
    }

    @Test
    public void testRead() {
        Optional<AuthMemberEntity> result = authMemberRepository.findByEmail("user7@study.org", false);
        AuthMemberEntity authMemberEntity = result.get();

        System.out.println(authMemberEntity);
    }
}
