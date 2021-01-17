package gd.fintech.lms.student.controller;

// 학생의 객관식 시험문제에 대한 컨트롤러

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.student.service.StudentMultipleChoiceService;

@Controller
public class StudentMultipleChoiceController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired StudentMultipleChoiceService studentMultipleChoiceService;
	
	//강좌별 객관식 문제 리스트 페이징
	//매개변수:lectureNo,currentPage
	//리턴값:학생의 객관식문제를 보여주는 페이지
	@GetMapping("student/studentMultipleChoiceList")
	public String getStudentMultipleListByPage(Model model,
			@RequestParam(value="lectrueNo",required = false)int lectureNo,
			@RequestParam(value="currentPage",defaultValue = "1")int currentPage) {
		Map<String,Object> map = studentMultipleChoiceService.getStudentMultipleListByPage(lectureNo, currentPage);
		
		model.addAttribute("multipleChoice",map.get("multipleChoice"));
		logger.debug(map.get("multipleChoice")+"=객관식 문제리스트 ");
		
		model.addAttribute("navPerPage",map.get("navPerPage"));
		model.addAttribute("navBeginPage", map.get("navBeginPage"));
		model.addAttribute("navLastPage", map.get("navLastPage"));
		
		model.addAttribute("lastPage",map.get("lastPage"));
		logger.debug(map.get("lastPage")+"=라스트페이지수");
		
		model.addAttribute("lecturNo",lectureNo);
		logger.debug(map.get("lecturNo")+"=강좌 번호 ");
		
		model.addAttribute("multipleChoiceCount",map.get("multipleChoiceCount"));
		model.addAttribute("currentPage",currentPage);
		
		return "student/studentMultipleChoiceList";
	}
}
