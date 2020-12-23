package gd.fintech.lms.manager.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.manager.service.LectureManagerService;
import gd.fintech.lms.manager.vo.Lecture;



//운영자의 강좌에 대한 컨트롤러

@Controller
public class LectureManagerController {
	//debugLogger
	private final Logger logger = LoggerFactory.getLogger(LectureManagerController.class);
	
	// 강좌  
	@Autowired private LectureManagerService lectureManagerService;
	
	
	
	@GetMapping("/manager/lectureDetail")
	public String managerLectureDetail(Model model,
			@RequestParam (value ="lectureNo")int lectureNo ) {
		Lecture lecture = lectureManagerService.managerLectureDetail(lectureNo);
		model.addAttribute("lecture", lecture);
		return "/manager/lectureDetail";
	}
	

	
}
