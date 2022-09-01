package com.exe.board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exe.board.question.Question;
import com.exe.board.question.QuestionRepository;

@SpringBootTest
class SpringBootBoardApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;
	
	@Test
	void contextLoads() {
	}

//	@Test
	void save() {
		Question q1 = new Question();
		q1.setSubject("스프링부트는 먹는건가요?");
		q1.setContent("스프링이 먹고 싶어요.");
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
	}
	
//	
//	@Test
//	void findAll() {
//		List<Question> lists = questionRepository.findAll();
//		
//		//기대값 and 실제값
//		//기대값과 실제값이 같아야 test가 성공하는 것
//		assertEquals(2, lists.size());
//		
//		Question q = lists.get(0);
//		assertEquals("스프링부트는 먹는건가요?", q.getSubject());
//	}
//	
//	@Test
//	void findBySubject() {
//		Question q = questionRepository.findBySubject("스프링부트는 먹는건가요?");
//		assertEquals(1, q.getId());
//	}
//	
//	@Test
//	void findBySubjectAndContent( ) {
//		Question q = questionRepository
//				.findBySubjectAndContent("스프링부트는 먹는건가요?", "스프링이 먹고 싶어요.");
//	
//		assertEquals(1, q.getId());
//	}
//	
//	@Test
//	void findBySubjectLike() {
//		List<Question> lists = questionRepository.findBySubjectLike("스프링%");
//		
//		Question q = lists.get(0);
//		
//		assertEquals("스프링부트는 먹는건가요?", q.getSubject());
//		
//		
//	}
//	
//	@Test
//	void update() {
//		Optional<Question> op = questionRepository.findById(1);
//		assertTrue(op.isPresent());
//		
//		Question q = op.get();
//		q.setSubject("스프링");
//		q.setContent("스프링부트");
//		
//		questionRepository.save(q);	
//	}
//	
//	@Test
//	void delete() {
//		assertEquals(2, questionRepository.count());
//		
//		Optional<Question> op = questionRepository.findById(1);
//		assertTrue(op.isPresent());
//		
//		
//		Question q = op.get();
//		questionRepository.delete(q);
//		assertEquals(2,questionRepository.count());
//		
//		
//		
//		
//	}
//	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
