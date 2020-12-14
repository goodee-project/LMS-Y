package gd.fintech.lms.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import gd.fintech.lms.admin.service.MemberService;

@Controller
public class MemberController {
	@Autowired private MemberService memberService;
	// 회원 관리를 위한 컨트롤러
}
