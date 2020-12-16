package gd.fintech.lms.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 회원가입 처리를 위한 컨트롤러 클래스

@Controller
public class SignUpController {

	// 학생 회원가입 페이지로 이동하는 메소드
	// 리턴값: 학생 회원가입 뷰
	@GetMapping("/signUpStudent")
	public String signUpStudent() {
		return "signUpStudent";
	}
}
