package com.exe.springmybatis;

import lombok.Data;

//create table custom
//(id number,
//name char(10),
//age number);

@Data
public class CustomDTO {
	
	private int id;
	private String name;
	private int age;
	
}
