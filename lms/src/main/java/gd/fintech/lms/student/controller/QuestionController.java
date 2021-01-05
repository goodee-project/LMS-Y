package gd.fintech.lms.student.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.student.service.QuestionService;
import gd.fintech.lms.student.vo.Question;

@Controller
public class QuestionController {
	Logger logger = LoggerFactory.getLogger(QuestionController.class);
	@Autowired QuestionService questionService;
	
	
	//모든학생의 질문 리스트(페이징)
	//질문 리스트 10개씩 보여줌(페이징)
	//매개변수:질문의 번호
	//리턴값:질문의 순번으로 모든학생의 질문 리스트 보여줌
	@GetMapping("student/studentQuestionList")
	public String studentQuestionList(Model model,
			@RequestParam(value="lectureNo",defaultValue="1")int lectureNo,
			@RequestParam(value="currentPage",defaultValue="1")int currentPage) {
		
		Map<String,Object> map = questionService.getQuestionListByPage(lectureNo, currentPage);
		
		
		model.addAttribute("questionAllList",map.get("questionAllList"));
		model.addAttribute("navPerPage",map.get("navPerPage"));
		model.addAttribute("navBeginPage", map.get("navBeginPage"));
		model.addAttribute("navLastPage", map.get("navLastPage"));
		
		model.addAttribute("lastPage",map.get("lastPage"));
		logger.debug(map.get("lastPage")+"라스트");
		model.addAttribute("lectureNo",lectureNo);
		model.addAttribute("questionCount",map.get("questionCount"));
		model.addAttribute("currentPage",currentPage);
		
		
		return  "student/studentQuestionList";
	}
	
	//질문 입력 폼
	//매개변수:
	//리턴값:질문 입력할수 있는 폼
	@GetMapping("/student/studentQuestionAdd")
	public String addQuestion() {
		return "student/studentQuestionAdd";
	}
	
	//질문 입력 액션
	//매개변수:질문의 VO 와 세션
	//리턴값:
	@PostMapping("/student/studentQuestionAdd")
	public String addQuestion(Question question,HttpSession session) {
		questionService.addQuestion(question, session);
		return "redirect:/student/studentQuestionList";
	}
	
	//학생의 질문 상세보기
	//매개변수:질문의 번호
	//리턴값:해당 질문의 상세보기
	@GetMapping("/student/studentQuestionOne")
	public String getQuestionOne(Model model,
			@RequestParam(value="questionNo")int questionNo) {
		Question question = questionService.getQuestionOne(questionNo);
		logger.debug(question.toString());
		model.addAttribute("question",question);
		return "/student/studentQuestionOne";
	}
	
	
	//질문 수정 폼
	//매개변수:질문읩 번호
	//리턴값:질문의 번호를 참조해 게시판 질문 수정하는 페이지
	@GetMapping("student/studentQuestionModify")
	public String questionModify(Model model,
			@RequestParam(value="questionNo")int questionNo){
		Question question = questionService.getQuestionOne(questionNo);
		model.addAttribute("question",question);
		return "student/studentQuestionModify";
	}
	
	//질문 수정 액션
	//매개변수:질문 vo
	//리턴값:질문을 수정하고 해당번호의 상세보기 페이지로 이동
	@PostMapping("/student/studentQuestionModify")
	public String questioModify(Question question,HttpSession session) {
		questionService.modifyQuestion(question,session);
		return "redirect:/student/studentQuestionOne?questionNo="+question.getQuestionNo();
	}
	
	//질문 제거 액션
	//매개변수:질문의 번호
	//리턴값:질문의 번호를 참조해 게시판 질문 삭제
	@GetMapping("student/removeQuestion")
	public String removeQuestion(@RequestParam(value="questionNo")int questionNo) {
		Question question = questionService.getQuestionOne(questionNo);
		questionService.removeQuestion(questionNo);
		return "redirect:/student/studentQuestionList?questionNo="+question.getQuestionNo();
	}
	
}

