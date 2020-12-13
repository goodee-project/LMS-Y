package gd.fintech.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import gd.fintech.lms.service.MemberService;

@Controller
public class LoginController {
	@Autowired private MemberService memberService;
	
	// 로그인 처리를 위한 컨트롤러
}
