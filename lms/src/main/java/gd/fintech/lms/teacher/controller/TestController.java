package gd.fintech.lms.teacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.dto.MultipleChoiceForm;
import gd.fintech.lms.teacher.service.TestService;
import gd.fintech.lms.teacher.vo.Test;

@Controller
public class TestController {
	@Autowired private TestService testService;
	
	@GetMapping("/teacher/testDetail")
	public String testDetail(
			@RequestParam("lectureNo") int lectureNo,
			Model model) {
		Test test = testService.getTestDetail(lectureNo);
		
		model.addAttribute("test", test);
		return "teacher/testDetail";
	}
	
	@GetMapping("/teacher/createTest")
	public String createTest(
			@RequestParam("lectureNo") int lectureNo) {
		return "teacher/createTest";
	}
	
	@PostMapping("/teacher/createTest")
	public String createTest(Test test) {
		testService.createTest(test);
		
		return "redirect:/teacher/testDetail?lectureNo="+test.getLectureNo();
	}
	
	@GetMapping("/teacher/modifyTest")
	public String modifyTest(
			@RequestParam("lectureNo") int lectureNo,
			Model model) {
		Test test = testService.getTestDetail(lectureNo);
		
		model.addAttribute("test", test);
		return "teacher/modifyTest";
	}
	
	@PostMapping("/teacher/modifyTest")
	public String modifyTest(Test test) {
		testService.modifyTest(test);
		
		return "redirect:/teacher/testDetail?lectureNo="+test.getLectureNo();
	}
	
	@GetMapping("/teacher/createMultipleChoice")
	public String createMultipleChoice(
			@RequestParam("lectureNo") int lectureNo,
			Model model) {
		Test test = testService.getTestDetail(lectureNo);
		
		model.addAttribute("test", test);
		return "teacher/createMultipleChoice";
	}
	
	@PostMapping("/teacher/createMultipleChoice")
	public String createMultipleChoice(MultipleChoiceForm multipleChoiceForm) {
		testService.createMultipleChoice(multipleChoiceForm);
		
		return "redirect:/teacher/testDetail?lectureNo="+multipleChoiceForm.getLectureNo();
	}
	
	@GetMapping("/teacher/modifyMultipleChoice")
	public String modifyMultipleChoice(
			@RequestParam("multipleChoiceNo") int multipleChoiceNo) {
		return "teacher/modifyMultipleChoice";
	}
}
