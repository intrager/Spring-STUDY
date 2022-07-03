package com.study.hellospring.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/css/**", "/js/**", "/img/**").permitAll()
				.antMatchers("/guest/**").permitAll()
				.antMatchers("/member/**").hasAnyRole("USER", "ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated();
		
		http.formLogin()
				.loginPage("/loginForm")			// default : login
				.loginProcessingUrl("/j_spring_security_check")
				.failureUrl("/loginError")			// default : /login?error
				.usernameParameter("j_username")	// 지정한 변수명으로 파라미터명 지정
				.passwordParameter("j_password")	// 지정한 변수명으로 파라미터명 지정
				.permitAll();
		
		http.logout()
				.logoutUrl("/logout")	// default
				.logoutSuccessUrl("/")
				.permitAll();
		
		/**
		 *  ssl을 사용하지 않으면 true로 사용
		 *  @EnableWebSecurity 어노테이션에 의해 CSRF 프로덕션이 활성화됨.
		 *  사이트 간 요청 위조(CSRF : Cross Site Request Forgery)는 
		 *  개발 중에는 불편할 수 있는 기능이므로
		 *  http에서 https에 대한 요청을 제출하는 등의 상황에서 잘못된 토큰 예외(403)를 발생시킴.
		 *  그러므로, 밑에 있는 줄처럼 꺼놓는 게 좋음
		 */
		http.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password(passwordEncoder().encode("1234")).roles("USER")
			.and()
			.withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
