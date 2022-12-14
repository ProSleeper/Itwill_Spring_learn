package com.exe.springdi4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("serviceConsumer")
public class ServiceConsumer {
	
	//생성자로 의존성 주입
	
	@Autowired
	@Qualifier("messageService")
	MessageService ms;
	
	@Autowired
	TimeService ts;
	
	@Autowired
	@Qualifier("dummyJob")
	JobService js;
	
	public void consumerService() {
		System.out.println(ms.getMessage());
		System.out.println(ts.getTimeString());
		js.getJob();
		
	}
}
