package gd.fintech.lms.manager.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.manager.service.ManagerService;
import gd.fintech.lms.manager.vo.Manager;

// 운영자 관련 컨트롤러 

@Controller
public class ManagerController {
	
	private final Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	@Autowired private  ManagerService managerService;
	
	
	// 운영자 상세정보
 	// 리턴값: accountId에 해당하는 운영자 상세정보
	@GetMapping("/manager/managerDetail")
	public String managerInfo(Model model,
			@RequestParam (value="accountId")String accountId) {
	    Manager manager = managerService.getManagerDetail(accountId);
	    model.addAttribute("manager", manager);
	    logger.debug("manager"+ manager );
		return "/manager/managerDetail";
	}

	// 운영자 정보 수정 폼
	// 리턴값: 운영자 정보 수정 액션
	@GetMapping("manager/managerModify")
	public String managerModify(Model model,
			@RequestParam(value="accountId")String accountId) {
		Manager manager = managerService.getManagerDetail(accountId); 
		model.addAttribute("manager",manager);
		logger.debug("manager"+ manager );
		return "/manager/managerModify";
	}
	
	// 운영자 정보수정 액션
	// 리턴값: 수정된 운영자 정보
	@PostMapping("manager/managerModify")
	public String managerModify(Manager manager) {
		managerService.modifyManager(manager);
		logger.debug("manager"+ manager );
		return "/manager/managerDetail?accountId="+manager.getAccountId();
	}
	
}
