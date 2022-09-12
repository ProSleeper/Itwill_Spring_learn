package com.exe.aop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

	@Bean
	public Greeter greeter() {
		Greeter g = new Greeter();
		return g;
	}
	
}
