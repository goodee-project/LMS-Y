package gd.fintech.lms.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 계층별 권한에 따른 index 페이지 이동 컨트롤러
@Controller
public class IndexController {
	// 학생 페이지 이동
	@GetMapping("/auth/student/index")
	public String studentIndex() {
		return "studentIndex";
	}
	// 학생 페이지 이동
	@GetMapping("/auth/teacher/index")
	public String teacherIndex() {
		return "teacherIndex";
	}
	// 학생 페이지 이동
	@GetMapping("/auth/manager/index")
	public String managerIndex() {
		return "managerIndex";
	}
	// 학생 페이지 이동
	@GetMapping("/auth/admin/index")
	public String adminIndex() {
		return "adminIndex";
	}
}
