package gd.fintech.lms.admin.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.admin.service.DormantAccountService;

// 휴면계정 관리 관련 Controller

@Controller
public class DormantAccountController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
		
	// 휴면계정 관련 Service
	@Autowired private DormantAccountService dormantAccountService;
	
	// 휴면계정 목록 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. currentPage(현재 페이지)
	// #3. searchType(검색조건)
	// #4. searchKeyword(검색어)
	// 리턴값: dormantAccountList(휴면계정 목록 페이지)
	// 회원가입 승인대기 중인 운영자 목록을 페이징하여 출력
	// 페이지 표시 네비게이션 바 출력
	@GetMapping("/admin/dormantAccountListByManager")
	public String dormantAccountListByManager(Model model,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "searchType", required = false, defaultValue = "all") String searchType,
			@RequestParam(value = "searchKeyword", required = false) String searchKeyword) {
		Map<String, Object> map = dormantAccountService.getDormantAccountListByManager(currentPage, searchType, searchKeyword);
		
		// 휴면계정 목록
		model.addAttribute("dormantAccountListByManager", map.get("dormantAccountListByManager"));
		
		// 검색 관련 값
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchKeyword", searchKeyword);
		
		// 페이지 관련 값
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("pageNaviSize", map.get("pageNaviSize"));
		model.addAttribute("pageNaviBegin", map.get("pageNaviBegin"));
		model.addAttribute("pageNaviEnd", map.get("pageNaviEnd"));
		
		return "/admin/dormantAccountListByManager";
	}
	
	// 강사 휴면계정 목록 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. currentPage(현재 페이지)
	// #3. searchType(검색조건)
	// #4. searchKeyword(검색어)
	// 리턴값: dormantAccountList(휴면계정 목록 페이지)
	// 회원가입 승인대기 중인 운영자 목록을 페이징하여 출력
	// 페이지 표시 네비게이션 바 출력
	@GetMapping("/admin/dormantAccountListByTeacher")
	public String dormantAccountListByTeacher(Model model,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "searchType", required = false, defaultValue = "all") String searchType,
			@RequestParam(value = "searchKeyword", required = false) String searchKeyword) {
		logger.debug("강사 휴면계정 리스트 디버그 현재 페이지 " + currentPage);
		logger.debug("강사 휴면계정 리스트 디버그 검색조건 " + searchType);
		logger.debug("강사 휴면계정 리스트 디버그 검색어 " + searchKeyword);
		Map<String, Object> map = dormantAccountService.getDormantAccountListByTeacher(currentPage, searchType, searchKeyword);
		
		// 휴면계정 목록
		model.addAttribute("dormantAccountListByTeacher", map.get("dormantAccountListByTeacher"));
		
		// 검색 관련 값
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchKeyword", searchKeyword);
		
		// 페이지 관련 값
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("pageNaviSize", map.get("pageNaviSize"));
		model.addAttribute("pageNaviBegin", map.get("pageNaviBegin"));
		model.addAttribute("pageNaviEnd", map.get("pageNaviEnd"));
		
		return "/admin/dormantAccountListByTeacher";
	}
	
	// 학생 휴면계정 목록 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. currentPage(현재 페이지)
	// #3. searchType(검색조건)
	// #4. searchKeyword(검색어)
	// 리턴값: dormantAccountList(휴면계정 목록 페이지)
	// 학생 휴면계정 목록을 페이징하여 출력
	// 페이지 표시 네비게이션 바 출력
	@GetMapping("/admin/dormantAccountListByStudent")
	public String dormantAccountListByStudent(Model model,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "searchType", required = false, defaultValue = "all") String searchType,
			@RequestParam(value = "searchKeyword", required = false) String searchKeyword) {
		Map<String, Object> map = dormantAccountService.getDormantAccountListByStudent(currentPage, searchType, searchKeyword);
		
		// 휴면계정 목록
		model.addAttribute("dormantAccountListByStudent", map.get("dormantAccountListByStudent"));
		
		// 검색 관련 값
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchKeyword", searchKeyword);
		
		// 페이지 관련 값
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("pageNaviSize", map.get("pageNaviSize"));
		model.addAttribute("pageNaviBegin", map.get("pageNaviBegin"));
		model.addAttribute("pageNaviEnd", map.get("pageNaviEnd"));
		
		return "/admin/dormantAccountListByStudent";
	}
	
	// 관리자가 운영자 휴면계정의 계정 상태를 활성화로 변경하는 메소드
	// 매개변수: accountId(아이디)
	// 리턴값: dormantAccountList 페이지로 이동
	// 계정의 활성화 여부를 활성화로 변경
	// 운영자 휴면계정 목록으로 이동
	@GetMapping(value = "/admin/dormantAccountStateActiveByManager", produces = "application/text; charset=utf8")
	public String dormantAccountStateActiveByManager(
			@RequestParam(value = "accountId") String accountId,
			@RequestParam(value = "currentPage", required = false) int currentPage,
			@RequestParam(value = "searchType", required = false) String searchType,
			@RequestParam(value = "searchKeyword", required = false) String searchKeyword) {
		logger.debug(accountId.toString());
		dormantAccountService.updateDormantAccountState(accountId);
		
		logger.debug("현재 페이지" + currentPage);
		logger.debug("검색조건" + searchType);
		logger.debug("검색어" + searchKeyword);
		
		return "redirect:/admin/dormantAccountListByManager?currentPage=" + currentPage + "&searchType=" + searchType + "&searchKeyword=" + searchKeyword;
	}
	
	// 관리자가 강사 휴면계정의 계정 상태를 활성화로 변경하는 메소드
	// 매개변수: accountId(아이디)
	// 리턴값: dormantAccountList 페이지로 이동
	// 계정의 활성화 여부를 활성화로 변경
	// 강사 휴면계정 목록으로 이동
	@GetMapping(value = "/admin/dormantAccountStateActiveByTeacher", produces = "application/text; charset=utf8")
	public String dormantAccountStateActiveByTeacher(@RequestParam(value = "accountId") String accountId,
			@RequestParam(value = "currentPage", required = false) int currentPage,
			@RequestParam(value = "searchType", required = false) String searchType,
			@RequestParam(value = "searchKeyword", required = false) String searchKeyword) {
		logger.debug(accountId.toString());
		dormantAccountService.updateDormantAccountState(accountId);
		
		return "redirect:/admin/dormantAccountListByTeacher?currentPage=" + currentPage + "&searchType=" + searchType + "&searchKeyword=" + searchKeyword;
	}
	// 관리자가 학생 휴면계정의 계정 상태를 활성화로 변경하는 메소드
	// 매개변수: accountId(아이디)
	// 리턴값: dormantAccountList 페이지로 이동
	// 계정의 활성화 여부를 활성화로 변경
	// 학생 휴면계정 목록으로 이동
	@GetMapping(value = "/admin/dormantAccountStateActiveByStudent", produces = "application/text; charset=utf8")
	public String dormantAccountStateActiveStudent(@RequestParam(value = "accountId") String accountId,
			@RequestParam(value = "currentPage", required = false) int currentPage,
			@RequestParam(value = "searchType", required = false) String searchType,
			@RequestParam(value = "searchKeyword", required = false) String searchKeyword) {
		logger.debug(accountId.toString());
		dormantAccountService.updateDormantAccountState(accountId);
		
		return "redirect:/admin/dormantAccountListByStudent?currentPage=" + currentPage + "&searchType=" + searchType + "&searchKeyword=" + searchKeyword;
	}
}
