package gd.fintech.lms.student.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.student.service.QuestionService;
import gd.fintech.lms.student.vo.Question;

@Controller
public class QuestionController {
	Logger logger = LoggerFactory.getLogger(QuestionController.class);
	@Autowired QuestionService questionService;
	
	//질문 리스트 10개씩 보여줌(페이징)
	@GetMapping("student/studentQuestionList")
	public String questionList(Model model,
			@RequestParam(value="currentPage")int currentPage) {
		int rowPerPage=10;
		int questionCount=questionService.selectQuestionCount();
		int lastPage =0;
		if(questionCount%rowPerPage ==0) {
			lastPage=questionCount/rowPerPage;
		} else if(questionCount%rowPerPage !=0) {
			lastPage=questionCount/rowPerPage+1;
		}
		List<Question>questionList = questionService.getQuestionListByPage(currentPage, rowPerPage);
		model.addAttribute("questionList",questionList);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("lastPage",lastPage);
		return  "student/studentQuestionList";
	}
	
	//질문 입력 폼
	@GetMapping("student/addQuestion")
	public String addQuestion() {
		return "student/addQuestion";
	}
	
	//질문 입력 액션
	@PostMapping("student/addQuestion")
	public String addQuestion(Question question) {
		questionService.addQeustion(question);
		return "redirect:/student/studentQuestionList";
	}
	
	//학생의 질문 상세보기
	@GetMapping("student/studentQuestionOne")
	public String questionOne(Model model,
			@RequestParam(value="questionNo")int questionNo) {
		Question questionOne = questionService.getQuestionOne(questionNo);
		model.addAttribute("questionOne",questionOne);
		return "student/studentQuestionOne";
	}
	
	//질문 수정 폼
	@GetMapping("student/questionModify")
	public String questionModify(Model model,
			@RequestParam(value="currentPage")int currentPage,
			@RequestParam(value="accountId")Question accountId) {
		Question questionModify = questionService.modifyQuestion(accountId);
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("questionModify",questionModify);
		return "student/questionModify";
	}
	
	//질문 수정 액션
	@PostMapping("student/questionModify")
	public String questioModify(Question question,@RequestParam(value="currentPage")int currentPage) {
		questionService.modifyQuestion(question);
		return "redirect:/student/questionList?currentPage="+currentPage;
	}
	
}

