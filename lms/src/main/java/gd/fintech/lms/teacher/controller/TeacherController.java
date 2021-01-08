package gd.fintech.lms.teacher.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import gd.fintech.lms.account.vo.Account;
import gd.fintech.lms.dto.TeacherForm;
import gd.fintech.lms.teacher.service.TeacherService;
import gd.fintech.lms.teacher.vo.AccountImage;
import gd.fintech.lms.teacher.vo.Teacher;


////강사 자신의 정보 수정 및 상세보기 할 수 있는 컨트롤러

@Controller
public class TeacherController {
	//Logger
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// TeacherService 객체 주입
	@Autowired private TeacherService teacherService;

	// 강사 정보 상세보기 페이지로 이동하는 메서드
	// 리턴값:강사 아이디로 로그인시 세션에 있는 아이디를 참조하여 정보를 띄우는 뷰페이지
	@GetMapping("/teacher/teacherOne")
	public String TeacherOne(Model model,HttpServletRequest request) {
		
		//세션정보 가져옴
		HttpSession session = ((HttpServletRequest)request).getSession();
		//세션에 있는 아이디 가져옴
		String accountId = (String)session.getAttribute("accountId");
		Map<String, Object> map = teacherService.getTeacherOne(accountId);
		//Teacher map = teacherService.getTeacherOne(accountId);
		model.addAttribute("accountId", accountId);
		model.addAttribute("map",map);
		return "/teacher/teacherOne";
	}

	// 강사정보 수정 폼으로 이동하는 메서드
	@GetMapping("/teacher/modifyTeacher")
	public String modifyTeacher(Model model, HttpServletRequest request) {
		//세션정보 가져옴
		HttpSession session = ((HttpServletRequest)request).getSession();
		//세션에 있는 아이디 가져옴
		String accountId = (String)session.getAttribute("accountId");
		Map<String, Object> map = teacherService.getTeacherOne(accountId);
		//Teacher map = teacherService.getTeacherOne(accountId);
		AccountImage myImage = teacherService.selectMyImage(accountId);
		//모델로 뷰에 값을 넘김
		model.addAttribute("myImage",myImage);
		model.addAttribute("session",session);
		model.addAttribute("accountId",accountId);
		model.addAttribute("map", map);
		return "/teacher/modifyTeacher";
	}

	// 강사정보 수정 액션
	@PostMapping("/teacher/modifyTeacher")
	public String modifyTeacher(TeacherForm teacherForm, HttpServletRequest request) {
		//세션정보 가져옴
		HttpSession session = ((HttpServletRequest)request).getSession();
		//세션에 있는 아이디 가져옴
		String accountId = (String)session.getAttribute("accountId");
		teacherService.modifyTeacherOne(teacherForm, session, accountId);
		return "redirect:/teacher/teacherOne";
	}
	
	//이미지삭제
	@GetMapping("/teacher/removeTeacherFile")
	public String removeTeacherFile(Model model,
			@RequestParam(value="accountId")String accountId,HttpServletRequest request) {
		AccountImage accountImage = teacherService.getTeacherImageFile(accountId);
		teacherService.removeFile(accountId);
		return "redirect:/teacher/modifyTeacher";
	}
	
	//강사 비밀번호 수정 폼
	@GetMapping("/teacher/modifyTeacherPw")
	public String modifyTeacherPw(HttpServletRequest request,Model model) {
		//세션정보 가져옴
		HttpSession session = ((HttpServletRequest)request).getSession();
		//세션에 있는 아이디 가져옴
		String accountId = (String)session.getAttribute("accountId");
		model.addAttribute("accountId",accountId);
		return "/teacher/modifyTeacherPw";
	}
	
	//강사 비밀번호 액션
	@PostMapping("/teacher/modifyTeacherPw")
	public String modifyTeacherPw(Account account) {
		teacherService.modifyTeacherPw(account);
		
		return "redirect:/logout";
		
	}
}
