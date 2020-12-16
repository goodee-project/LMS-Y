package gd.fintech.lms.account.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import gd.fintech.lms.account.vo.Account;
import gd.fintech.lms.account.service.MemberService;

// 로그인 처리를 위한 컨트롤러 클래스

@Controller
public class LoginController {
	// MemberService 객체 주입
	@Autowired MemberService memberService;	
	
	// 처음 접속시 로그인을 위한 페이지로 이동하는 메소드
	// 리턴값: 전체 login 뷰페이지
	@GetMapping({"/","/login"})
	public String login() {
		return "login";
	}
	// 학생 로그인 화면으로 이동하는 메소드
	// 리턴값: 학생 login 뷰페이지
	@GetMapping("/studentLogin")
	public String studentLogin() {
		return "loginStudent";
	}
	// 강사 로그인 화면으로 이동하는 메소드
	// 리턴값: 강사 login 뷰페이지
	@GetMapping("/teacherLogin")
	public String teacherLogin() {
		return "loginTeacher";
	}
	// 운영자 로그인 화면으로 이동하는 메소드
	// 리턴값: 운영자 login 뷰페이지
	@GetMapping("/managerLogin")
	public String managerLogin() {
		return "loginManager";
	}	
	// 관리자 로그인 화면으로 이동하는 메소드
	// 리턴값: 관리자 login 뷰페이지
	@GetMapping("/adminLogin")
	public String adminLogin() {
		return "loginAdmin";
	}
	
	// 로그인뷰에서 로그인 버튼 클릭시 로그인 여부를 처리하는 메소드
	// 매개변수: 로그인뷰에 입력된 ID,PW 데이터 담는 커맨드객체, 세션을 이용할 HttpSession)
	// 리턴값: 각 계층별 권한에 따른 인덱스뷰로 리다이렉트(LoginController의 매핑값으로)
	@PostMapping("/login")
	public String login(Account account, HttpSession session) {
		// 서비스에서 계정 조회 결과 가져오기
		Account memberCk = memberService.getMemberById(account);
		// 계정이 없는 경우(조회 결과가 null인 경우)
		if(memberCk == null) {
			return "redirect:/login";
		}
		// 계정이 있는 경우 세션에 아이디 정보 담기
		session.setAttribute("accountId", memberCk.getAccountId());
		// 계정이 있는 경우 세션에 level 정보 담기
		session.setAttribute("accountLevel", memberCk.getAccountLevel());
		
		// 학생 권한에 따른 인덱스 페이지 이동
		if(memberCk.getAccountLevel() == 1) {
			return "redirect:/auth/student/index";
		}
		// 강사 권한에 따른 인덱스 페이지 이동
		else if(memberCk.getAccountLevel() == 2) {
			return "redirect:/auth/teacher/index";
		}
		// 운영자 권한에 따른 인덱스 페이지 이동
		else if(memberCk.getAccountLevel() == 3) {
			return "redirect:/auth/manager/index";
		}
		// 관리자 권한에 따른 페이지 이동
		else {
			return "redirect:/auth/admin/index";
		}	
	}
}
