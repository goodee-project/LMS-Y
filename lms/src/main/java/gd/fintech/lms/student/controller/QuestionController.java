package gd.fintech.lms.student.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.student.service.QuestionService;
import gd.fintech.lms.student.vo.Question;

@Controller
public class QuestionController {
	Logger logger = LoggerFactory.getLogger(QuestionController.class);
	@Autowired QuestionService questionService;
	
	@GetMapping("/auth/questionList")
	public String questionList(Model model,
			@RequestParam(value="currentPage")int currentPage) {
		int rowPerPage=10;
		List<Question>selectQuestionCount = questionService.getQuestionListByPage(currentPage, rowPerPage);
		model.addAttribute("selectQuestionCount",selectQuestionCount);
		model.addAttribute("currentPage",currentPage);
		return  "questionList";
	}
}
