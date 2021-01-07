package gd.fintech.lms.account.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.MailUtil;
import gd.fintech.lms.account.service.AccountService;

// 계정 찾기와 관련된 컨트롤러

@Controller
public class FindAccountController {	
	// AccountService 객체 주입
	@Autowired private AccountService accountService;
	
	// 계정찾기 페이지로 넘어가는 메소드
	// 리턴값: 계정 찾기 페이지
	@GetMapping("/findAccount")
	public String findAccount() {
		return "account/findAccount";
	}
	
	// 입력된 이메일로 메일을 보내는 메소드
	// 매개변수: 이메일
	// 리턴값: 이메일 보내기
	@RequestMapping(value = "/findAccount", method = RequestMethod.POST)
	public String findAccountByEmail(@RequestParam(value = "email", required = true) String email) throws Exception {
		String accountId = accountService.getAccountIdByEmail(email);	// 아이디 가져오기
		String newPw = accountService.getNewPw();	// 임시 비밀번호 생성
		Map<String, Object> map = new HashMap<>();
		map.put("accountEmail", email);
		map.put("accountPw", newPw);
		// 비밀번호 변경
		accountService.modifyAcccountPw(map);
		
		String subject = "[계정찾기] 아이디/임시 비밀번호 발급 안내";
		String msg = "";
		msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
		msg += "<h3 style='color: blue;'><strong>발급된 것은 임시 비밀번호입니다. 로그인 후에 비밀번호를 변경하세요.</strong>";
		msg += "<p>아이디 : <strong>" + accountId + "</strong></p>";
		msg += "<p>임시 비밀번호 : <strong>" + newPw + "</strong></p></div>";
		
		MailUtil.sendMail(email, subject, msg);
		return "account/findAccount";
	}
}
