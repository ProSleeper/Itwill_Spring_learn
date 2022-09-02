package com.exe.board.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
   @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       
    	
    	//인증되지 않은 모든 요청을 허락
    	http.authorizeRequests().antMatchers("/**").permitAll()
    	.and()
    	.csrf().ignoringAntMatchers("/h2-console/**")
    	//.disable().headers().frameOptions().disable();
    	.and()
    	.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN));
   
    	
        return http.build();
    }
	
}


/*
CSRF(Cross Site Request Forgery)
웹사이트의 취약점 공격을 방지하기 위해 사용하는 기술
스프링 시큐어리티가 CSRF 토큰 값을 발행해서
*/