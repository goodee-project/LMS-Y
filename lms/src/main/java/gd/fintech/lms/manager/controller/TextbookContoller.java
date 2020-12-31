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

import gd.fintech.lms.manager.service.TextbookService;
import gd.fintech.lms.manager.vo.Textbook;

// 교재 정보 관련 Controller

@Controller
public class TextbookContoller {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 교재 정보 관련 Service
	@Autowired TextbookService textbookService;
	
	// 교재 목록 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. currentPage
	// 리턴값: textbookList(교재 목록 페이지)
	// 교재 목록을 페이징하여 출력
	// 페이지 표시 네비게이션 바 출력
	@GetMapping("/manager/textbookList")
	public String textbookList(Model model,
			@RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
			@RequestParam(value = "searchType", required = false, defaultValue = "all") String searchType,
			@RequestParam(value = "searchKeyword", required = false) String searchKeyword) {
		Map<String, Object> map = textbookService.getTextbookList(currentPage, searchType, searchKeyword);
		
		// 교재 목록
		model.addAttribute("textbookList", map.get("textbookList"));
		
		// 검색 관련 값
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchKeyword", searchKeyword);
		
		// 페이지 관련 값
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("pageNaviSize", map.get("pageNaviSize"));
		model.addAttribute("pageNaviBegin", map.get("pageNaviBegin"));
		model.addAttribute("pageNaviEnd", map.get("pageNaviEnd"));
		
		return "/manager/textbookList";
	}
	
	// 교재 정보 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. textbookISBN(교재 고유번호)
	// 리턴값: textbookDetail(고유번호에 해당하는 교재 정보 페이지)
	@GetMapping("/manager/textbookDetail")
	public String textbookDetail(Model model, @RequestParam(value = "textbookISBN") String textbookISBN) {
		Textbook textbookDetail = textbookService.getTextbookDetail(textbookISBN);
		model.addAttribute("textbookDetail", textbookDetail);
		
		return "/manager/textbookDetail";
	}
	
	// 교재 정보를 입력할 수 있는 페이지를 출력하는 메소드
	// 매개변수: 없음
	// 리턴값: createTextbook(과목 정보 추가 페이지)
	// 추가할 교재 정보를 입력할 수 있는 페이지 출력
	@GetMapping("/manager/createTextbook")
	public String createTextbook() {
		return "/manager/createTextbook";
	}
	
	// 교재 정보를 입력된 값으로 추가하는 메소드
	// 매개변수: textbook(교재 정보)
	// 리턴값: textbookList 페이지로 이동
	// 입력된 정보로 교재 정보를 추가
	// 교재 목록 출력 페이지로 이동
	@PostMapping("/manager/createTextbook")
	public String createTextbook(Textbook textbook) {
		textbookService.createTextbook(textbook);
		logger.debug(textbook.toString());
		
		return "redirect:/manager/textbookList";
	}
	
	// 교재 정보를 수정할 수 있는 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. textbookISBN(교재 고유번호)
	// 리턴값: modifyTextbook(고유번호에 해당하는 교재 정보 수정 페이지)
	// 수정할 교재 정보를 입력할 수 있는 페이지 출력
	// 기존의 정보를 출력
	@GetMapping("/manager/modifyTextbook")
	public String modifyTextbook(Model model, @RequestParam(value = "textbookISBN") String textbookISBN) {
		Textbook modifyTextbook = textbookService.getTextbookDetail(textbookISBN);
		logger.debug(modifyTextbook.toString());
		model.addAttribute("modifyTextbook", modifyTextbook);
		
		return "/manager/modifyTextbook";
	}
	
	// 교재 정보를 입력된 값으로 수정하는 메소드
	// 매개변수: textbook(교재 정보)
	// 리턴값: textbookDetail 페이지로 이동
	// 입력된 정보로 교재 정보를 수정
	// 수정된 교재 정보 페이지로 이동(고유번호에 해당하는 교재 정보 페이지로 이동)
	@PostMapping("/manager/modifyTextbook")
	public String modifyTextbook(Textbook textbook) {
		textbookService.modifyTextbook(textbook);
		logger.debug(textbook.toString());
		
		return "redirect:/manager/textbookDetail?textbookISBN=" + textbook.getTextbookISBN();
	}
}
