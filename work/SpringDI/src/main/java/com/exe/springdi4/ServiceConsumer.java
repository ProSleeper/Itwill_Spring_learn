package com.exe.springdi4;

public class ServiceConsumer {
	
	//생성자로 의존성 주입
	MessageService ms;
	TimeService ts;
	JobService js;
	
	public ServiceConsumer() {};
	public ServiceConsumer(MessageService ms) {
		
		this.ms = ms;
	}
	
	//메소드로 의존성 주입
	public void setTimeService(TimeService ts) {
		this.ts = ts;
	}

	public void setJobService(JobService js) {
		this.js = js;
	}
	
	public void consumerService() {
		System.out.println(ms.getMessage());
		System.out.println(ts.getTimeString());
		js.getJob();
		
	}
}
