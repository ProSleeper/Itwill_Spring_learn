package com.exe.board;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
//@RestController = @Controller + @ResponseBody
@Controller
public class HelloController {
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		
		String result = "{\"result\":\"Hello SpringBoot nate\"}";
		
		return "안녕하세요. 현재 서버시간은 "+new Date() +"입니다. \n";
	}
	
	@RequestMapping("/bye")
	@ResponseBody
	public String bye() {
		
		String result = "{\"result\":\"Hello SpringBoot bye\"}";
		
		return result;
	}
	
}
