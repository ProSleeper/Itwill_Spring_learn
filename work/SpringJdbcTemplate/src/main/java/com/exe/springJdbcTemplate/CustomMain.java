package com.exe.springJdbcTemplate;

import java.util.Iterator;
import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public class CustomMain {

	
	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext("app-context.xml");
		
		CustomDAO2 dao = (CustomDAO2)context.getBean("customDAO2");
		
		CustomDTO dto;
		
		dto = new CustomDTO();
		dto.setId(333);
		dto.setName("네임드");
		dto.setAge(33);
		
		dao.insertData(dto);
		System.out.println("insert Complete");
		
		
//		//update
//		dto = new CustomDTO();
//		dto.setId(111);
//		dto.setName("SOLID");
//		dto.setAge(10);
//		
//		dao.updateData(dto);
//		System.out.println("update Complete");
		
		//delete 
//		dao.deleteData(222);
		
		
		
//		//OneSelect
//		dto = dao.getReadData(222);
//		if (dto != null) {
//			System.out.printf("%d %s %d\n", dto.getId(), dto.getName(), dto.getAge());
//		}
//		System.out.println("OneSelect Complete");
	
	
//		//list
//		List<CustomDTO> lists = dao.getList();
//		for (CustomDTO dto1 : lists) {
//			System.out.printf("%d %s %d\n", dto1.getId(), dto1.getName(), dto1.getAge());
//			
//		
//		}
//		
//		System.out.println("select Complete");
		
		
	}
}
