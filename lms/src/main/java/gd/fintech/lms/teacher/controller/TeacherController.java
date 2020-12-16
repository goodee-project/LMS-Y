package gd.fintech.lms.teacher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.teacher.service.TeacherService;
import gd.fintech.lms.teacher.vo.Teacher;

@Controller
public class TeacherController {
	//TeacherService 객체 주입
	@Autowired private TeacherService teacherService;
	
	//강사 정보 상세보기 페이지로 이동하는 메서드
	//리턴값: 강사 아이디로 로그인시 세션에 있는 아이디를 참조하여 정보를 띄우는 뷰페이지
	@GetMapping("/auth/teacher/teacherOne")
	public String getTeacherList(Model model,
			@RequestParam(value="accountId",required = false)String accountId) {
		Teacher teacher = teacherService.TeacherOne(accountId);
		model.addAttribute("teacher",teacher);
		return "teacherOne";
	}
}
