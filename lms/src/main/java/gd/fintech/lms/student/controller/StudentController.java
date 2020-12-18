package gd.fintech.lms.student.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.student.service.StudentService;
import gd.fintech.lms.student.vo.Student;

@Controller
public class StudentController {
	Logger logger = LoggerFactory.getLogger(StudentController.class);
	@Autowired StudentService studentService;
	@Autowired 
	
	
	
	//다시짜기
	
	//학생 상세보기
	@GetMapping("/auth/student/studentDetail")
	public String studentDetail(Model model,
			@RequestParam(value="accountId", required = false)String accountId) {
		Student studentOne=studentService.getStudentDetail(accountId);
		model.addAttribute("accountId",accountId);
		return "studentDetail";
	}
	//학생 과제물 보기
	@GetMapping("/auth/student/studentReport")
	public String studentReport(Model model,
			@RequestParam(value="accountId")String accountId) {
		Student studentReport=studentService.getStudentResult(accountId);
		model.addAttribute("accountId",accountId);
		return "studentReport";
	}
	//학생정보 수정 폼
	@GetMapping("/auth/studentModify")
	public String modifyStudent(Model model,
			@RequestParam(value="accountId")String accountId) {
		model.addAttribute("accountId",accountId);
		return "studentModify";
	}
	//학생정보 수정 액션
	
}
