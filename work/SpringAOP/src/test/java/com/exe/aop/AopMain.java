package com.exe.aop;


import org.springframework.context.support.GenericXmlApplicationContext;

public class AopMain {

	public static void main(String[] args) {
		/**
		GenericXmlApplicationContext를 이용해서 app-context.xml 를 읽어온다.
		-스프링은 스프링 컨테이너가 객체의 생성과 초기화 하는 것이 핵심이다.
		-이것을 위해서 최상위 interface로 BeanFactory와 ApplicationContext 가 존재하고
		-아래에서 사용하는 GenericXmlApplicationContext는 위 2개의 interface를 구현한 구현체이다.
		-이런 구현체는 3개가 존재한다. GenericXmlApplicationContext, AnnotationConfigApplicationContext, GenericGroovyApplicationContext
		-3개의 구현체는 전부 객체 설정정보를 가져온다. 다만 읽어오는 방식이 다를 뿐이다.
		 - AnnotationConfigApplicationContext: 자바 애노테이션을 이용한 클래스로부터 객체 설정 정보를 가져온다.(@Configuration가 붙은 .java파일)
		 - GenericXmlApplicationContext: XML로부터 객체 설정 정보를 가져온다.(app-context.xml에서 정보를 가져온다.)
		 - GenericGroovyApplicationContext: 그루비 코드를 이용해 설정 정보를 가져온다.(아마 Groovy언어 파일을 읽어오는 것 같다.)
		서블릿에서의 dispatcherServlet의 역할을 위 3개의 구현체가 한다고 보면 될 것 같다.
		*/
		
//		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfig.class);
		GenericXmlApplicationContext context = new GenericXmlApplicationContext("app-context.xml");
		
		TargetA ta = context.getBean("targetA", TargetA.class);
		
		
		//ta.doSomething1();
		//ta.doSomething2();
		//ta.doAnother1();
		//ta.doAnother2();
		
		TargetB tb = context.getBean("targetB", TargetB.class);
		
		Greeter gt = context.getBean("greeter", Greeter.class);
		Greeter gt1 = context.getBean("greeter", Greeter.class);
		
		System.out.println(gt.getClass().getName());
		System.out.println(gt.getClass() == gt1.getClass() ? true : false);
		
		//tb.doSomething1();
		//tb.doSomething2();
		//tb.doAnother1();
		//gt.greet();
//		ctx.close();
	}
}
