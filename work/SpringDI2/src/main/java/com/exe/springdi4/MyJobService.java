package com.exe.springdi4;

import org.springframework.stereotype.Component;

@Component("dummyJob")
public class MyJobService implements JobService{

	public void getJob() {
		System.out.println("나는 프로그래머가 아닌게 아닙니다.");
		
	}

	
}
