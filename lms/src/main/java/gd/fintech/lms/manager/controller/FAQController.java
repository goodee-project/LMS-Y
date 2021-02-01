package gd.fintech.lms.manager.controller;

import java.util.List;
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
import gd.fintech.lms.manager.service.FAQCategoryService;
import gd.fintech.lms.manager.service.FAQService;
import gd.fintech.lms.manager.vo.FAQ;
import gd.fintech.lms.manager.vo.FAQCategory;

// FAQ 관련 컨트롤러

@Controller
public class FAQController {	
	//debugLogger
	private final Logger logger = LoggerFactory.getLogger(FAQController.class);
	
	@Autowired private FAQService faqService;
	@Autowired private FAQCategoryService faqCategoryService;	
		
	// FAQ 리스트
	// 매개변수: Model @RequestParam: currentPage (현재페이지) @RequestParam: categoryFaqSearch(FAQ 카테고리 검색)
	// 리턴값: FAQ 리스트 페이지 출력
	@GetMapping(value = {"/faq/FAQList", "/manager/FAQList", "/teacher/FAQList", "/student/FAQList"})
	public String FAQList(Model model,
			@RequestParam(value="currentPage",defaultValue = "1")int currentPage,
			@RequestParam(value="categoryFaqSearch", required = false) String categoryFaqSearch,
			HttpSession session) {
	  Map<String, Object> map = faqService.getFAQListByPage(currentPage,categoryFaqSearch);
	  logger.debug(map.toString());
	  List<FAQCategory> categoryList = faqCategoryService.getFAQCategoryList();
	  model.addAttribute("categoryFaqSearch", categoryFaqSearch);
	  model.addAttribute("categoryList", categoryList);
	  model.addAttribute("currentPage",currentPage);
	  model.addAttribute("faqList",map.get("faqList"));
	  model.addAttribute("lastPage",map.get("lastPage"));
	  model.addAttribute("navBeginPage",map.get("navBeginPage"));
	  model.addAttribute("navLastPage",map.get("navLastPage"));
	  model.addAttribute("navPerPage",map.get("navPerPage"));
	  model.addAttribute("managerLevel", AccountLevel.MANAGER.getValue());
	  logger.debug("categoryList",categoryList);
	  logger.debug("categoryFaqSearch",categoryFaqSearch);
	  return "manager/FAQList";
	}

	// FAQ 작성 폼
	// 매개변수 model
	// 리턴값: FAQ 입력 액션
	@GetMapping("/manager/createFAQ")
	public String createFAQ(Model model) {
	List<FAQCategory> categoryList = faqCategoryService.getFAQCategoryList();
	model.addAttribute("categoryList",categoryList);
	return "manager/createFAQ";
	}
	
	// FAQ 작성 액션 
	// 매개변수: FAQ 정보, session
	// 리턴값: 입력한 FAQ정보를 포함한 FAQList 페이지 출력
	@PostMapping("/manager/createFAQ")
	public String createFAQ(FAQ faq, HttpSession session) {
		faqService.createFAQ(faq,session);
		return "redirect:/manager/FAQList";
	}
	
	// FAQ 수정 폼
	// 매개변수: model,  @RequestParam: faqNo(FAQ고유번호)
	// 리턴값: faqNo에 해당하는 FAQ을 수정하는 페이지 
	@GetMapping("/manager/modifyFAQ")
	public String modifyFAQ(Model model, @RequestParam(name="faqNo")int faqNo) {
		FAQ faq = faqService.getFAQDetail(faqNo);
		List<FAQCategory> categoryList = faqCategoryService.getFAQCategoryList();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("faq", faq);
		return "/manager/modifyFAQ";
		
	}
	
	// FAQ 수정 액션
	// 매개변수: FAQ 정보
	// 리턴값: faqNo에 해당하는 FAQ 상세보기 페이지
	@PostMapping("/manager/modifyFAQ")
	public String modifyFAQ(FAQ faq) {
		faqService.modifyFAQ(faq);
		return "redirect:/FAQDetail?faqNo="+faq.getFaqNo();
	}
	
	// FAQ 상세정보
	// 매개변수: model, @RequestParam: faqNo(FAQ고유번호)
	// 리턴값: 입력한 faqNo에 해당하는 FAQ 상세정보 
	@GetMapping("/FAQDetail")
	public String faqDetail(Model model,
			@RequestParam("faqNo")int faqNo,
			HttpSession session) {
		FAQ faq = faqService.getFAQDetail(faqNo);
		
		// 조회수 증가
		long updateTime =0;
		// 세션에 저장된 조회 시간을 검색 
		if(session.getAttribute("updateTime"+faqNo) !=null) {
			updateTime = (long)session.getAttribute("updateTime"+faqNo);
		}
		// 시스템 현재시간
		long currentTime = System.currentTimeMillis(); 
		if(currentTime - updateTime > 24*60*60*1000) {
			faqService.increaseFAQCountUp(faqNo);
			session.setAttribute("updateTime"+faqNo, currentTime);			
		}
		model.addAttribute("faq", faq);
		model.addAttribute("managerLevel", AccountLevel.MANAGER.getValue());
		return "/manager/FAQDetail";
		
	}	
	
	// FAQ 삭제
	// 매개변수: @RequestParam: faqNo(FAQ고유번호)
	// 리턴값: faqNo에 해당하는 FAQ 삭제 
	@GetMapping("/manager/removeFAQ")
	public String removeFAQ(
			@RequestParam("faqNo")int faqNo ) {
		faqService.removeFAQ(faqNo);
		return "redirect:/manager/FAQList";
	}
	
}
