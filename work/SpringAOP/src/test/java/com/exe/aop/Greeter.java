package com.exe.aop;


import org.springframework.stereotype.Component;

@Component("greeter")
public class Greeter {

	public void greet() {
		System.out.println(String.format("%s", "스프링 AOP"));
	}
}
