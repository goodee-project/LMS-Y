package gd.fintech.lms.account.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.teacher.service.TeacherChartService;

// 계층별 권한에 따른 index 페이지로 이동하는 컨트롤러 클래스

@Controller
public class IndexController {
	// 강사 차트 서비스
	@Autowired TeacherChartService teacherChartService;
	
	// 학생 페이지로 이동하는 메소드
	// 리턴값: studentIndex(뷰이름)
	@GetMapping("/student/index")
	public String studentIndex() {
		return "student/studentIndex";
	}
	// 강사 페이지로 이동하는 메소드
	// 매개변수 : 
	// Model 
	// session
	// 리턴값: teacherIndex(뷰이름)
	@GetMapping("/teacher/index")
	public String teacherIndex(Model model, HttpSession session,
			@RequestParam(value="lectureNo", defaultValue = "-1") int lectureNo) {
		if(lectureNo==-1) {
			lectureNo = teacherChartService.getDefaultLecture(session);
		}
		model.addAttribute("lectureNo", lectureNo);
		model.addAttribute("lectureList", teacherChartService.getLectureListByAccountId(session));
		return "teacher/teacherIndex";
	}
	// 운영자 페이지로 이동하는 메소드
	// 리턴값: managerIndex(뷰이름)
	@GetMapping("/manager/index")
	public String managerIndex() {
		return "manager/managerIndex";
	}
	// 관리자 페이지로 이동하는 메소드
	// 리턴값: adminIndex(뷰이름)
	@GetMapping("/admin/index")
	public String adminIndex() {
		return "admin/adminIndex";
	}
}
