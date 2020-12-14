package gd.fintech.lms.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import gd.fintech.lms.admin.service.MemberService;

// 로그인 처리를 위한 컨트롤러
@Controller
public class LoginController {
	@Autowired private MemberService memberService;	// 계정 회원 조회를 위한 서비스 주입
	

	
}
