package com.exe.springmybatis;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public class CustomMain {

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext("app-context.xml");
		
		CustomDAO dao = (CustomDAO)context.getBean("customDAO");
		
		CustomDTO dto;
//		
//		dto = new CustomDTO();
//		dto.setId(223);
//		dto.setName("클립스");
//		dto.setAge(19);
//		
//		dao.insertData(dto);
//		System.out.println("insert Complete");
		
		//update
//		dto = new CustomDTO();
//		dto.setId(333);
//		dto.setName("SOLID");
//		dto.setAge(99);
//		
//		dao.updateData(dto);
//		System.out.println("update Complete");
		
		//delete 
//		dao.deleteData(333);
		
		
		
//		//OneSelect
		dto = dao.getReadData(223);
		
		if (dto != null) {
			System.out.printf("%d %s %d\n", dto.getId(), dto.getName(), dto.getAge());
		}
		System.out.println("OneSelect Complete");
	
	
		//list
//		List<CustomDTO> lists = dao.getList();
//		for (CustomDTO dto1 : lists) {
//			System.out.printf("%d %s %d\n", dto1.getId(), dto1.getName(), dto1.getAge());
//		
//		}
//		
//		System.out.println("select Complete");
		
		
	}
}
