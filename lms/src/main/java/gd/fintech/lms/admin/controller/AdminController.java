package gd.fintech.lms.admin.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gd.fintech.lms.account.service.AccountService;
import gd.fintech.lms.admin.vo.Admin;

// 관리자 정보 관련 Controller

@Controller
public class AdminController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 계정 관련 Service
	@Autowired private AccountService accountService;
	
	// 관리자 정보 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. accountId(아이디)
	// 리턴값: adminDetail(관리자 정보를 출력하는 페이지)
	@GetMapping("/admin/adminDetail")
	public String adminDetail(Model model, HttpSession session) {
		// 세션에서 accountId(아이디)를 가져옴
		String accountId = (String)session.getAttribute("accountId");
		// accountId(아이디)에 해당하는 운용자 정보를 adminDetail(운영자 정보)에 저장
		Admin adminDetail = accountService.getAdminOne(accountId);
		logger.debug(adminDetail.toString());
		
		// 운영자 정보
		model.addAttribute("adminDetail", adminDetail);
		
		return "/admin/adminDetail";
	}
}
