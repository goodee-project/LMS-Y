package gd.fintech.lms.student.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gd.fintech.lms.student.service.StudentService;

@RestController
public class StudentRestController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired StudentService studentService;

	//비밀번호 확인을 위한 메서드
	//매개변수:강사ID,비밀번호
	//리턴값:승인 또는 비승인
	@PostMapping("/student/studentPwCk")
	public String studentPwCk(@RequestParam(value="accountId",required = true)String accountId,
						@RequestParam(value="accountPw",required = true)String accountPw) {
		String pwCk = studentService.getStudentPw(accountId, accountPw);
		//Logger 디버깅
		logger.debug("비밀번호 확인 메서드 디버깅-->"+pwCk);
		
		return pwCk;
	}
	
	//이메일 중복체를 위한 메서드
	//매개변수:계정 아이디,이메일
	//리턴값:승인 또는 비승인
	@PostMapping("/student/studentEmailCk")
	public String studentEmailCk(@RequestParam(value="accountId",required = true)String accountId,
			@RequestParam(value="studentEmail",required = true)String studentEmail) {
		String emailCk = studentService.getStudentEmail(accountId, studentEmail);
		if(emailCk ==null) {
			return "pass";
		}
		return "noPass";
	}
}
