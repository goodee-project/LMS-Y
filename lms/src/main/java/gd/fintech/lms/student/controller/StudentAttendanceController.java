package gd.fintech.lms.student.controller;



import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.student.service.StudentAttendanceService;
;

//학생이 보는 출석

@Controller
public class StudentAttendanceController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired StudentAttendanceService studentAttendanceService;
	
	//학생 계정별로 보는 출석 리스트
	//매개변수:학생id
	//리턴값:계정에 대한 출석
	@GetMapping("student/studentAttendanceList")
	public String studentAttendanceList(Model model,
			@RequestParam(value="accountId",required = false)String accountId,
			@RequestParam(value="currentPage",defaultValue = "1")int currentPage) {
		Map<String,Object> map= studentAttendanceService.getStudentAttendanceListByPage(accountId, currentPage);
		
		model.addAttribute("attendanceList",map.get("attendanceList"));
		model.addAttribute("navPerPage",map.get("navPerPage"));
		model.addAttribute("navBeginPage", map.get("navBeginPage"));
		model.addAttribute("navLastPage", map.get("navLastPage"));
		
		model.addAttribute("lastPage",map.get("lastPage"));
		model.addAttribute("accountId",accountId);
		model.addAttribute("attendanceCount",map.get("attendanceCount"));
		model.addAttribute("currentPage",currentPage);
		return "student/studentAttendanceList";
	}

}
