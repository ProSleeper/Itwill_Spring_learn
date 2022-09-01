package com.exe.board.question;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

//View를 표현하기 위한 template의 종류
//Thymleaf, Mustache, Groovy, Freemarker, Velocity

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

//	private final QuestionRepository questionRepository;
	private final QuestionService questionService;

	@RequestMapping("/list")
	public String list(Model model) {

		List<Question> lists = questionService.getList();

		model.addAttribute("lists", lists);

		return "question_list";
	}

	@RequestMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		
		Question question = questionService.getQuestion(id);
		model.addAttribute("question", question);
		
		return "question_detail";
	}
	
	@GetMapping("/create")
	public String questionCreate() {
		
		
		
		return "question_form";
	}
	
	@PostMapping("/create")
	public String questionCreate(String subject, @RequestParam String content) {
		
		System.out.println("안옴");
		
		questionService.create(subject, content);
		
		return "redirect:/question/list";
	}
	
	
	

	@RequestMapping("/")
	public String home() {
		return "redirect:/question/list";
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	컨트롤러가 리포지토리를 직접 접근할때의 방법
//	private final QuestionRepository questionRepository;
//
//
//	@RequestMapping("/question/list")
//	public String list(Model model) {
//
//		List<Question> lists = questionRepository.findAll();
//
//		model.addAttribute("lists", lists);
//		System.out.println("오니?");
//
//		return "question_list";
//	}
//
//	//View를 표현하기 위한 template의 종류
//	//Thymleaf, Mustache, Groovy, Freemarker, Velocity
//
//	@RequestMapping("/")
//	public String home() {
//		return "redirect:/question/list";
//	}


}
