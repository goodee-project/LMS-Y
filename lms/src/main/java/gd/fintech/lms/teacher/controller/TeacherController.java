package gd.fintech.lms.teacher.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.account.vo.Address;
import gd.fintech.lms.teacher.service.TeacherService;
import gd.fintech.lms.teacher.vo.Teacher;

@Controller
public class TeacherController {
	//TeacherService 객체 주입
	@Autowired private TeacherService teacherService;
	
	//강사 정보 상세보기 페이지로 이동하는 메서드
	//리턴값: 강사 아이디로 로그인시 세션에 있는 아이디를 참조하여 정보를 띄우는 뷰페이지
	@GetMapping("/auth/teacher/teacherOne")
	public String TeacherOne(Model model,
			@RequestParam(value="accountId",required = false)String accountId) {
		Teacher teacher = teacherService.getTeacherOne(accountId);
		model.addAttribute("teacher",teacher);
		return "teacherOne";
	}
	
	//강사정보 수정 폼으로 이동하는 메서드
	@GetMapping("/auth/teacher/modifyTeacher")
	public String modifyTeacher(Model model,
			@RequestParam(value="accountId")String accountId) {
		Teacher teacher = teacherService.getTeacherOne(accountId);
		model.addAttribute("teacher",teacher);
		return "modifyTeacher";
	}
	
	//강사정보 수정 액션
	@PostMapping("/auth/teacher/modifyTeacher")
	public String modifyTeacher(Teacher teacher) {
		teacherService.getTeacherUpdate(teacher);
		return "teacherOne";
	}
	
	//우변펀호로 주소 정보를 띄우는 뷰페이지
		@GetMapping("/auth/addressOne")
		public String AddressOne(Model model,
				@RequestParam(value="zipCode",required = false)String zipCode) {
			List<Address> address = teacherService.getAddressByZipCode(zipCode);
			model.addAttribute("address",address);
			return "addressOne";
		}
}
