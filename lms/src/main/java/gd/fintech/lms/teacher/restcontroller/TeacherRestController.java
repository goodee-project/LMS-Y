package gd.fintech.lms.teacher.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gd.fintech.lms.teacher.service.TeacherService;

//비밀번호 변경시 비밀번호 정보를 가져오기 위한 비동기 컨트롤러 클래스

@RestController
public class TeacherRestController {
		//Logger
		private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
		//TeacherService 객체 주입
		@Autowired private TeacherService teacherService;
		
		//비밀번호 확인을 위한 메서드
		//매개변수:강사ID,비밀번호
		//리턴값:승인 또는 비승인
		@PostMapping("/teacher/teacherPwCk")
		public String teacherPwCk(@RequestParam(value="accountId",required = true)String accountId,
							@RequestParam(value="accountPw",required = true)String accountPw) {
			String pwCk = teacherService.getTeacherPw(accountId, accountPw);
			//Logger 디버깅
			logger.debug("비밀번호 확인 메서드 디버깅-->"+pwCk);
			
			return pwCk;
		}
		
		//이메일 중복체를 위한 메서드
		//매개변수:계정 아이디,이메일
		//리턴값:승인 또는 비승인
		@PostMapping("/teacher/teacherEmailCk")
		public String teacherEmailCk(@RequestParam(value="accountId",required = true)String accountId,
				@RequestParam(value="teacherEmail",required = true)String teacherEmail) {
			String emailCk = teacherService.getTeacherEmail(accountId, teacherEmail);
			if(emailCk ==null) {
				return "pass";
			}
			return "noPass";
		}
		
}
