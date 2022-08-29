package com.exe.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class MyAroundAdvice {

	@Around("execution(public void com..aop.*.*(..))")
	public Object aroundMethodCall(ProceedingJoinPoint jointPoint) {
		Object result = null;
		
		try {
//			long longtime = System.currentTimeMillis();
			
			System.out.println("메소드 실행 전(Around Before)");
			result = jointPoint.proceed();
			System.out.println("메소드 실행 후(Around After)");
//			System.out.println("종료시간: " + ((System.currentTimeMillis() - longtime) / 1000f) + "초");
		} catch (Throwable e) {
			// TODO: handle exception
		}
		return result;
	}
}
