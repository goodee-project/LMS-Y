package gd.fintech.lms.admin.controller;

import java.util.List;

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
		// 한 페이지에 보여줄 항목수 15개
		int rowPerPage = 15;
		// 총 항목수
		int totalCount = adminService.getManagerQueueCount();
		// 마지막 페이지
		int lastPage = totalCount / rowPerPage;
		// 페이지 네비게이션 바에 표시할 페이지 수
		int navPerPage = 10;
		// 페이지 네비게이션 바의 첫번째 페이지
		int navFirstPage = currentPage - (currentPage % navPerPage) + 1;
		// 페이지 네비게이션 바의 마지막 페이지
		int navLastPage = (navFirstPage + navPerPage) - 1;
		
		List<ManagerQueue> managerQueueList = adminService.getManagerQueueList(currentPage, rowPerPage);
		logger.debug(managerQueueList.toString());
		
		// 한 페이지에 보여줄 항목수 미만의 항목이 남을 경우 마지막 페이지를 한 페이지 추가
		if (totalCount % rowPerPage != 0) {
			lastPage += 1;
		}
		
		// 만약 마지막 페이지가 0이라면 현재 페이지도 0이 됨
		if (lastPage == 0) {
			currentPage = 0;
		}
		
		// 만약 현재 페이지 나누기 네비게이션 바의 페이지의 나머지가 0이거나 현재 페이지가 0이 아니라면
		// 네비게이션 바의 첫 페이지는 네비게이션 바의 첫 페이지에서 네비게이션 바에 표시할 페이지 수 뺀 값이 됨
		// 네비게이션 바의 마지막 페이지는 네비게이션 바의 마지막 페이지에서 네비게이션 바에 표시할 페이지 수를 뺸 값이 됨
		if (currentPage % navPerPage == 0 && currentPage != 0) {
			navFirstPage = navFirstPage - navPerPage;
			navLastPage = navLastPage - navPerPage;
		}
		
		model.addAttribute("managerQueueList", managerQueueList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("navPerPage", navPerPage);
		model.addAttribute("navFirstPage", navFirstPage);
		model.addAttribute("navLastPage", navLastPage);
		
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
