package com.study.hellospring.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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
				.permitAll();
		http.logout()
				.permitAll();
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("user").password(passwordEncoder().encode("1234")).roles("USER")
			.and()
			.withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");
	}
	
	@Bean
	BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
