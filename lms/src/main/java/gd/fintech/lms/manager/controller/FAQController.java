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
		
	// FAQ 
	// 매개변수: Model @RequestParam: currentPage (현재페이지)
	// 리턴값: FAQ 리스트 페이지 출력
	@GetMapping("/manager/FAQList")
	public String FAQList (Model model, @RequestParam(name="currentPage",defaultValue = "1")int currentPage) {
	  Map<String, Object> map = faqService.getFAQListByPage(currentPage);
	  
	  List<FAQCategory> categoryList = faqCategoryService.getFAQCategoryList();
	  model.addAttribute("categoryList", categoryList);
	  model.addAttribute("currentPage",currentPage);
	  model.addAttribute("faqList",map.get("faqList") );
	  model.addAttribute("lastPage",map.get("lastPage") );
	  model.addAttribute("navBeginPage",map.get("navBeginPage") );
	  model.addAttribute("navLastPage",map.get("navLastPage") );
	  model.addAttribute("navPerPage",map.get("navPerPage") );
	  logger.debug("categoryList",categoryList);
	  return "manager/FAQList";
	}
	
	
	// FAQ 작성 폼
	// 리턴값: FAQ 입력 액션
	@GetMapping("/manager/createFAQ")
	public String createFAQ(Model model) {
	List<FAQCategory> categoryList = faqCategoryService.getFAQCategoryList();
	model.addAttribute("categoryList",categoryList);
	return "manager/createFAQ";
	}
	
	
	// 세션을 가져와서 acountId를 세션에서 가져오고 작성자를 accountID에 해당하는 이름으로 
	// FAQ 작성 액션 
	// 매개변수: FAQ 정보
	// 리턴값:  입력한 FAQ정보를 포함한 FAQList 페이지 출력
	@PostMapping("/manager/createFAQ")
	public String createFAQ(FAQ faq, HttpSession session) {
		faqService.createFAQ(faq,session);
		return "redirect:/manager/FAQList";
	}
	
	// FAQ 수정 폼
	// 리턴값:  
	@GetMapping("/manager/modifyFAQ")
	public String modifyFAQ(Model model, @RequestParam(name="faqNo")int faqNo) {
		FAQ faq = faqService.getFAQDetail(faqNo);
		List<FAQCategory> categoryList = faqCategoryService.getFAQCategoryList();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("faq", faq);
		return "/manager/modifyFAQ";
		
	}
	
	// FAQ 수정 액션
	// 리턴값: 입력한 FAQ
	@PostMapping("/manager/modifyFAQ")
	public String modifyFAQ(FAQ faq) {
		faqService.modifyFAQ(faq);
		return "redirect:/manager/FAQDetail?faqNo="+faq.getFaqNo();
	}
	
	// FAQ 상세정보
	// 리턴값: 입력한 faqNo에 해당하는 FAQ 상세정보 
	@GetMapping("/manager/FAQDetail")
	public String faqDetail(Model model,
			@RequestParam("faqNo")int faqNo) {
		FAQ faq = faqService.getFAQDetail(faqNo);
		model.addAttribute("faq", faq);
		return "manager/FAQDetail";
		
	}	
	
}
