
package gd.fintech.lms.teacher.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.dto.QuestionCommentForm;
import gd.fintech.lms.student.service.QuestionService;
import gd.fintech.lms.student.vo.Question;
import gd.fintech.lms.teacher.service.QuestionCommentService;

// 강의별 질문게시판에 강사가 댓글을 관리하는 기능을 담당하는 컨트롤러

@Controller
public class QuestionCommentController {
	// 질문게시판의 게시글을 읽기 위한 서비스
	@Autowired private QuestionService questionService;
	
	// 질문게시판의 댓글을 관리하기 위한 서비스
	@Autowired private QuestionCommentService questionCommentService;
	
	// 강사가 관리하는 질문게시판의 게시글 출력
	// 매개변수:
	// RequestParam: currentPage(현재 페이지)
	// Model
	// 리턴값: teacher/questionList.jsp 뷰 포워딩
	@GetMapping("/teacher/questionList")
	public String questionList(
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			Model model) {
		// TODO 협업자가 메서드를 고치는대로 고쳐진 메서드 사용
		List<Question> list = new ArrayList<>(); // questionService.getQuestionListByPage(currentPage, 10);
		
		model.addAttribute("questionList", list);
		return "teacher/questionList";
	}
	
	// 질문게시판의 게시글 상세내용 출력
	// 매개변수:
	// RequestParam: questionNo(게시글 고유번호)
	// Model
	// 리턴값: teacher/questionDetail.jsp 뷰 포워딩
	@GetMapping("/teacher/questionDetail")
	public String questionDetail(
			@RequestParam("questionNo") int questionNo,
			Model model) {
		Question question = questionService.getQuestionOne(questionNo);
		
		model.addAttribute("question", question);
		return "teacher/questionDetail";
	}
	
	// 질문게시판 댓글 작성 폼 호출
	// 매개변수:
	// RequestParam: questionNo(댓글을 달 게시글 고유번호)
	// Model
	// 리턴값: teacher/createQuestionComment.jsp 뷰 포워딩
	@GetMapping("/teacher/createQuestionComment")
	public String createQuestionComment(
			@RequestParam("questionNo") int questionNo,
			Model model) {
		Question question = questionService.getQuestionOne(questionNo);
		
		model.addAttribute("question", question);
		return "teacher/createQuestionComment";
	}
	
	// 질문게시판 댓글 작성
	// 매개변수: QuestionCommentForm(커맨드 객체), HttpSession(작성자 인증용 세션 객체)
	// 리턴값: /teacher/questionList 리다이렉트
	@PostMapping("/teacher/createQuestionComment")
	public String createQuestionComment(
			QuestionCommentForm questionCommentForm,
			HttpSession session) {
		questionCommentService.createQuestionComment(questionCommentForm, session);
		
		return "redirect:/teacher/questionList";
	}
	
	// 질문게시판 댓글 수정 폼 호출
	// 매개변수:
	// RequestParam: questionCommentNo(댓글 고유번호)
	// Model
	// 리턴값: teacher/modifyQuestionComment.jsp 뷰 포워딩
	@GetMapping("/teacher/modifyQuestionComment")
	public String modifyQuestionComment(
			@RequestParam("questionCommentNo") int questionCommentNo,
			Model model) {
		Map<String, Object> map = questionCommentService.getQuestionCommentDetailAndQuestionDetail(questionCommentNo);
		
		model.addAttribute("map", map);
		return "teacher/modifyQuestionComment";
	}
	
	// 질문게시판 댓글 수정
	// 매개변수: QuestionCommentForm(커맨드 객체), HttpSession(작성자 인증용 세션 객체)
	// 리턴값: /teacher/questionDetail 리다이렉트
	@PostMapping("/teacher/modifyQuestionComment")
	public String modifyQuestionComment(
			QuestionCommentForm questionCommentForm,
			HttpSession session) {
		questionCommentService.modifyQuestionComment(questionCommentForm, session);
		
		return "redirect:/teacher/questionDetail?questionNo="+questionCommentForm.getQuestionNo();
	}
	
	// 질문게시판 댓글의 첨부파일 다운로드
	// 매개변수:
	// RequestParam: questionCommentFileUUID(파일 UUID)
	// HttpServletRequest, HttpServletResponse
	// 리턴값: 파일 다운로드
	@GetMapping("/teacher/downloadQuestionCommentFile")
	public void downloadQuestionCommentFile(
			@RequestParam("questionCommentFileUUID") String questionCommentFileUUID,
			HttpServletRequest request, HttpServletResponse response) {
		questionCommentService.downloadQuestionCommentFile(questionCommentFileUUID, request, response);
	}
}
