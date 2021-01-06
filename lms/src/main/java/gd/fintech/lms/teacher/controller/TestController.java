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

// 강사가 시험을 관리하는 기능을 담당하는 컨트롤러

@Controller
public class TestController {
	// 시험 관리를 위한 서비스
	@Autowired private TestService testService;
	
	// 시험 정보 및 생성한 객관식 문제의 정보를 출력
	// 매개변수:
	// RequestParam: lectureNo(시험이 등록된 강좌 고유번호)
	// Model
	// 리턴값: teacher/testDetail.jsp 뷰 포워딩
	@SuppressWarnings("rawtypes") // size() 메서드를 쓰기 위해 넘어오는 list를 List 타입으로 변환하면서 뜨는 경고 무시 (제네릭 사용을 권고하는 경고)
	@GetMapping("/teacher/testDetail")
	public String testDetail(
			@RequestParam("lectureNo") int lectureNo,
			Model model) {
		Map<String, Object> map = testService.getTestDetailWithMultipleChoice(lectureNo);
		
		// view에서 사용하기 쉽게 모델에 들어갈 속성을 넣어줌 (Map을 그대로 넣는 대신 속성을 여러개로 쪼갬)
		model.addAttribute("test", map.get("test"));
		model.addAttribute("isEditable", map.get("isEditable"));
		model.addAttribute("multipleChoiceList", map.get("list"));
		model.addAttribute("multipleChoiceListSize", ((List)map.get("list")).size());
		model.addAttribute("lectureNo", lectureNo); // 강좌 메뉴에 사용될 lectureNo 등록
		return "teacher/testDetail";
	}

	// 시험 생성 폼 호출
	// 매개변수: lectureNo(시험을 등록할 강좌 고유번호)
	// 리턴값: teacher/createTest.jsp 뷰 포워딩
	@GetMapping("/teacher/createTest")
	public String createTest(
			@RequestParam("lectureNo") int lectureNo,
			Model model) {
		model.addAttribute("lectureNo", lectureNo); // 강좌 메뉴에 사용될 lectureNo 등록
		return "teacher/createTest";
	}

	// 시험 생성
	// 매개변수: Test(커맨드 객체)
	// 리턴값: teacher/testDetail 리다이렉트
	@PostMapping("/teacher/createTest")
	public String createTest(Test test) {
		testService.createTest(test);
		
		return "redirect:/teacher/testDetail?lectureNo="+test.getLectureNo();
	}

	// 시험 수정 폼 호출
	// 매개변수:
	// RequestParam: lectureNo(시험이 등록된 강좌 고유번호)
	// Model
	// 리턴값: teacher/modifyTest.jsp 뷰 포워딩
	@GetMapping("/teacher/modifyTest")
	public String modifyTest(
			@RequestParam("lectureNo") int lectureNo,
			Model model) {
		Test test = testService.getTestDetailWithDateFormatting(lectureNo);
		
		model.addAttribute("test", test);
		model.addAttribute("lectureNo", lectureNo); // 강좌 메뉴에 사용될 lectureNo 등록
		return "teacher/modifyTest";
	}

	// 시험 수정
	// 매개변수: Test(커맨드 객체)
	// 리턴값: teacher/testDetail 리다이렉트
	@PostMapping("/teacher/modifyTest")
	public String modifyTest(Test test) {
		testService.modifyTest(test);
		
		return "redirect:/teacher/testDetail?lectureNo="+test.getLectureNo();
	}

	// 객관식 문제 생성 폼 호출
	// 매개변수:
	// RequestParam: lectureNo(시험이 등록된 강좌 고유번호)
	// Model
	// 리턴값: teacher/createMultipleChoice.jsp 뷰 포워딩
	@GetMapping("/teacher/createMultipleChoice")
	public String createMultipleChoice(
			@RequestParam("lectureNo") int lectureNo,
			Model model) {
		Test test = testService.getTestDetail(lectureNo);
		
		model.addAttribute("test", test);
		model.addAttribute("lectureNo", lectureNo); // 강좌 메뉴에 사용될 lectureNo 등록
		return "teacher/createMultipleChoice";
	}

