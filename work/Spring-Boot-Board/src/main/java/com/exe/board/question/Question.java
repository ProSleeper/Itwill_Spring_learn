package com.exe.board.question;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.exe.board.answer.Answer;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity	//JPA가 엔터티로 인식
public class Question {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //identity는 1씩 증가
	private Integer id;
	
	@Column(length = 200)
	private String subject;
	
	@Column(columnDefinition = "TEXT")	//"TEXT"는 글자 수 제한 없음
	private String content;
	
	private LocalDateTime createdDate;
																	//트랜잭션을 유지하게 하는 방법
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE/* , fetch = FetchType.EAGER */)
	private List<Answer> answerList;
}























