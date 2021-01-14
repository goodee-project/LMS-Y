package gd.fintech.lms.student.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.student.service.StudentQuestionFileService;

@Controller
public class StudentQuestionFileController {
	
	@Autowired StudentQuestionFileService studentQuestionFileService;
	
	// 질문게시판 댓글의 첨부파일 다운로드
		// 매개변수:
		// RequestParam: questionCommentFileUUID(파일 UUID)
		// HttpServletRequest, HttpServletResponse
		// 리턴값: 파일 다운로드
		@GetMapping("/student/downloadStudentQuestionCommentFile")
		public void downloadQuestionCommentFile(
				@RequestParam("questionCommentFileUUID") String questionCommentFileUUID,
				HttpServletRequest request, HttpServletResponse response) {
			studentQuestionFileService.downloadStudentQuestionCommentFile(questionCommentFileUUID, request, response);
		}
}
