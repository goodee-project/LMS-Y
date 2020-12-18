package gd.fintech.lms.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.admin.service.AdminService;
import gd.fintech.lms.admin.vo.ManagerQueue;

// 관리자가 하는 업무 Controller

@Controller
public class AdminController {
	@Autowired AdminService adminService;
	
	// 회원가입 승인대기 중인 운영자 리스트 페이지로 이동하는 메소드
	// 매개변수: 
	@GetMapping("/auth/admin/managerQueueList")
	public String managerQueueList(Model model, @RequestParam(value = "currentPage") int currentPage) {
		// rowPerPage = 한 페이지에 보여줄 항목수
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
		
		// 한 페이지에 보여줄 항목수 미만의 항목이 있을 경우 마지막 페이지를 추가
		// 만약 총 항목수 나누기 한 페이지에 보여줄 항목수 값의 나머지가 0이 아니라면
		if (totalCount % rowPerPage != 0) {
			// 마지막 페이지에 1을 더함
			lastPage += 1;
		}
		
		// 만약 마지막 페이지가 0이라면
		if (lastPage == 0) {
			// currentPage(현재 페이지)도 0이 됨
			currentPage = 0;
		}
		
		// 만약 현재 페이지 나누기 네비게이션 바의 페이지의 나머지가 0이거나 현재 페이지가 0이 아니라면
		if (currentPage % navPerPage == 0 && currentPage != 0) {
			// 네비게이션 바의 첫 페이지는 네비게이션 바의 첫 페이지 빼기 네비게이션 바에 표시할 페이지 수가 됨
			navFirstPage = navFirstPage - navPerPage;
			// 네비게이션 바의 마지막 페이지는 네비게이션 바의 마지막 페이지 빼기 네비게이션 바에 표시할 페이지 수가 됨
			navLastPage = navLastPage - navPerPage;
		}
		
		model.addAttribute("managerQueueList", managerQueueList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("navPerPage", navPerPage);
		model.addAttribute("navFirstPage", navFirstPage);
		model.addAttribute("navLastPage", navLastPage);
		
		return "managerQueueList";
	}
	
	// 회원가입 승인대기 중인 운영자의 개인정보를 출력하는 메소드
	// 매개변수: accountId(아이디)
	// 리턴값: (아이디에 해당하는 운영자의 개인정보를 출력하는 페이지)
	
	// 회원가입 승인대기 중인 운영자의 가입을 승인하는 메소드
}
