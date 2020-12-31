package gd.fintech.lms.teacher.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.dto.MultipleChoiceForm;
import gd.fintech.lms.teacher.service.TestService;
import gd.fintech.lms.teacher.vo.MultipleChoice;
import gd.fintech.lms.teacher.vo.Test;

@Controller
public class TestController {
	@Autowired private TestService testService;
	
	@GetMapping("/teacher/testDetail")
	public String testDetail(
			@RequestParam("lectureNo") int lectureNo,
			Model model) {
		Test test = testService.getTestDetail(lectureNo);
		Map<String, Object> map = testService.getMultipleChoiceList(lectureNo);
		
		model.addAttribute("test", test);
		model.addAttribute("isEditable", map.get("isEditable"));
		model.addAttribute("multipleChoiceList", map.get("list"));
		model.addAttribute("multipleChoiceListSize", ((List)map.get("list")).size());
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
		test.setTestStartDate(test.getTestStartDate().replaceAll("\\s*\\d+:\\d+:\\d+\\.\\d+$", ""));
		test.setTestEndDate(test.getTestEndDate().replaceAll("\\s*\\d+:\\d+:\\d+\\.\\d+$", ""));
		
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
			@RequestParam("multipleChoiceNo") int multipleChoiceNo,
			Model model) {
		MultipleChoice multipleChoice = testService.getMultipleChoiceDetail(multipleChoiceNo);
		
		model.addAttribute("multipleChoice", multipleChoice);
		model.addAttribute("multipleChoiceExample1", multipleChoice.getMultipleChoiceExampleList().get(0).getMultipleChoiceExampleContent());
		model.addAttribute("multipleChoiceExample2", multipleChoice.getMultipleChoiceExampleList().get(1).getMultipleChoiceExampleContent());
		model.addAttribute("multipleChoiceExample3", multipleChoice.getMultipleChoiceExampleList().get(2).getMultipleChoiceExampleContent());
		model.addAttribute("multipleChoiceExample4", multipleChoice.getMultipleChoiceExampleList().get(3).getMultipleChoiceExampleContent());
		model.addAttribute("multipleChoiceExample5", multipleChoice.getMultipleChoiceExampleList().get(4).getMultipleChoiceExampleContent());
		return "teacher/modifyMultipleChoice";
	}
	
	@PostMapping("/teacher/modifyMultipleChoice")
	public String modifyMultipleChoice(MultipleChoiceForm multipleChoiceForm) {
		testService.modifyMultipleChoice(multipleChoiceForm);
		
		return "redirect:/teacher/testDetail?lectureNo="+testService.getMultipleChoiceDetail(multipleChoiceForm.getMultipleChoiceNo()).getLectureNo();
	}
	
	@GetMapping("/teacher/removeMultipleChoice")
	public String removeMultipleChoice(
			@RequestParam("multipleChoiceNo") int multipleChoiceNo) {
		int lectureNo = testService.getMultipleChoiceDetail(multipleChoiceNo).getLectureNo();
		testService.removeMultipleChoice(multipleChoiceNo);
		
		return "redirect:/teacher/testDetail?lectureNo="+lectureNo;
	}
}
