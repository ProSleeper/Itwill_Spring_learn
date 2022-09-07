package com.web.oauth.base.config;

public class WebSecurityConfig {

	@Autowired
	private final BaseCustomOauth2UserService baseCustomOauth2UserService;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception{
		http.csrf().disable().headers().frameOptions().disable
		.and()
		.authorizeRequests()
		.antMatchers("/", "h2/**","/h2-console/**", "js/**", "css/**").permitAll()
		.antMatchers("/api/v1/**").hasRole(BaseAuthRole.USER.name())
		.anyRequest().authenticated()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/")
		.deleteCookies("JSESSION").invalidateHttpSession(true)
		.and()
		.oauth2Login().defaultSuccessUrl("/")
		.userInfoEndpoint().userService(baseCustomOauth2UserService)
		;
		
	}
	
}
