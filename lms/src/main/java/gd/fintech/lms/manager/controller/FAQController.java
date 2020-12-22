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

import gd.fintech.lms.manager.service.FAQService;
import gd.fintech.lms.manager.vo.FAQ;

// FAQ 관련 컨트롤러

@Controller
public class FAQController {	
	//debugLogger
	private final Logger logger = LoggerFactory.getLogger(FAQController.class);
	
	@Autowired private FAQService faqService;
		
		
	// FAQ 
	// 매개변수: Model @RequestParam: currentPage (현재페이지)
	// 리턴값: FAQ 리스트 페이지 출력
	@GetMapping("/manager/FAQList")
	public String FAQList (Model model, @RequestParam(name="currentPage",defaultValue = "1")int currentPage) {
	int rowPerPage= 10;
	int Count = faqService.getFAQCount();
	int lastPage = 0;
	if(Count % rowPerPage ==0) {
		lastPage = Count / rowPerPage;
				
	   } else if (Count % rowPerPage !=0){
		lastPage = Count / rowPerPage +1;
				
	   }
	  List<FAQ> faqList = faqService.getFAQListByPage(currentPage, rowPerPage);
	  model.addAttribute("lastPage", lastPage);
	  model.addAttribute("currentPage", currentPage);
	  model.addAttribute("faqList",faqList);
	  logger.debug("lastPage"+ lastPage );
	  return "FAQList";
	}
	
	
	// FAQ 작성 폼
	// 리턴값: FAQ 입력 액션
	@GetMapping("/manager/createFAQ")
	public String createFAQ() {
	return "createFAQ";
	}
	
	
	// FAQ 작성 액션 
	// 매개변수: FAQ 정보
	// 리턴값:  입력한 FAQ정보를 포함한 FAQList 페이지 출력
	@PostMapping("/manager/createFAQ")
	public String createFAQ(FAQ faq) {
		faqService.createFAQ(faq);
		return "redirect:/manager/FAQList";
	}
	
	
	

}