package com.exe.springdi4;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;


@Component("timeService")
public class MyTimeService implements TimeService{

	public String getTimeString() {
		return SimpleDateFormat.getDateTimeInstance(SimpleDateFormat.LONG, SimpleDateFormat.LONG).format(new Date());
	}
}
