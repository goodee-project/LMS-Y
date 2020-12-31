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

import gd.fintech.lms.student.service.ClassRegistrationCancelService;
import gd.fintech.lms.student.service.ClassRegistrationService;
import gd.fintech.lms.student.vo.ClassRegistration;
import gd.fintech.lms.student.vo.ClassRegistrationCancel;

@Controller
public class ClassRegistrationController {
	
	@Autowired ClassRegistrationService classRegistrationService;
	@Autowired ClassRegistrationCancelService classRegistrationCancelService;
	
	//학생의 수강신청 목록 리스트(페이징)
	@GetMapping("student/classRegistration")
	public String getClassRegistrationListByPage(Model model,
			HttpServletRequest request,
			@RequestParam(value="currentPage",defaultValue="1")int currentPage) {
		
		HttpSession session = ((HttpServletRequest)request).getSession();
		//Id 가지고오기
		String accountId =(String)session.getAttribute("accountId");
		
		List<ClassRegistration>registrationList = classRegistrationService.getClassRegistrationListByPage(accountId, currentPage);
		model.addAttribute("registrationList",registrationList);
		model.addAttribute("accountId",accountId);
		model.addAttribute("currentPage",currentPage);
		
		return "student/classRegistration";
	}
	
	//수강신청 할 수 있는 모든 수강 리스트
	@GetMapping("student/classRegistationAdd")
	public String getClassRegistrationAllList(Model model,
			@RequestParam(value="lectureNo")int lectureNo,
			@RequestParam(value="currentPage",defaultValue="1")int currentPage) {
		List<ClassRegistration>registrationList = classRegistrationService.getClassRegistrationAllListByPage(lectureNo, currentPage);
		
		model.addAttribute("registrationList",registrationList);
		model.addAttribute("lectureNo",lectureNo);
		model.addAttribute("currentPage",currentPage);
		return "student/classRegistrationAdd";
	}
	
	//학생이 수강신청한 과목 정보보기(상세보기)
	@GetMapping("student/classRegistrationDetail")
	public String getClassRegistrtaionOne(Model model,HttpServletRequest request) {
		//세션 가져오기
		HttpSession session = ((HttpServletRequest)request).getSession();
		//No 값 가져오기 
		int subjectNo =(int)session.getAttribute("subjectNo");
		ClassRegistration selectClassRegistrationDetail = classRegistrationService.getClassRegistrtaionOne(subjectNo);
		model.addAttribute("selectClassRegistrationDetail",selectClassRegistrationDetail);
		return "student/classRegistrationDetail";
	}
	
	//학생 수강신청 취소
	@GetMapping("student/classRegistrationCancel")
	public String removeClassRegistration(
			@RequestParam(value="classRegistrationNo")int classRegistrationNo) {
		classRegistrationCancelService.removeClassRegistration(classRegistrationNo);
		return "수강취소 사유 입력폼으로 이동";
	}
	
	//학생 수강신청 사유 입력폼
	@GetMapping("student/classRegistrationCanelReasonForm")
	public String addCancel() {
			return "student/classRegistrationCanelReasonForm";
	}
	
	//학생 수강신청 사유 입력 액션
	@PostMapping("student/classRegistrationCanelReasonForm")
	public String addCancel(
			@RequestParam(value="content")String content,
			@RequestParam(value="classRegistrationNo")int classRegistrationNo) {
		classRegistrationCancelService.addCancel(content, classRegistrationNo);
		return "redirect:/student/classRegistrationCanelReasonForm";
	}
}
