package gd.fintech.lms.student.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import gd.fintech.lms.teacher.service.LectureArchiveService;
import gd.fintech.lms.teacher.service.LectureNoticeService;
import gd.fintech.lms.teacher.service.QuestionCommentService;

//학생의 강좌별 질문게시판에 대한 컨트롤러

@Controller
public class QuestionController {
	Logger logger = LoggerFactory.getLogger(QuestionController.class);
	@Autowired QuestionService questionService;
	@Autowired QuestionCommentService questionCommentService;
	
	
	//모든학생의 질문 리스트(페이징)
	//질문 리스트 10개씩 보여줌(페이징)
	//매개변수:질문의 번호
	//리턴값:질문의 순번으로 모든학생의 질문 리스트 보여줌 
	@GetMapping("student/studentQuestionList")
	public String studentQuestionList(Model model,
			@RequestParam(value="studentQuestionSearch",required = false)String studentQuestionSearch,
			@RequestParam(value="currentPage",defaultValue="1")int currentPage) {
		
		Map<String,Object> map = questionService.getQuestionListByPage(studentQuestionSearch, currentPage);
		
		
		model.addAttribute("questionAllList",map.get("questionAllList"));
		logger.debug(map.get("questionAllList")+"=리스트정보");
		model.addAttribute("navPerPage",map.get("navPerPage"));
		model.addAttribute("navBeginPage", map.get("navBeginPage"));
		model.addAttribute("navLastPage", map.get("navLastPage"));
		
		model.addAttribute("lastPage",map.get("lastPage"));
		logger.debug(map.get("lastPage")+"=라스트페이지수");
		model.addAttribute("studentQuestionSearch",studentQuestionSearch);
		model.addAttribute("questionCount",map.get("questionCount"));
		model.addAttribute("currentPage",currentPage);
		
		
		return  "student/studentQuestionList";
	}
	//나의 질문 리스트(페이징)
	//질문 리스트 10개씩 보여줌
	//매개변수:학생의 id
	//리턴값:해당 학생의 id로 나오는 리스트
	@GetMapping("student/studentMyQuestion")
	public String myStudentQuestionList(Model model,
			@RequestParam(value="accountId",required = false)String accountId,
			@RequestParam(value="studentMyQuestionSearch",required = false)String studentMyQuestionSearch,
			@RequestParam(value="currentPage",defaultValue="1")int currentPage) {
		Map<String,Object> map = questionService.getStudentQuestionListByPage(accountId, currentPage,studentMyQuestionSearch);
		
		model.addAttribute("studentMyQuestionSearch",studentMyQuestionSearch);
		model.addAttribute("questionList",map.get("questionList"));
		model.addAttribute("navPerPage",map.get("navPerPage"));
		model.addAttribute("navBeginPage", map.get("navBeginPage"));
		model.addAttribute("navLastPage", map.get("navLastPage"));
		
		model.addAttribute("lastPage",map.get("lastPage"));
		model.addAttribute("accountId",accountId);
		model.addAttribute("questionCount",map.get("questionCount"));
		model.addAttribute("currentPage",currentPage);
		return "student/studentMyQuestion";
	}
	//강좌별 질문 리스트(페이징)
	//질문 리스트 10개씩 보여줌
	//매개변수:강좌의 번호
	//리턴값:해당 강좌의 번호로 나오는 리스트
	@GetMapping("student/studentLectureQuestionList")
	public String lectureQuestionList(Model model,
			@RequestParam(value="lectureNo",required = false)int lectureNo,
			@RequestParam(value="studentLectureSearch",required = false)String studentLectureSearch,
			@RequestParam(value="currentPage",defaultValue="1")int currentPage) {
		Map<String,Object> map = questionService.getLectureQuestionListByPage(lectureNo, currentPage,studentLectureSearch);
		
		model.addAttribute("studentLectureSearch",studentLectureSearch);
		model.addAttribute("questionList",map.get("questionList"));
		model.addAttribute("navPerPage",map.get("navPerPage"));
		model.addAttribute("navBeginPage", map.get("navBeginPage"));
		model.addAttribute("navLastPage", map.get("navLastPage"));
		
		model.addAttribute("lastPage",map.get("lastPage"));
		model.addAttribute("lectureNo",lectureNo);
		model.addAttribute("questionCount",map.get("questionCount"));
		model.addAttribute("currentPage",currentPage);
		return "student/studentLectureQuestionList";
	}
	
