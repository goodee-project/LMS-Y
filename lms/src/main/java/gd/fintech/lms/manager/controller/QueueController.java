package gd.fintech.lms.manager.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.manager.service.QueueService;
import gd.fintech.lms.manager.vo.StudentQueue;
import gd.fintech.lms.manager.vo.TeacherQueue;

@Controller
public class QueueController {
	// debug를 하기위한 logger 
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	// 매니저가 관리하는 학생,강사 승인대기 서비스
	@Autowired private QueueService queueService;
	
	// 학생 승인대기 리스트 출력
	// 매개변수 : 
	// Model
	// RequestParam : 
	// currentPage(현재 페이지)
	// studentName(학생 이름)
	// 리턴값 : 학생 승인대기 리스트 
	@GetMapping("/manager/studentQueueList")
	public String studentQueueList(Model model,
			@RequestParam(value="currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value="studentName", required = false) String studentName) {
		Map<String, Object> map = queueService.getStudentQueueListByPage(currentPage, studentName);
		
		logger.debug(map.toString());
		
		model.addAttribute("studentQueueList", map.get("studentQueueList"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("studentName", studentName);
		
		model.addAttribute("navPerPage", map.get("navPerPage"));
		model.addAttribute("navBeginPage", map.get("navBeginPage"));
		model.addAttribute("navLastPage", map.get("navLastPage"));
		
		return "/manager/studentQueueList";
	}
	
	// 학생 승인대기 상세내용 출력
	// 매개변수 : 
	// Model
	// RequestParam : accountId(계정 ID)
	// 리턴값 : 계정에 해당하는 학생의 세부 내용 출력
	@GetMapping("/manager/studentQueueDetail")
	public String StudentQueueDetail(Model model,
			@RequestParam(value="accountId") String accountId) {
		StudentQueue studentQueueDetail = queueService.getStudentQueueDetail(accountId);
		logger.debug(studentQueueDetail.toString());
		model.addAttribute("studentQueueDetail", studentQueueDetail);
		return "/manager/studentQueueDetail";
	}
	
	// 학생 승인
	// 매개변수 : 
	// RequestParam : accountId(계정 ID)
	// session
	// 리턴값 : 계정에 해당되는 학생 승인
	@GetMapping("/manager/approveStudent")
	public String approveStudentMembership(@RequestParam(value="accountId") String accountId,
			HttpSession session) {
		queueService.approveStudentMembership(accountId, session);
		return "redirect:/manager/studentQueueList";
	}
	
	// 학생 승인 거절
	// 매개변수 : 
	// RequestParam : accountId(계정 ID)
	// 리턴값 : 계정에 해당되는 학생 승인 거절
	@GetMapping("/manager/disapproveStudent")
	public String disapproveStudentMembership(@RequestParam(value="accountId") String accountId) {
		queueService.disapproveStudentMembership(accountId);
		return "redirect:/manager/studentQueueList";
	}
	
	// 강사 승인대기 리스트 출력
	// 매개변수 : 
	// Model 
	// RequestParam : 
	// currentPage(현재 페이지)
	// teacherName(강사 이름)
	// 리턴값 : 강사 승인대기 리스트 
	@GetMapping("/manager/teacherQueueList")
	public String teacherQueueList(Model model,
			@RequestParam(value="currentPage", defaultValue = "1") int currentPage,
			@RequestParam(value="teacherName", required = false) String teacherName) {
		Map<String, Object> map = queueService.getTeacherQueueListByPage(currentPage, teacherName);
		
		logger.debug(map.toString());
		
		model.addAttribute("teacherQueueList", map.get("teacherQueueList"));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", map.get("lastPage"));
		model.addAttribute("teacherName", teacherName);
		
		model.addAttribute("navPerPage", map.get("navPerPage"));
		model.addAttribute("navBeginPage", map.get("navBeginPage"));
		model.addAttribute("navLastPage", map.get("navLastPage"));
		
		return "/manager/teacherQueueList";
	}
	
	// 강사 승인대기 상세내용 출력
	// 매개변수 : 
	// Model
	// RequestParam : accountId(계정 ID)
	// 리턴값 : 계정에 해당하는 강사의 세부 내용 출력
	@GetMapping("/manager/teacherQueueDetail")
	public String teacherQueueDetail(Model model,
			@RequestParam(value="accountId") String accountId) {
		TeacherQueue teacherQueueDetail = queueService.getTeacherQueueDetail(accountId);
		logger.debug(teacherQueueDetail.toString());
		model.addAttribute("teacherQueueDetail", teacherQueueDetail);
		return "/manager/teacherQueueDetail";
	}
	
	// 강사 승인
	// 매개변수 : 
	// RequestParam : accountId(계정 ID)
	// 리턴값 : 계정에 해당되는 강사 승인
	@GetMapping("/manager/approveTeacher")
	public String approveTeacherMembership(@RequestParam(value="accountId") String accountId,
			HttpSession session) {
		queueService.approveTeacherMembership(accountId, session);
		return "redirect:/manager/teacherQueueList";
	}
	
	// 강사 승인 거절
	// 매개변수 : 
	// RequestParam : accountId(계정 ID)
	// 리턴값 : 계정에 해당되는 강사 승인 거절
	@GetMapping("/manager/disapproveTeacher")
	public String disapproveTeacherMembership(@RequestParam(value="accountId") String accountId) {
		queueService.disapproveTeacherMembership(accountId);
		return "redirect:/manager/teacherQueueList";
	}
}
