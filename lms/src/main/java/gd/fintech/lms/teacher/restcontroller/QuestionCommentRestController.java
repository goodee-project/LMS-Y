package gd.fintech.lms.teacher.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gd.fintech.lms.teacher.service.QuestionCommentService;

@RestController
public class QuestionCommentRestController {
	@Autowired private QuestionCommentService questionCommentService;
	
	@PostMapping("/teacher/removeQuestionCommentFile")
	public boolean removeQuestionCommentFile(
			@RequestParam("questionCommentFileUUID") String questionCommentFileUUID) {
		questionCommentService.removeQuestionCommentFile(questionCommentFileUUID);
		return true;
	}
}
