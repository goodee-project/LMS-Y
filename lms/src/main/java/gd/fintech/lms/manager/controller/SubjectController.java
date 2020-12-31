package gd.fintech.lms.manager.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.manager.service.SubjectService;
import gd.fintech.lms.manager.vo.Subject;

// 과목 정보 관련 Controller

@Controller
public class SubjectController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 과목 정보 관련 Service
	@Autowired private SubjectService subjectService;
	
	// 과목 목록 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. currentPage(현재 페이지)
	// 리턴값: subjectList(과목 목록 페이지)
	// 과목 목록을 페이징하여 출력
	// 페이지 표시 네비게이션 바 출력
	@GetMapping("/manager/subjectList")
	public String subjectList(Model model,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "searchType", required = false, defaultValue = "all") String searchType,
			@RequestParam(value = "searchKeyword", required = false) String searchKeyword) {
		Map<String, Object> map = subjectService.getSubjectList(currentPage, searchType, searchKeyword);
		
		// 과목 목록
		model.addAttribute("subjectList", map.get("subjectList"));
		
		// 검색 관련 값
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchKeyword", searchKeyword);
		
		// 페이지 관련 값
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("pageNaviSize", map.get("pageNaviSize"));
		model.addAttribute("pageNaviBegin", map.get("pageNaviBegin"));
		model.addAttribute("pageNaviEnd", map.get("pageNaviEnd"));
		
		return "/manager/subjectList";
	}
	
	
	// 과목 정보 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. subjectNo(과목 고유번호)
	// 리턴값: subjectDetail(고유번호에 해당하는 과목 정보 페이지)
	@GetMapping("/manager/subjectDetail")
	public String subjectDetail(Model model, @RequestParam(value = "subjectNo") int subjectNo) {
		Subject subjectDetail = subjectService.getSubjectDetail(subjectNo);
		logger.debug(subjectDetail.toString());
		model.addAttribute("subjectDetail", subjectDetail);
		
		return "/manager/subjectDetail";
	}
	
	// 과목 정보를 입력할 수 있는 페이지를 출력하는 메소드
	// 매개변수: 없음
	// 리턴값: createSubject(과목 정보 추가 페이지)
	// 추가할 과목 정보를 입력할 수 있는 페이지 출력
	@GetMapping("/manager/createSubject")
	public String createSubject() {
		return "/manager/createSubject";
	}
	
	// 과목 정보를 입력된 값으로 추가하는 메소드
	// 매개변수: subject(과목 정보)
	// 리턴값: subjectList 페이지로 이동
	// 입력된 정보로 과목 정보를 추가
	// 과목 목록 출력 페이지로 이동
	@PostMapping("/manager/createSubject")
	public String createSubject(Subject subject) {
		subjectService.createSubject(subject);
		logger.debug(subject.toString());
		
		return "redirect:/manager/subjectList";
	}
	
	// 과목 정보를 수정할 수 있는 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. subjectNo(과목 고유번호)
	// 리턴값: modiftSubject(고유번호에 해당하는 과목 정보 수정 페이지)
	// 수정할 과목 정보를 입력할 수 있는 페이지 출력
	// 기존의 정보를 출력
	@GetMapping("/manager/modifySubject")
	public String modifySubject(Model model, @RequestParam(value = "subjectNo") int subjectNo) {
		Subject modifySubject = subjectService.getSubjectDetail(subjectNo);
		logger.debug(modifySubject.toString());
		model.addAttribute("modifySubject", modifySubject);
		
		return "/manager/modifySubject";
	}
	
	// 과목 정보를 입력된 값으로 수정하는 메소드
	// 매개변수: subject(과목 정보)
	// 리턴값: subjectDetail 페이지로 이동
	// 입력된 정보로 과목 정보를 수정
	// 수정된 과목 정보 페이지로 이동(고유번호에 해당하는 과목 정보 페이지로 이동)
	@PostMapping("/manager/modifySubject")
	public String modifySubject(Subject subject) {
		subjectService.modifySubject(subject);
		logger.debug(subject.toString());
		
		return "redirect:/manager/subjectDetail?subjectNo=" + subject.getSubjectNo();
	}
}
