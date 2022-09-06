package com.exe.board.answer;


import java.security.Principal;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.exe.board.question.Question;
import com.exe.board.question.QuestionService;
import com.exe.board.user.SiteUser;
import com.exe.board.user.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.server.ResponseStatusException;

@RequestMapping("/answer")
@RequiredArgsConstructor
@Controller
public class AnswerController {
	
	private final QuestionService questionService;
	private final AnswerService answerService;
	private final UserService userService;
	
	@PreAuthorize("isAuthenticated")
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("id") Integer id,
			@Valid AnswerForm answerForm, BindingResult bindResult, Principal principal) {
		
		Question question = questionService.getQuestion(id);
		SiteUser siteUser = userService.getUser(principal.getName());
		
		if (bindResult.hasErrors()) {
			model.addAttribute("question", question);
			
			return "question_detail";
		}
		Answer answer = answerService.create(question, answerForm.getContent(), siteUser);


		return String.format("redirect:/question/detail/%s#answer_%s", answer.getQuestion().getId(), answer.getId());
	}

	@PreAuthorize("isAuthenticated")
	@GetMapping("/modify/{id}")
	public String modifyAnswer(AnswerForm answerForm, @PathVariable("id") Integer id, Principal principal) {

		Answer answer = answerService.getAnswer(id);

		if(!answer.getAuthor().getUserName().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
		}

		answerForm.setContent(answer.getContent());

		return "answer_form";
	}

	@PreAuthorize("isAuthenticated")
	@PostMapping("/modify/{id}")
	public String modifyAnswer(@Valid AnswerForm answerForm, BindingResult bindResult, @PathVariable("id") Integer id, Principal principal) {


		if (bindResult.hasErrors()) {

			return "answer_form";
		}
		Answer answer = answerService.getAnswer(id);

		if(!answer.getAuthor().getUserName().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다.");
		}

		answerService.modify(answer, answerForm.getContent());

		return String.format("redirect:/question/detail/%s#answer_%s", answer.getQuestion().getId(), answer.getId());
	}

	@PreAuthorize("isAuthenticated")
	@GetMapping("/delete/{id}")
	public String deleteAnswer(@PathVariable("id") Integer id, Principal principal) {

		Answer answer = answerService.getAnswer(id);

		if(!answer.getAuthor().getUserName().equals(principal.getName())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없습니다.");
		}

		answerService.delete(answer);

		return String.format("redirect:/question/detail/%s", answer.getQuestion().getId());
	}

	@PreAuthorize("isAuthenticated")
	@GetMapping("/vote/{id}")
	public String voteAnswer(@PathVariable("id") Integer id, Principal principal) {

		Answer answer = answerService.getAnswer(id);

		SiteUser siteUser = userService.getUser(principal.getName());

		answerService.vote(answer, siteUser);

		return String.format("redirect:/question/detail/%s#answer_%s", answer.getQuestion().getId(), answer.getId());
	}

}
