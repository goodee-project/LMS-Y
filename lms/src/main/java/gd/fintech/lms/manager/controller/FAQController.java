package gd.fintech.lms.manager.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.manager.service.FAQService;
import gd.fintech.lms.manager.vo.FAQ;

// FAQ 관련 컨트롤러

@Controller
public class FAQController {	
	
	@Autowired private FAQService faqService;
		
		
	//
	//
	//
	@GetMapping("/manager/FAQ/FAQList")
	public String FAQList (Model model, @RequestParam(name="currentPage")int currentPage) {
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
	  return "faqList";
	}

}
