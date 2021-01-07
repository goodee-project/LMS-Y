package gd.fintech.lms.account.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gd.fintech.lms.account.service.AccountService;

// 계정을 찾을 시 DB에 등록된 계정의 이메일인지 확인하는 비동기 컨트롤러

@RestController
public class FindAccountRestController {
	// AccountService 객체 주입
	@Autowired private AccountService accountService;
	
	// DB에 등록된 이메일인지 확인하는 메소드
	// 매개변수: 이메일
	// 리턴값: 승인 또는 비승인
	@GetMapping("/findEmail")
	public String findAccountCheck(@RequestParam(value = "email", required = true) String accountEmail) {
		String emailCk = accountService.getAccountIdByEmail(accountEmail);
		// DB에 조회된 값이 있다면 패스, 없으면 비승인
		if(emailCk != null) {
			return "pass";
		}
		return "noPass";
	}
}
