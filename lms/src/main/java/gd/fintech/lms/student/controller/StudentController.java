package gd.fintech.lms.student.controller;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.student.service.StudentService;
import gd.fintech.lms.student.vo.Student;

@Controller
public class StudentController {
	Logger logger = LoggerFactory.getLogger(StudentController.class);
	@Autowired StudentService studentService;
	 
	//학생 자신의 과제물을 보기
	@GetMapping("/student/?")
	public String getReportDetail(Model model,
			@RequestParam(value="accountId")String accountId) {
		List<Student> studentReportDetail = studentService.getReportDetail(accountId);
		model.addAttribute("studentReportDetail",studentReportDetail);
		return "/student/?";
		
	}
	//학생 자신의 정보 상세보기
	@GetMapping("/student/studentDetail")
	public String getStudentDetail(Model model,
			@RequestParam(value="accountId",required=false)String accountId) {
		Student studentDetail= studentService.getStudentDetail(accountId);
		model.addAttribute("studentDetail",studentDetail);
		return "/student/studentDetail";
	}	
	//학생정보 수정 폼
	@GetMapping("student/studentModify")
	public String getStudentModify(Model model,
			@RequestParam(value="accountId")String accountId) {
		Student studentModify = studentService.getStudentDetail(accountId); 
		model.addAttribute("studentModify",studentModify);
		return "/student/studentModify";
	}
	
	//학생정보 수정 액션
	@PostMapping("student/studentModify")
	public String getStudentModify(Student student) {
		studentService.modifyStudent(student);
		return "/student/studentDetail";
	}
}