	//질문 입력 폼
	//매개변수:질문의 번호
	//리턴값:질문 입력할수 있는 폼
	@GetMapping("/student/createStudentQuestion")
	public String addQuestion(
			@RequestParam(value="lectureNo")int lectureNo,
			Model model) {
		model.addAttribute("lectureNo",lectureNo);
		return "student/createStudentQuestion";
	}
	
	//질문 입력 액션
	//매개변수:질문의 VO 와 세션
	//리턴값:질문 리스트 질문 추가
	@PostMapping("/student/createStudentQuestion")
	public String addQuestion(Question question,HttpSession session,
			@RequestParam(value="lectureNo",required = false)int lectureNo) {
		questionService.addQuestion(question, session);
		return "redirect:/student/studentLectureQuestionList?lectureNo="+lectureNo;
	}
	
	//학생의 질문 상세보기
	//매개변수:질문의 번호
	//리턴값:해당 질문의 상세보기
	@GetMapping("/student/studentQuestionDetail")
	public String getQuestionOne(Model model,HttpServletRequest request,
			@RequestParam(value="questionNo")int questionNo) {
		//학생 질문게시판 상세보기
		Question question = questionService.getQuestionOne(questionNo);
		HttpSession session = ((HttpServletRequest)request).getSession();
		//Id 가지고오기
		String accountId =(String)session.getAttribute("accountId");
		System.out.println(accountId+"계정Id");
		//조회수 증가
		long updateTime = 0;
		//세션에 저장된 조회 시간 검색
		if(session.getAttribute("updateTime"+questionNo) !=null) {
			updateTime = (long)session.getAttribute("updateTime"+questionNo);
		}
		//시스템 현재시간
		long currentTime = System.currentTimeMillis();
		if(currentTime - updateTime>24*60*601000) {
			//조회수 증가 코드
			questionService.increaseQuestionCount(questionNo);
			session.setAttribute("updateTime"+questionNo, currentTime);
		}
		
		
		logger.debug(question.toString());
		model.addAttribute("question",question);
		model.addAttribute("accountId",session.getAttribute("accountId"));
		return "student/studentQuestionDetail";
	}
	
	//질문 수정 폼
	//매개변수:질문읩 번호
	//리턴값:질문의 번호를 참조해 게시판 질문 수정하는 페이지
	@GetMapping("student/modifyStudentQuestion")
	public String questionModify(Model model,
			@RequestParam(value="questionNo")int questionNo){
		Question question = questionService.getQuestionOne(questionNo);
		model.addAttribute("question",question);
		return "student/modifyStudentQuestion";
	}
	
	//질문 수정 액션
	//매개변수:질문 vo
	//리턴값:질문을 수정하고 해당번호의 상세보기 페이지로 이동
	@PostMapping("/student/modifyStudentQuestion")
	public String questioModify(Question question,HttpSession session) {
		questionService.modifyQuestion(question,session);
		return "redirect:/student/studentQuestionDetail?questionNo="+question.getQuestionNo();
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
	
	// 질문게시판 댓글의 첨부파일 다운로드
	// 매개변수:
	// RequestParam: questionCommentFileUUID(파일 UUID)
	// HttpServletRequest, HttpServletResponse
	// 리턴값: 파일 다운로드
	@GetMapping("/student/downloadQuestionCommentFile")
	public void downloadQuestionCommentFile(
			@RequestParam("questionCommentFileUUID") String questionCommentFileUUID,
			HttpServletRequest request, HttpServletResponse response) {
		questionCommentService.downloadQuestionCommentFile(questionCommentFileUUID, request, response);
	}
	
}

