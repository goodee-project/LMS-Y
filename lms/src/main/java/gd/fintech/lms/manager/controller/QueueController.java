package gd.fintech.lms.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.manager.service.QueueService;

@Controller
public class QueueController {
	// 매니저가 관리하는 학생,강사 승인대기 서비스
	@Autowired private QueueService queueService;
	
	// 학생 승인대기 리스트 출력
	// 매개변수 : Model
	// 리턴값 : 학생 승인대기 리스트 
	@GetMapping("/manager/studentQueueList")
	public String studentQueueList(Model model) {
		model.addAttribute("studentQueueList",queueService.getStudentQueueList());
		return "studentQueueList";
	}
	
	// 학생 승인대기 상세내용 출력
	// 매개변수 : 
	// Model
	// RequestParam : accountId(계정 ID)
	// 리턴값 : 계정에 해당하는 학생의 세부 내용 출력
	@GetMapping("/manager/studentQueueDetail")
	public String StudentQueueDetail(Model model,
			@RequestParam(value="accountId") String accountId) {
		model.addAttribute("studentQueueDetail", queueService.getStudentQueueDetail(accountId));
		return "studentQueueDetail";
	}
	
	// 학생 승인
	// 매개변수 : 
	// RequestParam : accountId(계정 ID)
	// 리턴값 : 계정에 해당되는 학생 승인
	@GetMapping("/manager/approveStudent")
	public String approveStudentMembership(@RequestParam(value="accountId") String accountId) {
		queueService.approveStudentMembership(accountId);
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
	// 매개변수 : Model
	// 리턴값 : 강사 승인대기 리스트 
	@GetMapping("/manager/teacherQueueList")
	public String teacherQueueList(Model model) {
		model.addAttribute("teacherQueueList",queueService.getTeacherQueueList());
		return "teacherQueueList";
	}
	
	// 강사 승인대기 상세내용 출력
	// 매개변수 : 
	// Model
	// RequestParam : accountId(계정 ID)
	// 리턴값 : 계정에 해당하는 강사의 세부 내용 출력
	@GetMapping("/manager/teacherQueueDetail")
	public String teacherQueueDetail(Model model,
			@RequestParam(value="accountId") String accountId) {
		model.addAttribute("teacherQueueDetail", queueService.getTeacherQueueDetail(accountId));
		return "teacherQueueDetail";
	}
	
	// 강사 승인
	// 매개변수 : 
	// RequestParam : accountId(계정 ID)
	// 리턴값 : 계정에 해당되는 강사 승인
	@GetMapping("/manager/approveTeacher")
	public String approveTeacherMembership(@RequestParam(value="accountId") String accountId) {
		queueService.approveTeacherMembership(accountId);
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
