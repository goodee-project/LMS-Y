package gd.fintech.lms.student.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.student.service.StudentService;
import gd.fintech.lms.student.vo.Student;

@Controller
public class StudentController {
	@Autowired StudentService studentService;
	
	//학생 상세보기
	@GetMapping("/auth/student/&&&&&&")
	public String studentOne(Model model,
			@RequestParam(value="accountId")String accountId) {
		Student studentOne=studentService.getStudentOne(accountId);
		model.addAttribute("studentOne",studentOne);
		return "studentOne";
	}
}
