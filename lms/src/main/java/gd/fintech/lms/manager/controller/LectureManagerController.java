package gd.fintech.lms.manager.controller;

import java.util.List;

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
	
	@Autowired private LectureManagerService lectureManagerService;
	
	// 
	//
	//
	@GetMapping("/manager/lectureList")
	public String lectureList (Model model, @RequestParam(name="currentPage",defaultValue = "1")int currentPage) {
	int rowPerPage= 10;
	int Count = lectureManagerService.getLectureCount();
	int lastPage = 0;
	if(Count % rowPerPage ==0) {
		lastPage = Count / rowPerPage;
				
	   } else if (Count % rowPerPage !=0){
		lastPage = Count / rowPerPage +1;
				
	   }
	  List<Lecture> lectureList = lectureManagerService.getLectureListByPage(currentPage, rowPerPage);
	  model.addAttribute("lastPage", lastPage);
	  model.addAttribute("currentPage", currentPage);
	  model.addAttribute("lectureList",lectureList);
	  logger.debug("lastPage"+ lastPage );
	  return "manager/lectureList";
	}
	
	
}
