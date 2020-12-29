package gd.fintech.lms.manager.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.AccountLevel;
import gd.fintech.lms.manager.service.LMSNoticeService;
import gd.fintech.lms.manager.vo.LMSNotice;

// lms의 공지사항 관리 컨트롤러

@Controller
public class LMSNoticeController {
	// debug를 하기위한 logger 
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// lms 공지사항 서비스
	@Autowired private LMSNoticeService lmsNoticeService;
	
	// lms 공지사항 리스트 출력
	// 매개변수 :
	// Model
	// RequestParam : 
	// currentPage(현재 페이지)
	// lmsNoticeSearch(공지사항 검색어)
	// 리턴값 : 공지사항 리스트
	@GetMapping(value={
			"/student/lmsNoticeList", 
			"/teacher/lmsNoticeList", 
			"/manager/lmsNoticeList"
			})
	public String lmsNoticeList(Model model,
			@RequestParam(value="currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value="lmsNoticeSearch", required = false) String lmsNoticeSearch,
			HttpSession session) {
		Map<String, Object> map = lmsNoticeService.getLMSNoticeListByPage(currentPage, lmsNoticeSearch);
		
		logger.debug(map.toString());
		logger.debug(session.getAttribute("accountLevel").toString());
		
		model.addAttribute("lmsNoticeList", map.get("lmsNoticeList"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("lmsNoticeSearch", lmsNoticeSearch);
		
		model.addAttribute("navPerPage", map.get("navPerPage"));
		model.addAttribute("navBeginPage", map.get("navBeginPage"));
		model.addAttribute("navLastPage", map.get("navLastPage"));
		
		model.addAttribute("accountLevel", session.getAttribute("accountLevel"));
		model.addAttribute("accountId", session.getAttribute("accountId"));
		model.addAttribute("managerLevel", AccountLevel.MANAGER.getValue());
		return "lmsNoticeList";
	}
	
	// 공지사항 상세보기 페이지 
	// 매개변수 :
	// Model
	// RequestParam : lmsNoticeNo(공지사항 번호)
	// 리턴값 : 공지사항 번호에 해당되는 공지 상세 정보
	@GetMapping("/notice/lmsNoticeDetail")
	public String lmsNoticeDetail (Model model,
			@RequestParam(value="lmsNoticeNo") int lmsNoticeNo,
			HttpSession session) {
		LMSNotice lmsNotice = lmsNoticeService.getLMSNoticeDetail(lmsNoticeNo);
		logger.debug(lmsNotice.toString());
		
		// 조회수 증가
		long updateTime = 0;
		// 세션에 저장된 조회 시간을 검색
		if(session.getAttribute("updateTime"+lmsNoticeNo) != null) {
			updateTime = (long)session.getAttribute("updateTime"+lmsNoticeNo);
		}
		// 시스템 현재시간 
		long currentTime = System.currentTimeMillis();
		if(currentTime - updateTime > 24*60*601000) {
			lmsNoticeService.increaseLMSNoticeCountOfViews(lmsNoticeNo);
			session.setAttribute("updateTime"+lmsNoticeNo, currentTime);
		}
		
		model.addAttribute("lmsNotice", lmsNotice);
		model.addAttribute("accountLevel", session.getAttribute("accountLevel"));
		model.addAttribute("managerLevel", AccountLevel.MANAGER.getValue());
		return "lmsNoticeDetail";
	}
	
	// lms 공지사항 입력 페이지
	// 리턴값 : 공지사항 입력 액션
	@GetMapping("/manager/createLMSNotice")
	public String createLMSNotice() {
		return "/manager/createLMSNotice";
	}
	
	// lms 공지사항 입력 액션
	// 매개변수 : lms 공지사항 정보
	// 리턴값 : 입력한 공지사항 페이지 출력
	@PostMapping("/manager/createLMSNotice")
	public String createLMSNotice(LMSNotice lmsNotice, HttpSession session) {
		logger.debug(lmsNotice.toString());
		lmsNoticeService.createLMSNotice(lmsNotice, session);
		return "redirect:/notice/lmsNoticeDetail?lmsNoticeNo="+lmsNotice.getLmsNoticeNo();
	}
	
	// lms 공지사항 수정 페이지
	// 매개변수 : 
	// Model
	// RequestParam : lmsNoticeNo(lms공지사항 번호)
	// 리턴값 : 공지사항 수정 액션
	@GetMapping("/manager/modifyLMSNotice")
	public String modifyLMSNotice(Model model,
			@RequestParam(value="lmsNoticeNo") int lmsNoticeNo) {
		LMSNotice lmsNotice = lmsNoticeService.getLMSNoticeDetail(lmsNoticeNo);
		logger.debug(lmsNotice.toString());
		model.addAttribute("lmsNotice", lmsNotice);
		return "/manager/modifyLMSNotice";
	}
	
	// lms 공지사항 수정 액션
	// 매개변수 : lms 공지사항 정보
	// 리턴값 : 수정한 공지사항 페이지 출력
	@PostMapping("/manager/modifyLMSNotice")
	public String modifyLMSNotice(LMSNotice lmsNotice) {
		logger.debug(lmsNotice.toString());
		lmsNoticeService.modifyLMSNotice(lmsNotice);
		return "redirect:/notice/lmsNoticeDetail?lmsNoticeNo="+lmsNotice.getLmsNoticeNo();
	}
	
	// lms 공지사항 삭제 
	// 매개변수 : 
	// RequestParam : lmsNoticeNo(lms 공지사항 번호)
	// 리턴값 : 해당 번호의 공지사항 삭제 & 공지사항 리스트 출력  
	@GetMapping("/manager/removeLMSNotice")
	public String removeLMSNotice(@RequestParam(value="lmsNoticeNo") int lmsNoticeNo) {
		lmsNoticeService.removeLMSNotice(lmsNoticeNo);
		return "redirect:/manager/lmsNoticeList?currentPage=1";
	}
}
