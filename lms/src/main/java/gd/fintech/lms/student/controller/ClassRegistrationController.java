package gd.fintech.lms.student.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ch.qos.logback.classic.Logger;
import gd.fintech.lms.manager.service.SubjectService;
import gd.fintech.lms.manager.vo.Subject;
import gd.fintech.lms.student.service.ClassRegistrationCancelService;
import gd.fintech.lms.student.service.ClassRegistrationService;
import gd.fintech.lms.student.vo.ClassRegistration;
import gd.fintech.lms.student.vo.ClassRegistrationCancel;

//학생의 수강 신청및 쉬소에 대한 컨트롤러

@Controller
public class ClassRegistrationController {
	@Autowired ClassRegistrationService classRegistrationService;
	@Autowired SubjectService subjectService;
	@Autowired ClassRegistrationCancelService classRegistrationCancelService;
	
	//학생의 수강신청 목록 리스트(페이징)
	//매개변수:
		//리턴값:
	@GetMapping("/student/classRegistration")
	public String getClassRegistrationListByPage(Model model,
			HttpSession session,
			@RequestParam(value="currentPage",defaultValue="1")int currentPage) {

		Map<String,Object> map=classRegistrationService.getClassRegistrationListByPage(currentPage, session);
		model.addAttribute("classRegistrationList",map.get("classRegistrationList"));
		model.addAttribute("navPerPage",map.get("navPerPage"));
		model.addAttribute("navBeginPage", map.get("navBeginPage"));
		model.addAttribute("navLastPage", map.get("navLastPage"));
		model.addAttribute("lastPage",map.get("lastPage"));
		
		model.addAttribute("accountId", session.getAttribute("accountId"));
		model.addAttribute("currentPage", currentPage);
		
		return "/student/classRegistration";
	}
	
	//수강신청 할 수 있는 모든 수강 리스트
	//매개변수:
		//리턴값:
	@GetMapping("/student/availableLectureList")
	public String availableLectureList(Model model,HttpSession session,

			@RequestParam(value="currentPage",defaultValue="1") int currentPage) {
		
		Map<String, Object> map = classRegistrationService.getAvailableLectureList(currentPage,session);
		
		model.addAttribute("availableLectureListMap",map.get("availableLectureListMap"));
		model.addAttribute("navPerPage",map.get("navPerPage"));
		model.addAttribute("navBeginPage", map.get("navBeginPage"));
		model.addAttribute("navLastPage", map.get("navLastPage"));
		
		model.addAttribute("lastPage",map.get("lastPage"));
		model.addAttribute("currentPage",currentPage);
		return "/student/availableLectureList";
	} 
	
	
	
	//학생이 신청한 수강 과목 정보보기(상세보기)
	//매개변수:
		//리턴값:
	@GetMapping("/student/classRegistrationDetail")
	public String getClassRegistrtaionMyOne(Model model,HttpSession session,
			@RequestParam(value="lectureNo",required = false)int lectureNo) {
		
		
		String accountId =(String)session.getAttribute("accountId");
		System.out.println(accountId+"계정Id");
		
		Map<String,Object> classRegistrationMap = classRegistrationService.getClassRegistrationLectureDetail(lectureNo,session);
		model.addAttribute("classRegistration",classRegistrationMap.get("classRegistration"));
		model.addAttribute("accountId",accountId);
		model.addAttribute("isRegisterable",classRegistrationMap.get("isRegisterable"));
		
		return "/student/classRegistrationDetail";
	}
	//학생 수강신청 사유 입력폼
	@GetMapping("/student/classRegistrationCancel")
	public String addCancel(Model model,HttpServletRequest request,
		@RequestParam(value="classRegistrationNo")int classRegistrationNo,
		@RequestParam(value="lectureName")String lectureName,
		@RequestParam(value="lectureNo")int lectureNo) {
		
		model.addAttribute("classRegistrationNo",classRegistrationNo);
		model.addAttribute("lectureName",lectureName);
		model.addAttribute("lectureNo",lectureNo);
		
			return "/student/classRegistrationCancel";
	}
	
	
	//학생 수강신청 취소
	@PostMapping("/student/classRegistrationCancel")
	public String classRegistrationCancel(
			@RequestParam(value="classRegistrationNo")int classRegistrationNo,
			@RequestParam(value="cancelContent")String cancelContent) {
		//수강상태 변경( 취소)
		classRegistrationCancelService.classRegistrationModify(classRegistrationNo);
		//취소 사유
		classRegistrationCancelService.addCancel(cancelContent, classRegistrationNo);
		return "redirect:/student/classRegistration"; 
	}
	
	//학생 수강신청
	@GetMapping("/student/classRegistrationChoose")
	public String classRegistartionChoose(HttpSession session,Model model,
			@RequestParam(value="lectureNo")int lectureNo) {
		model.addAttribute("lectureNo",lectureNo);
		//수강 신청
		classRegistrationService.insertRegistration(lectureNo,session);
		return "redirect:/student/classRegistration";
	}
	
}
