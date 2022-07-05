package com.example.auth.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/sample/all").permitAll()
                .antMatchers("/sample/member").hasRole("USER");

        /*
            HttpSecurity -> formLogin()
            인가/인증 절차에서 문제가 발생했을 때 로그인 페이지를 보여주도록 지정할 수 있고,
            화면으로 로그인 방식을 지원한다는 의미로 사용됨.
         */
        http.formLogin();   // 인가, 인증에 문제 시 로그인 화면
        http.csrf().disable();
        http.logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 사용자 계정은 user1
        auth.inMemoryAuthentication().withUser("user1")
            .password("$2a$10$0Lq.XKfrqxIPoLxFm0Zxz.ZpXUg9zHsECEYPRAW9F1xjePS6Y.lu2")
            .roles("USER");
    }
}
