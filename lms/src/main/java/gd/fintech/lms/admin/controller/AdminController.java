package gd.fintech.lms.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.account.service.AccountService;
import gd.fintech.lms.admin.service.AdminService;
import gd.fintech.lms.admin.vo.Admin;
import gd.fintech.lms.admin.vo.ManagerQueue;

// 관리자가 하는 업무 Controller

@Controller
public class AdminController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 계정 관련 Service
	@Autowired private AccountService accountService;
	// 관리자가 하는 업무 Service
	@Autowired private AdminService adminService;
	
	// 관리자 정보 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. accountId(아이디)
	// 리턴값: adminDetail(관리자 정보를 출력하는 페이지)
	@GetMapping("/admin/adminDetail")
	public String adminDetail(Model model, HttpServletRequest request) {
		// 세션정보를 가져옴
		HttpSession session = ((HttpServletRequest)request).getSession();
		String accountId = (String)session.getAttribute("accountId");
		Admin adminDetail = accountService.getAdminOne(accountId);
		model.addAttribute("accountId", accountId);
		model.addAttribute("adminDetail", adminDetail);
		
		return "/admin/adminDetail";
	}
	
	// 회원가입 승인대기 중인 운영자 목록 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. currentPage(현재 페이지)
	// 리턴값: managerQueueList(회원가입 승인대기 중인 운영자 목록 페이지)
	// 회원가입 승인대기 중인 운영자 목록을 페이징하여 출력
	// 페이지 표시 네비게이션 바 출력
	@GetMapping("/admin/managerQueueList")
	public String managerQueueList(Model model, @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage) {
		Map<String, Object> map = adminService.getManagerQueueList(currentPage);
		
		// 승인대기 중인 운영자 목록
		model.addAttribute("managerQueueList", map.get("managerQueueList"));
		
		// 페이지 관련 값
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("pageNaviSize", map.get("pageNaviSize"));
		model.addAttribute("pageNaviBegin", map.get("pageNaviBegin"));
		model.addAttribute("pageNaviEnd", map.get("pageNaviEnd"));
		
		return "/admin/managerQueueList";
	}
	
	// 회원가입 승인대기 중인 운영자의 개인정보 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. accountId(아이디)
	// 리턴값: managerQueueDetail(아이디에 해당하는 운영자 개인정보 페이지)
	@GetMapping("/admin/managerQueueDetail")
	public String managerQueueDetail(Model model, @RequestParam(value = "accountId") String accountId) {
		ManagerQueue managerQueueDetail = adminService.getManagerQueueDetail(accountId);
		logger.debug(managerQueueDetail.toString());
		model.addAttribute("managerQueueDetail", managerQueueDetail);
		
		return "/admin/managerQueueDetail";
	}
	
	// 회원가입 승인대기 중인 운영자의 가입을 승인하는 메소드
	// 매개변수: accountId(아이디)
	// 리턴값: managerQueueList 페이지로 이동
	// 운영자의 개인정보를 Manager에 저장
	// 운영자의 개인정보을 ManagerQueue에서 삭제
	// 운영자의 계정의 활성화 여부를 활성화로 변경
	// 회원가입 승인대기 중인 운영자 목록 페이지로 이동
	@GetMapping("/admin/approveManagerMembership")
	public String approveManagerMembership(@RequestParam(value = "accountId") String accountId) {
		logger.debug(accountId.toString());
		adminService.approveManagerMembership(accountId);
		
		return "redirect:/admin/managerQueueList";
	}
	
	// 회원가입 승인대기 중인 운영자의 가입을 거부하는 메소드
	// 매개변수: accountId(아이디)
	// 리턴값: managerQueueList 페이지로 이동
	// 운영자의 개인정보를 ManagerQueue에서 삭제
	// 운영자의 계정의 활성화 여부를 탈퇴로 변경
	// 회원가입 승인대기 중인 운영자 목록 페이지로 이동
	@GetMapping("/admin/disapproveManagerMembership")
	public String disapproveManagerMembership(@RequestParam(value = "accountId") String accountId) {
		logger.debug(accountId.toString());
		adminService.disapproveManagerMembership(accountId);
		
		return "redirect:/admin/managerQueueList";
	}
}
