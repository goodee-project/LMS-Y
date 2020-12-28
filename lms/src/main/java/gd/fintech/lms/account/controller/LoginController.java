package gd.fintech.lms.account.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import gd.fintech.lms.account.vo.Account;
import gd.fintech.lms.account.vo.LoginLog;
import gd.fintech.lms.account.service.AccountService;
import gd.fintech.lms.account.service.LoginLogService;
import gd.fintech.lms.AccountLevel;

// 로그인 처리를 위한 컨트롤러 클래스

@Controller
public class LoginController {
	// MemberService 객체 주입
	@Autowired private AccountService accountService;
	// LoginLogService 객체 주입
	@Autowired private LoginLogService loginLogService;
	
	// 처음 접속시 로그인을 위한 페이지로 이동하는 메소드
	// 만약 세션에 계정이 등록되어 있는데 로그인 페이지로 접속시 해당 계정권한의 인덱스로 이동
	// 세션에 등록된 계정이 상위 레벨의 페이지로 접속시 해당 세션에 등록된 권한 인덱스 페이지로 이동
	// 매개변수: HttpSession
	// 리턴값: 전체 login 뷰페이지
	@GetMapping({"/","/login"})
	public String login(HttpSession session) {
		if(session.getAttribute("accountLevel") != null && session.getAttribute("accountLevel").equals(AccountLevel.STUDENT.getValue())) {
			return "redirect:/student/index";
		}
		else if(session.getAttribute("accountLevel") != null && session.getAttribute("accountLevel").equals(AccountLevel.TEACHER.getValue())) {
			return "redirect:/teacher/index";
		}
		else if(session.getAttribute("accountLevel") != null && session.getAttribute("accountLevel").equals(AccountLevel.MANAGER.getValue())) {
			return "redirect:/manager/index";
		}
		else if(session.getAttribute("accountLevel") != null && session.getAttribute("accountLevel").equals(AccountLevel.ADMIN.getValue())) {
			return "redirect:/admin/index";
		}
		else {
			return "account/login";
		}
	}
	// 학생 로그인 화면으로 이동하는 메소드
	// 리턴값: 학생 login 뷰페이지
	@GetMapping("/studentLogin")
	public String studentLogin() {
		return "account/loginStudent";
	}
	// 강사 로그인 화면으로 이동하는 메소드
	// 리턴값: 강사 login 뷰페이지
	@GetMapping("/teacherLogin")
	public String teacherLogin() {
		return "account/loginTeacher";
	}
	// 운영자 로그인 화면으로 이동하는 메소드
	// 리턴값: 운영자 login 뷰페이지
	@GetMapping("/managerLogin")
	public String managerLogin() {
		return "account/loginManager";
	}	
	// 관리자 로그인 화면으로 이동하는 메소드
	// 리턴값: 관리자 login 뷰페이지
	@GetMapping("/adminLogin")
	public String adminLogin() {
		return "account/loginAdmin";
	}
	
	// 로그인뷰에서 로그인 버튼 클릭시 로그인 여부를 처리하는 메소드
	// 매개변수: 로그인뷰에 입력된 ID,PW 데이터 담는 커맨드객체, 세션을 이용할 HttpSession), 페이지레벨(계층별 페이지에 레벨을 두어 접근가능한 계정을 구분하기 위함)
	// 리턴값: 각 계층별 권한에 따른 인덱스뷰로 리다이렉트(LoginController의 매핑값으로)
	@PostMapping("/login")
	public String login(Account account, HttpSession session,
			@RequestParam(value = "pageLevel") int pageLevel,
			HttpServletResponse response) throws IOException {
		// 서비스에서 계정 조회 결과(accountId, accountLevel) 가져오기
		Account memberCk = accountService.getMemberById(account);
		
		// 계정이 없는 경우
		if(memberCk == null) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 정보를 다시 확인하세요.'); history.go(-1);</script>");
			out.flush();
			return "account/login";
		}
		// 로그인 페이지별 상위 레벨의 계정에 접근시 해당 계정에 대한 로그인 제한하기
		else if(pageLevel != memberCk.getAccountLevel()) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('해당 계정에 접근권한이 없습니다.'); history.go(-1);</script>");
			out.flush();
			return "account/login";
		}

		// 계정 활성화 상태 체크
		if(memberCk.getAccountState().equals("대기")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('계정 승인 대기중입니다.'); history.go(-1);</script>");
			out.flush();
			return "account/login";
		}else if(memberCk.getAccountState().equals("거절")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('승인 거절 처리되었습니다'); history.go(-1);</script>");
			out.flush();
			return "account/login";
		}else if(memberCk.getAccountState().equals("탈퇴")) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('해당 계정은 탈퇴처리되었습니다'); history.go(-1);</script>");
			out.flush();
			return "account/login";
		}else {
			// 계정이 있는 경우 로그인 로그 기록 남기기
			LoginLog loginLog = new LoginLog();
			loginLog.setLoginId(session.getId());
			loginLog.setAccountId(memberCk.getAccountId());
			loginLogService.createLoginLogByAccountId(loginLog);
			// 계정이 있는 경우 세션에 아이디 정보 담기
			session.setAttribute("accountId", memberCk.getAccountId());
			// 계정이 있는 경우 세션에 level 담기
			session.setAttribute("accountLevel", memberCk.getAccountLevel());
			
			// 학생 권한에 따른 인덱스 페이지 이동
			if(memberCk.getAccountLevel() == AccountLevel.STUDENT.getValue()) {
				return "redirect:/student/index";
			}
			// 강사 권한에 따른 인덱스 페이지 이동
			else if(memberCk.getAccountLevel() == AccountLevel.TEACHER.getValue()) {
				return "redirect:/teacher/index";
			}
			// 운영자 권한에 따른 인덱스 페이지 이동
			else if(memberCk.getAccountLevel() == AccountLevel.MANAGER.getValue()) {
				return "redirect:/manager/index";
			}
			// 관리자 권한에 따른 페이지 이동
			else {
				return "redirect:/admin/index";
			}
		}
	}
	
	// 로그아웃을 위한 기능 메소드
	// 매개변수: 세션값(세션에 등록된 계정을 삭제하기 위함)
	// 리턴값: 초기 로그인뷰
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); 	// 세션 종료
		return "account/login";
	}
}
