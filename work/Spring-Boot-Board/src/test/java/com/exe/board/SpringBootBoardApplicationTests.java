package com.exe.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootBoardApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void contextLoads() {
	}

//	@Test
//	void save() {
//		Question q1 = new Question();
//		q1.setSubject("스프링부트는 먹는건가요?");
//		q1.setContent("스프링이 먹고 싶어요.");
//		q1.setCreatedDate(LocalDateTime.now());
//		
//		this.questionRepository.save(q1);
//		
//		
//		Question q2 = new Question();
//		q2.setSubject("리액트가 어렵네영");
//		q2.setContent("리액트에서 요청했어요~");
//		q2.setCreatedDate(LocalDateTime.now());
//		
//		this.questionRepository.save(q2);
//	}
	
	
	@Test
	void findAll() {
		List<Question> lists = questionRepository.findAll();
		
		//기대값 and 실제값
		//기대값과 실제값이 같아야 test가 성공하는 것
		assertEquals(2, lists.size());
		
		Question q = lists.get(0);
		assertEquals("스프링부트는 먹는건가요?", q.getSubject());
	}
	
	@Test
	void findBySubject() {
		Question q = questionRepository.findBySubject("스프링부트는 먹는건가요?");
		assertEquals(2, q.getId());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}