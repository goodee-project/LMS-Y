package gd.fintech.lms.manager.controller;

import java.util.List;

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
	public String subjectList(Model model, @RequestParam(value = "currentPage") int currentPage) {
		// 한 페이지에 보여줄 항목수 15개
		int rowPerPage = 15;
		// 총 항목수
		int totalCount = subjectService.getSubjectCount();
		// 마지막 페이지
		int lastPage = totalCount / rowPerPage;
		// 페이지 네비게이션 바에 표시할 페이지 수
		int navPerPage = 10;
		// 페이지 네비게이션 바의 첫번째 페이지
		int navFirstPage = currentPage - (currentPage % navPerPage) + 1;
		// 페이지 네비게이션 바의 마지막 페이지
		int navLastPage = (navFirstPage + navPerPage) - 1;
		
		List<Subject> subjectList = subjectService.getSubjectList(currentPage, rowPerPage);
		logger.debug(subjectList.toString());
		
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
		
		model.addAttribute("subjectList", subjectList);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("navPerPage", navPerPage);
		model.addAttribute("navFirstPage", navFirstPage);
		model.addAttribute("navLastPage", navLastPage);
		
		return "subjectList";
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
		
		return "subjectDetail";
	}
	
	// 과목 정보를 입력할 수 있는 페이지를 출력하는 메소드
	// 매개변수: 없음
	// 리턴값: createSubject(과목 정보 추가 페이지)
	// 추가할 과목 정보를 입력할 수 있는 페이지 출력
	@GetMapping("/manager/createSubject")
	public String createSubject() {
		return "createSubject";
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
		
		return "modiftSubject";
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
