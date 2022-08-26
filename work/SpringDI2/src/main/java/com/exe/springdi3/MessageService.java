package com.exe.springdi3;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MessageService {

	public void messageService() {
		
		//BeanFactory생성
		GenericXmlApplicationContext context = new GenericXmlApplicationContext("app-context.xml");
	
		//Bean 획득
		Message ms = (Message)context.getBean("message");
		ms.sayHello("배수지");
	}
}
