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

@Controller
public class ClassRegistrationController {
	@Autowired ClassRegistrationService classRegistrationService;
	@Autowired SubjectService subjectService;
	@Autowired ClassRegistrationCancelService classRegistrationCancelService;
	
	//학생의 수강신청 목록 리스트(페이징)
	@GetMapping("/student/classRegistration")
	public String getClassRegistrationListByPage(Model model,
			HttpServletRequest request,
			@RequestParam(value="currentPage",defaultValue="1")int currentPage) {
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		//Id 가지고오기
		String accountId =(String)session.getAttribute("accountId");
		System.out.println(accountId+"계정Id");
		
		Map<String,Object> map=classRegistrationService.getClassRegistrationListByPage(accountId, currentPage);
		model.addAttribute("classRegistrationList",map.get("classRegistrationList"));
		model.addAttribute("navPerPage",map.get("navPerPage"));
		model.addAttribute("navBeginPage", map.get("navBeginPage"));
		model.addAttribute("navLastPage", map.get("navLastPage"));
		model.addAttribute("lastPage",map.get("lastPage"));
		
		model.addAttribute("classRegistrationCount",map.get("classRegistrationCount"));
		model.addAttribute("accountId",accountId);
		model.addAttribute("currentPage",currentPage);
		
		return "/student/classRegistration";
	}
	
	//수강신청 할 수 있는 모든 수강 리스트
	@GetMapping("/student/availableLectureList")
	public String getClassRegistrationAllList(Model model,
			@RequestParam(value="currentPage",defaultValue="1") int currentPage) {
		
		Map<String, Object> map = classRegistrationService.getAvailableLectureList(currentPage);
		
		model.addAttribute("availableLectureList",map.get("availableLectureList"));
		model.addAttribute("navPerPage",map.get("navPerPage"));
		model.addAttribute("navBeginPage", map.get("navBeginPage"));
		model.addAttribute("navLastPage", map.get("navLastPage"));
		
		model.addAttribute("lastPage",map.get("lastPage"));
		model.addAttribute("currentPage",currentPage);
		return "/student/availableLectureList";
	} 
	
	//학생이 수강신청할 과목 정보보기(상세보기)
	@GetMapping("/student/classRegistrationDetail")
	public String getClassRegistrtaionOne(Model model,HttpServletRequest request,
			@RequestParam(value="lectureNo",required = false)int lectureNo) {
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		//Id 가지고오기
		String accountId =(String)session.getAttribute("accountId");
		System.out.println(accountId+"계정Id");
		//학생이 수강신청 한 과목인지 아닌지의 여부를 int값으로 나타냄
		int classRegistrationNoCount= classRegistrationService.getRegistrationNoCount(lectureNo, accountId);
		
		ClassRegistration classRegistration = classRegistrationService.getClassRegistrationLectureDetail(lectureNo);
		model.addAttribute("classRegistration",classRegistration);
		model.addAttribute("classRegistrationNoCount",classRegistrationNoCount);
		model.addAttribute("accountId",accountId);
		return "/student/classRegistrationDetail";
	}
	
	//학생이 신청한 수강 과목 정보보기(상세보기)
	@GetMapping("/student/classRegistrationMyDetail")
	public String getClassRegistrtaionMyOne(Model model,HttpServletRequest request,
			@RequestParam(value="lectureNo",required = false)int lectureNo) {
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		//Id 가지고오기
		String accountId =(String)session.getAttribute("accountId");
		System.out.println(accountId+"계정Id");
		
		ClassRegistration classRegistration = classRegistrationService.getClassRegistrationLectureDetail(lectureNo);
		model.addAttribute("classRegistration",classRegistration);
		model.addAttribute("accountId",accountId);
		return "/student/classRegistrationMyDetail";
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
	public String classRegistartionChoose(
			@RequestParam(value="accountId")String accountId,
			@RequestParam(value="lectureNo")int lectureNo) {
		classRegistrationService.insertRegistration(lectureNo, accountId);
		return "redirect:/student/classRegistration";
	}
	
}
