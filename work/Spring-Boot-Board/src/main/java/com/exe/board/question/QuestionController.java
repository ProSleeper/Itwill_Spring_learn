package com.exe.board.question;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exe.board.answer.AnswerForm;

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
	public String list(Model model, @PageableDefault Pageable pageable) {
		System.out.println("여기 오니?");
		Page<Question> paging = questionService.getList(pageable);
		
		model.addAttribute("lists", paging);
		model.addAttribute("paging", paging);

		return "question_list";
	}

	@RequestMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
		
		Question question = questionService.getQuestion(id);
		model.addAttribute("question", question);
		
		return "question_detail";
	}
	
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		
		
		
		return "question_form";
	}
	
	@PostMapping("/create") //매개변수의 순서가 중요 valid부터 꼭 써주고 그 뒤로 bindingresult
	public String questionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		
		questionService.create(questionForm.getSubject(), questionForm.getContent());
		
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
