package com.exe.springmvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller()
public class TestController {
	
//	@RequestMapping(value = "/test/param.action", method = RequestMethod.GET)
//	public String processGetRequest() {
//		System.out.println("Get방식 Request");
//		
//		return "paramResult";
//		
//	}
//	
//	@RequestMapping(value = "/test/param.action", method = RequestMethod.POST)
//	public String processPostRequest() {
//		System.out.println("Post 방식 Request");
//		return "paramResult";
//		
//	}
	
	
	@RequestMapping(value = "/test/param.action", method = {RequestMethod.POST, RequestMethod.GET})
	public String processRequest(PersonDTO dto, String name, HttpServletRequest request) {
		
		
		
		System.out.println("Get/Post 방식 Request");
		System.out.println(dto);
		
		System.out.println("name: " + dto.getName());
		System.out.println("phone: " + dto.getPhone());
		System.out.println("email: " + dto.getEmail());
		return "paramResult";
		
	}
	
	
	@RequestMapping(value = "/test/mav.action", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView mavRequest(PersonDTO dto) {
		
		
//		System.out.println("name: " + dto.getName());
//		System.out.println("phone: " + dto.getPhone());
//		System.out.println("email: " + dto.getEmail());
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", dto);
		mav.setViewName("paramResult");
		return mav;
	}
	
	@RequestMapping(value = "/test/redirect.action", method = {RequestMethod.POST, RequestMethod.GET})
	public String mavRedirectRequest() {
		
		
		//return "redirect:/hello.action";
		return "hello";
	}
	
	
	
	
	
	
	
}
