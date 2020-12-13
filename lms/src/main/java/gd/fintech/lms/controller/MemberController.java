package gd.fintech.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import gd.fintech.lms.service.MemberService;

@Controller
public class MemberController {
	@Autowired private MemberService memberService;
	// 회원 관리를 위한 컨트롤러
}
