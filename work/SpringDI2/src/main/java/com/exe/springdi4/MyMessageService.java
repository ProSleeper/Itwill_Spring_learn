package com.exe.springdi4;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("messageService")
@Scope(value = "prototype")
public class MyMessageService implements MessageService{

	public String getMessage() {
		return "こんにちは今日は金曜日";
	}

	
}
