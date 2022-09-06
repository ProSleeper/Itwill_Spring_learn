package com.exe.board.question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.exe.board.DataNotFoundException;
import com.exe.board.user.SiteUser;

import lombok.RequiredArgsConstructor;


//Controller -> Service -> Repository

//오버라이딩 된 생성자를 쓰기 위해서 필요한 어노테이션
@RequiredArgsConstructor
@Service
public class QuestionService {

	private final QuestionRepository questionRepository;
	
	
	public Page<Question> getList(Pageable pageable){
		
		List<Sort.Order> sorts = new ArrayList<Sort.Order>();
		sorts.add(Sort.Order.desc("createdDate"));
		
		
		pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1
				, pageable.getPageSize(), Sort.by(sorts));
		
		//getPageNumber: 반환할 페이지
		//getPageSize: 반환할 항목 수
		//PageRequest: 정렬 매개변수가 적용된 새로운 항목을 생성
		
		return questionRepository.findAll(pageable);
	}
	public List<Question> getList(){
		return questionRepository.findAll();
	}
	
	public Question getQuestion(Integer id) {
		Optional<Question> op = questionRepository.findById(id);
		
		if(op.isPresent()) {
			return op.get();
		}
		else {
			throw new DataNotFoundException("Question Not Found");
		}
	}
	
	public void create(String subject, String content, SiteUser author) {
		Question question = new Question();
		question.setSubject(subject);
		question.setContent(content);
		question.setCreatedDate(LocalDateTime.now());
		question.setAuthor(author);
		
		
		
		questionRepository.save(question);
	}
	
	public void modify(Question question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		
		questionRepository.save(question);
		
	}
	
	public void delete(Question question){
		questionRepository.delete(question);
	}

	public void vote(Question question, SiteUser siteUser){
		question.getVoter().add(siteUser);

		questionRepository.save(question);
	}
}

