	// 객관식 문제 생성
	// 매개변수: MultipleChoiceForm(커맨드 객체)
	// 리턴값: teacher/testDetail 리다이렉트
	@PostMapping("/teacher/createMultipleChoice")
	public String createMultipleChoice(MultipleChoiceForm multipleChoiceForm) {
		testService.createMultipleChoice(multipleChoiceForm);
		
		return "redirect:/teacher/testDetail?lectureNo="+multipleChoiceForm.getLectureNo();
	}

	// 객관식 문제 수정 폼 호출
	// 매개변수:
	// RequestParam: multipleChoiceNo(수정할 객관식 문제 고유번호)
	// Model
	// 리턴값: teacher/modifyMultipleChoice.jsp 뷰 포워딩
	@GetMapping("/teacher/modifyMultipleChoice")
	public String modifyMultipleChoice(
			@RequestParam("multipleChoiceNo") int multipleChoiceNo,
			Model model) {
		MultipleChoice multipleChoice = testService.getMultipleChoiceDetail(multipleChoiceNo);
		
		// view에서 사용하기 쉽게 모델에 들어갈 속성을 넣어줌 (중복되는 데이터지만 편의성을 위해 model의 속성으로 한번 더 추가함)
		model.addAttribute("multipleChoice", multipleChoice);
		model.addAttribute("multipleChoiceExample1", multipleChoice.getMultipleChoiceExampleList().get(0).getMultipleChoiceExampleContent());
		model.addAttribute("multipleChoiceExample2", multipleChoice.getMultipleChoiceExampleList().get(1).getMultipleChoiceExampleContent());
		model.addAttribute("multipleChoiceExample3", multipleChoice.getMultipleChoiceExampleList().get(2).getMultipleChoiceExampleContent());
		model.addAttribute("multipleChoiceExample4", multipleChoice.getMultipleChoiceExampleList().get(3).getMultipleChoiceExampleContent());
		model.addAttribute("multipleChoiceExample5", multipleChoice.getMultipleChoiceExampleList().get(4).getMultipleChoiceExampleContent());
		model.addAttribute("lectureNo", multipleChoice.getLectureNo()); // 강좌 메뉴에 사용될 lectureNo 등록
		return "teacher/modifyMultipleChoice";
	}

	// 객관식 문제 수정
	// 매개변수: MultipleChoiceForm(커맨드 객체)
	// 리턴값: teacher/testDetail 리다이렉트
	@PostMapping("/teacher/modifyMultipleChoice")
	public String modifyMultipleChoice(MultipleChoiceForm multipleChoiceForm) {
		testService.modifyMultipleChoice(multipleChoiceForm);
		
		// 문제 수정 후 해당 문제와 연관된 강좌의 시험 정보 페이지로 이동
		return "redirect:/teacher/testDetail?lectureNo="+testService.getMultipleChoiceDetail(multipleChoiceForm.getMultipleChoiceNo()).getLectureNo();
	}

	// 객관식 문제 삭제
	// 매개변수: multipleChoiceNo(삭제할 객관식 문제 고유번호)
	// 리턴값: teacher/testDetail 리다이렉트
	@GetMapping("/teacher/removeMultipleChoice")
	public String removeMultipleChoice(
			@RequestParam("multipleChoiceNo") int multipleChoiceNo) {
		int lectureNo = testService.getMultipleChoiceDetail(multipleChoiceNo).getLectureNo(); // 문제 삭제 후 돌아갈 페이지를 찾기 위해 LectureNo를 가져옴
		testService.removeMultipleChoice(multipleChoiceNo);
		
		return "redirect:/teacher/testDetail?lectureNo="+lectureNo;
	}
}
