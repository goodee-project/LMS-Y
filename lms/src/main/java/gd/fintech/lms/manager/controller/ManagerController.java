package gd.fintech.lms.manager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.manager.service.ManagerService;
import gd.fintech.lms.manager.vo.Manager;

// 운영자 관련 컨트롤러 

@Controller
public class ManagerController {
	@Autowired private  ManagerService managerService;
	
	
	// 운영자 상세정보
 	// 리턴값: accountId에 해당하는 운영자 상세정보
	@GetMapping("/manager/managerDetail")
	public String managerInfo(Model model,
			@RequestParam (value="accountId")String accountId) {
	    Manager manager = managerService.getManagerDetail(accountId);
	    model.addAttribute("manager", manager);
		return "/manager/managerDetail";
	}
	
	
	
	
}
