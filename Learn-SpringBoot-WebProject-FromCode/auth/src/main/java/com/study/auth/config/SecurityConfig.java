package com.study.auth.config;

import com.study.auth.security.handler.AuthLoginSuccessHandler;
import com.study.auth.security.service.AuthUserDetailsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Log4j2
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthUserDetailsService authUserDetailsService;

    public SecurityConfig(AuthUserDetailsService authUserDetailsService) {
        this.authUserDetailsService = authUserDetailsService;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
            HttpSecurity -> formLogin()
            인가/인증 절차에서 문제가 발생했을 때 로그인 페이지를 보여주도록 지정할 수 있고,
            화면으로 로그인 방식을 지원한다는 의미로 사용됨.
         */
        http.formLogin();   // 인가, 인증에 문제 시 로그인 화면
        http.csrf().disable();
        http.logout();

        http.oauth2Login().successHandler(successHandler());
        http.rememberMe().tokenValiditySeconds(60 * 60 * 24 * 7)    // 소셜 로그인은 remember-me를 사용할 수 없음
                .userDetailsService(authUserDetailsService);    // 7 days
    }

    @Bean
    public AuthLoginSuccessHandler successHandler() {
        return new AuthLoginSuccessHandler(passwordEncoder());
    }
}
