package com.exe.board.question;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.exe.board.answer.AnswerForm;
import com.exe.board.user.SiteUser;
import com.exe.board.user.UserService;

import lombok.RequiredArgsConstructor;

//View를 표현하기 위한 template의 종류
//Thymleaf, Mustache, Groovy, Freemarker, Velocity

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {

//	private final QuestionRepository questionRepository;
	private final QuestionService questionService;
	private final UserService userService;
	
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
	
	@PreAuthorize("isAuthenticated")
	@GetMapping("/create")
	public String questionCreate(QuestionForm questionForm) {
		
		
		
		return "question_form";
	}
	
	@PreAuthorize("isAuthenticated")
	@PostMapping("/create") //매개변수의 순서가 중요 valid부터 꼭 써주고 그 뒤로 bindingresult
	public String questionCreate(@Valid QuestionForm questionForm, 
			BindingResult bindingResult, Principal principal) {
		
		if(bindingResult.hasErrors()) {
			return "question_form";
		}
		SiteUser siteUser = userService.getUser(principal.getName());
		
		questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
		
		return "redirect:/question/list";
	}

	@RequestMapping("/")
	public String home() {
		return "redirect:/question/list";
	}

	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String questionModify(QuestionForm questionForm, 
			@PathVariable("id") Integer id, Principal principal) {
		
		Question question = questionService.getQuestion(id);
		if(!question.getAuthor().getUserName().equals(principal.getName())){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
		}
		
		questionForm.setSubject(question.getSubject());
		questionForm.setContent(question.getContent());
		
		return "question_form";
	}
	
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String questionModify(@Valid QuestionForm questionForm,
			BindingResult bindResult, @PathVariable("id") Integer id, Principal principal) {
		
		if(bindResult.hasErrors()) {
			
			return "question_form";
		}
		
		Question question = questionService.getQuestion(id);
		
		if(!question.getAuthor().getUserName().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
		}
		
		questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
		
		return String.format("redirect:/question/detail/%s", id);
	}


	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String questionDelete(Principal principal, @PathVariable("id") Integer id) {

		Question question = questionService.getQuestion(id);

		if(!question.getAuthor().getUserName().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
		}

		questionService.delete(question);
		return "redirect:/";
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/vote/{id}")
	public String questionVote(Principal principal, @PathVariable("id") Integer id) {
		System.out.print("오나?");
		Question question = questionService.getQuestion(id);

		SiteUser siteUser = userService.getUser(principal.getName());

		questionService.vote(question, siteUser);

		return String.format("redirect:/question/detail/%s", id);
	}

	@PreAuthorize("isAuthenticated()")
	@RequestMapping("/vote/test")
	@ResponseBody
	public String testqQestion() {

		return "testQuestion";
	}

	@RequestMapping("/vote/test2")
	public String testqQestionWow() {

		return "testHtml";
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
