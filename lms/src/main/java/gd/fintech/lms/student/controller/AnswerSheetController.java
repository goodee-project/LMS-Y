package gd.fintech.lms.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import gd.fintech.lms.student.service.AnswerSheetService;
import gd.fintech.lms.student.vo.AnswerSheet;

@Controller
public class AnswerSheetController {
	@Autowired AnswerSheetService answerSheetService;
	
	//학생이 낼 답안지 입력 폼
	@GetMapping("student/addAnswerSheet")
	public String addAnswerSheet() {
		return "addAnswerSheet";
	}
	
	//학생이 낼 답안지 입력 액션
	@PostMapping("student/addAnswerSheet")
	public String addAnswerSheet(AnswerSheet answerSheet) {
		answerSheetService.addAnswerSheet(answerSheet);
		return "redirect:/student/addAnswerSheet";
	}
	
	//학생이 낸 답지 상세보기( 점수포함)
	@GetMapping("student/answerSheetDetail")
	public String answerSheetDetail(String accountId) {
		return "answerSheetDetail";
	}
}
