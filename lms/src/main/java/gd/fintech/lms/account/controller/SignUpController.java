package gd.fintech.lms.account.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import gd.fintech.lms.account.service.SignUpService;
import gd.fintech.lms.dto.SignUpForm;


// 회원가입 처리를 위한 컨트롤러 클래스(페이지 이동)

@Controller
public class SignUpController {
	// SignUpService 객체 주입
	@Autowired private SignUpService signUpService;
	
	// 학생 회원가입 페이지로 이동하는 메소드
	// 리턴값: 학생 회원가입 뷰
	@GetMapping("/signUpStudent")
	public String signUpStudent() {
		return "account/signUpStudent";
	}
	
	// 학생 회원가입시 입력정보를 계정,학생승인대기 테이블에 입력하는 메소드
	// 매개변수: 회원가입폼에서 입력된 값
	// 리턴값: 학생로그인페이지	
	@PostMapping("/signUpStudent")
	public String signUpStudentAction(SignUpForm signUpForm) {
		// account(계정) 테이블에 입력되는 메소드
		signUpService.createSignUpAccount(signUpForm);
		// student_queue(학생승인대기) 테이블에 입력되는 메소드
		signUpService.createSignUpStudentQueue(signUpForm);	
		return "redirect:/studentLogin";
	}
	
	// 강사 회원가입 페이지로 이동하는 메소드
	// 리턴값: 강사 회원가입 뷰
	@GetMapping("/signUpTeacher")
	public String signUpTeacher() {
		return "account/signUpTeacher";
	}
	
	// 강사 회원가입시 입력정보를 계정,강사승인대기 테이블에 입력하는 메소드
	// 매개변수: 회원가입폼에서 입력된 값
	// 리턴값: 강사로그인페이지
	@PostMapping("/signUpTeacher")
	public String signUpTeacherAction(SignUpForm signUpForm) {
		// account(계정) 테이블에 입력되는 메소드
		signUpService.createSignUpAccount(signUpForm);
		// teacher_queue(강사승인대기) 테이블에 입력되는 메소드
		signUpService.createSignUpTeacherQueue(signUpForm);	
		return "redirect:/teacherLogin";
	}	
	
	// 운영자 회원가입 페이지로 이동하는 메소드
	// 리턴값: 운영자 회원가입 뷰
	@GetMapping("/signUpManager")
	public String signUpManager() {
		return "account/signUpManager";
	}
	
	// 운영자 회원가입시 입력정보를 계정,운영자승인대기 테이블에 입력하는 메소드
	// 매개변수: 회원가입폼에서 입력된 값
	// 리턴값: 운영자로그인페이지	
	@PostMapping("/signUpManager")
	public String signUpManagerAction(SignUpForm signUpForm) {
		// account(계정) 테이블에 입력되는 메소드
		signUpService.createSignUpAccount(signUpForm);
		// student_queue(학생승인대기) 테이블에 입력되는 메소드
		signUpService.createSignUpManagerQueue(signUpForm);	
		return "redirect:/managerLogin";
	}
}
