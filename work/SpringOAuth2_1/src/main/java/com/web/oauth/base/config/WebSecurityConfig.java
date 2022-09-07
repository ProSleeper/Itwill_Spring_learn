package com.web.oauth.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.web.oauth.base.model.BaseAuthRole;
import com.web.oauth.base.service.BaseCustomOauth2UserService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private final BaseCustomOauth2UserService baseCustomOauth2UserService;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception{
		http
		.csrf().disable().headers().frameOptions().disable()
		.and()
		.authorizeRequests()
		.antMatchers("/", "h2/**","/h2-console/**", "js/**", "css/**").permitAll()
		.antMatchers("/api/v1/**").hasRole(BaseAuthRole.USER.name())
		.anyRequest().authenticated()
		.and()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/")
		.deleteCookies("JSESSION").invalidateHttpSession(true)
		.and()
		.oauth2Login().defaultSuccessUrl("/")
		.userInfoEndpoint().userService(baseCustomOauth2UserService)
		;
		
		return http.build();
	}
	
}
