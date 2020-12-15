package gd.fintech.lms.account.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import gd.fintech.lms.account.vo.Account;
import gd.fintech.lms.account.service.MemberService;

// 로그인 처리를 위한 컨트롤러
@Controller
public class LoginController {	
	@Autowired MemberService memberService;	// MemberService 객체 주입
	// 처음 접속시 로그인을 위한 페이지로 이동
	@GetMapping({"/","/login"})
	public String login() {
		return "login";		// 로그인 뷰로 포워딩
	}
	// 학생 로그인 화면으로 이동
	@GetMapping("/studentLogin")
	public String studentLogin() {
		return "loginStudent";
	}
	// 강사 로그인 화면으로 이동
	@GetMapping("/teacherLogin")
	public String teacherLogin() {
		return "loginTeacher";
	}
	// 운영자 로그인 화면으로 이동
	@GetMapping("/managerLogin")
	public String managerLogin() {
		return "loginManager";
	}	
	// 관리자 로그인 화면으로 이동
	@GetMapping("/adminLogin")
	public String adminLogin() {
		return "loginAdmin";
	}
	
	// 로그인 여부 처리
	@PostMapping("/adminLogin")
	public String adminLogin(Account account, HttpSession session) {	// 매개변수(입력 id,pw), HttpSession 사용
		Account memberCk = memberService.getMemberById(account);		// 계정 조회 내용 가져오기
		// 계정이 없는 경우
		if(memberCk == null) {
			return "redirect:/login";
		}				
		// 계정이 있는 경우
		session.setAttribute("accountId", account.getAccountId());			// 세션에 id 정보 담기
		session.setAttribute("accountLevel", account.getAccountLevel());	// 세션에 level 정보 담기
		// 학생 권한 페이지 이동
		if(account.getAccountLevel() == 1) {
			return "redirect:/auth/student/index";
		}
		// 강사 권한 페이지 이동
		else if(account.getAccountLevel() == 2) {
			return "redirect:/auth/teacher/index";
		}
		// 운영자 권한 페이지 이동
		else if(account.getAccountLevel() == 3) {
			return "redirect:/auth/manager/index";
		}
		// 관리자 권한 페이지 이동
		else {
			return "redirect:/auth/admin/index";
		}	
	}
}
