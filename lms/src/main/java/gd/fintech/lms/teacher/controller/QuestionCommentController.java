
package gd.fintech.lms.teacher.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.FilePath;
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
	// 리턴값: teacherQuestionList.jsp 뷰 포워딩
	@GetMapping("/teacher/questionList")
	public String questionList(
			@RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
			Model model) {
		List<Question> list = questionService.getQuestionListByPage(currentPage, 10);
		
		model.addAttribute("questionList", list);
		return "teacher/questionList";
	}
	
	// 질문게시판의 게시글 상세내용 출력
	// 매개변수:
	// RequestParam: questionNo(게시글 고유번호)
	// Model
	// 리턴값: teacherQuestionDetail.jsp 뷰 포워딩
	@GetMapping("/teacher/questionDetail")
	public String questionDetail(
			@RequestParam("questionNo") int questionNo,
			Model model) {
		Question question = questionService.getQuestionOne(questionNo);
		
		model.addAttribute("question", question);
		return "teacher/questionDetail";
	}
	
	// 질문게시판 댓글 작성
	// 리턴값: teacherCreateQuestionComment.jsp 뷰 포워딩
	@GetMapping("/teacher/createQuestionComment")
	public String createQuestionComment(
			@RequestParam("questionNo") int questionNo,
			Model model) {
		Question question = questionService.getQuestionOne(questionNo);
		
		model.addAttribute("question", question);
		return "teacher/createQuestionComment";
	}
	@PostMapping("/teacher/createQuestionComment")
	public String createQuestionComment(
			QuestionCommentForm questionCommentForm,
			HttpSession session) {
		questionCommentService.createQuestionComment(questionCommentForm, session);
		
		return "redirect:/teacher/questionList";
	}
	
	// 질문게시판 댓글 수정
	// 매개변수:
	// RequestParam: questionCommentNo(댓글 고유번호)
	// 리턴값: teacherModifyQuestionComment.jsp 뷰 포워딩
	@GetMapping("/teacher/modifyQuestionComment")
	public String modifyQuestionComment(
			@RequestParam("questionCommentNo") int questionCommentNo) {
		return "teacher/modifyQuestionComment";
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
		// 서버에 업로드된 파일을 가져옴
		String fileName = FilePath.getFilePath()+questionCommentFileUUID;
		File file = new File(fileName);
		
		// 파일 컨텐츠를 읽어오기 위한 스트림 객체
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		
		// 다운로드할 파일의 컨텐츠를 채워주기 위한 스트림 객체
		ServletOutputStream sos = null;
		
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			
			// 원본 파일명을 받아오고, 브라우저별로 파일 이름이 제대로 인식되도록 처리함
			String originalFileName = questionCommentService.getQuestionCommentFileOriginal(questionCommentFileUUID);
			if (request.getHeader("user-agent").indexOf("MSIE") != -1 || request.getHeader("user-agent").indexOf("Trident") != -1) {
				originalFileName = URLEncoder.encode(originalFileName, "UTF-8").replaceAll("\\+", "%20");
			} else {
				originalFileName = new String(originalFileName.getBytes("UTF-8"), "ISO-8859-1");
			}
			
			sos = response.getOutputStream();
			
			// 서버에 업로드된 파일의 내용을 읽어, 다운로드할 파일의 컨텐츠를 채워넣음
			int read = 0;
			while ((read = bis.read()) != -1) {
				sos.write(read);
			}
			
			// 파일 다운로드 조회수를 채워줌
			questionCommentService.increaseQuestionCommentFileCount(questionCommentFileUUID);
		} catch (Exception e) {
			// 작업이 실패할 경우 스택트레이스를 출력함
			e.printStackTrace();
		} finally {
			// 작업이 끝나는대로 스트림 객체의 리소스를 반환함
			try {
				sos.close();
				bis.close();
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
