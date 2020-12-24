package gd.fintech.lms.manager.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.manager.service.LectureManagerService;
import gd.fintech.lms.manager.vo.Classroom;
import gd.fintech.lms.manager.vo.Lecture;



//운영자의 강좌에 대한 컨트롤러

@Controller
public class LectureManagerController {
	//debugLogger
	private final Logger logger = LoggerFactory.getLogger(LectureManagerController.class);
	
	// 강좌  
	@Autowired private LectureManagerService lectureManagerService;
	
	//
	// 매개변수:
	// 리턴값:
	@GetMapping("/manager/createLecture")
	public String createLecture() {
		return "manager/createLecture";
	}
	
	
	// 강좌 개설 액션
	// 매개변수:
	// 리턴값:
	@PostMapping("/manager/createLecture")
	public String createLecture(Lecture lecture) {
		lectureManagerService.createLecture(lecture);
		logger.debug("lecture"+ lecture );
		return "redirect:/manager/";
	}

	
}
