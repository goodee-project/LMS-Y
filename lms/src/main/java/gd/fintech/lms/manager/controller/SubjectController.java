package gd.fintech.lms.manager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.manager.service.ManagerService;
import gd.fintech.lms.manager.vo.Subject;

// 과목 정보 관련 Controller

@Controller
public class SubjectController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 
	@Autowired private ManagerService managerService;	
	
	// 과목 정보 페이지를 출력하는 메소드
	// 매개변수:
	// #1. model
	// #2. subjectNo(과목 고유번호)
	// 리턴값: subjectDetail(과목 정보 페이지)
	public String subjectDetail(Model model, @RequestParam(value = "subjectNo") int subjectNo) {
		// Subject subjectDetail = managerService.subjectDetail(subjectNo);
		return "subjectDetail";
	}
}
