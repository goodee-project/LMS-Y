package gd.fintech.lms.student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.student.service.ClassRegistrationCancelService;
import gd.fintech.lms.student.service.ClassRegistrationService;
import gd.fintech.lms.student.vo.ClassRegistration;
import gd.fintech.lms.student.vo.ClassRegistrationCancel;

@Controller
public class ClassRegistrationController {
	
	@Autowired ClassRegistrationService classRegistrationService;
	@Autowired ClassRegistrationCancelService classRegistrationCancelService;
	
	//학생의 수강신청 목록 리스트(페이징)
	@GetMapping("student/classRegistrationList")
	public String getClassRegistrationListByPage(Model model,
			@RequestParam(value="currentPage")int currentPage) {
		int rowPerPage=10;
		List<ClassRegistration>selectClassRegistrationCount = classRegistrationService.getClassRegistrationListByPage(currentPage, rowPerPage);
		model.addAttribute("selectClassRegistrationCount",selectClassRegistrationCount);
		model.addAttribute("currentPage",currentPage);
		return "student/classRegistrationList";
	}
	//학생이 수강신청한 과목 정보보기(상세보기)
	@GetMapping("student/classRegistrationDetail")
	public String getClassRegistrtaionOne(Model model,
			@RequestParam(value="subjectNo")int subjectNo) {
		ClassRegistration selectClassRegistrationOne = classRegistrationService.getClassRegistrtaionOne(subjectNo);
		model.addAttribute("selectClassRegistrationOne",selectClassRegistrationOne);
		return "student/classRegistrationDetail";
	}
	
	//학생 수강신청 취소
	@GetMapping("student/classRegistrationCancel")
	public String removeClassRegistration(
			@RequestParam(value="classRegistrationNo")int classRegistrationNo) {
		classRegistrationCancelService.removeClassRegistration(classRegistrationNo);
		return "수강취소 사유 입력폼으로 이동";
	}
	
	//학생 수강신청 사유 입력폼
	@GetMapping("student/classRegistrationCanelReasonForm")
	public String addCancel() {
			return "student/classRegistrationCanelReasonForm";
	}
	
	//학생 수강신청 사유 입력 액션
	@PostMapping("student/classRegistrationCanelReasonForm")
	public String addCancel(
			@RequestParam(value="content")String content,
			@RequestParam(value="classRegistrationNo")int classRegistrationNo) {
		classRegistrationCancelService.addCancel(content, classRegistrationNo);
		return "redirect:/student/classRegistrationCanelReasonForm";
	}
}
