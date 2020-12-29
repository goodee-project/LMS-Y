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
	//@suppressWarning 검증되지 않은 연산자 관련 경고 억제
	@GetMapping("student/studentQuestionList")
	public String questionList(Model model,
			@RequestParam(value="questionNo")int questionNo,
			@RequestParam(value="currentPage",defaultValue="1")int currentPage,
			HttpSession session) {
		Map<String,Object>map = questionService.getQuestionListByPage(questionNo, currentPage, session);
		
		//모든 질문이 있는 리스트
		model.addAttribute("questionAllList",map.get("questionAllList"));
		
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("pageNaviBegin",map.get("pageNaviBegin"));
		model.addAttribute("pageNaviEnd",map.get("pageNaviEnd"));
		model.addAttribute("lastPage", map.get("lastPage"));
		
		return  "student/studentQuestionList";
	}
	
	
	//로그인한 해당학생의 질문 리스트
	public String getStudentQuestionListByPage(Model model,
			@RequestParam(value="accountId")String accountId,
			@RequestParam(value="currentPage",defaultValue="1")int currentPage,
			HttpSession session){
		Map<String,Object>map=questionService.getStudentQuestionListByPage(accountId, currentPage, session);
		//해당 학생이 있는 리스트
		model.addAttribute("studentQuestionList",map.get("studentQuestionList"));
		
		model.addAttribute("currentPage",currentPage);
		model.addAttribute("pageNaviBegin",map.get("pageNaviBegin"));
		model.addAttribute("pageNaviEnd",map.get("pageNaviEnd"));
		model.addAttribute("lastPage", map.get("lastPage"));
		
		
		return "student/studentQuestionListDetail";
	}
	
	//질문 입력 폼
	@GetMapping("/student/addQuestion")
	public String addQuestion() {
		return "student/studentaddQuestion";
	}
	
	//질문 입력 액션
	@PostMapping("/student/addQuestion")
	public String addQuestion(Question question,HttpSession session) {
		questionService.addQuestion(question, session);
		return "redirect:/student/studentQuestionList";
	}
	
	//학생의 질문 상세보기
	@GetMapping("student/studentQuestionOne")
	public String questionOne(Model model,HttpServletRequest request,
			@RequestParam(value="questionNo",required = false)int questionNo) {
		HttpSession session =((HttpServletRequest)request).getSession();
		
		String accountId = (String)session.getAttribute("accountId");
		Question question = questionService.getQuestionOne(questionNo );
		model.addAttribute("accountId",accountId);
		model.addAttribute("question",question);
		return "student/studentquestionOne";
	}
	
	//질문 수정 폼
	@GetMapping("student/questionModify")
	public String questionModify(Model model,
			@RequestParam("questionNo")int questionNo){
		Question question = questionService.getQuestionOne(questionNo);
		model.addAttribute("question",question);
		return "student/studentquestionModify";
	}
	
	//질문 수정 액션
	@PostMapping("student/questionModify")
	public String questioModify(Question question,HttpSession session) {
		questionService.modifyQuestion(question,session);
		return "redirect:/student/studentquestionList?currentPage="+question.getQuestionNo();
	}
	
}

