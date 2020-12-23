package gd.fintech.lms.account.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gd.fintech.lms.account.service.SignUpService;

// 회원가입시 주소 정보를 가져오기 위한 비동기 컨트롤러 클래스

@RestController
public class SignUpRestController {
	// SignUpService 객체 주입
	@Autowired SignUpService signUpService;
	
	// 아이디 중복체크를 위한 메소드
	// 매개변수: 계정 아이디
	// 리턴값: 회원가입페이지
	@PostMapping("/accountIdCheck")
	public String accountIdCheck(@RequestParam(value = "accountId", required = true) String accountId) {
		String idCk = signUpService.getAccountId(accountId);
		if(idCk == null) {
			return "pass";
		}
		return "noPass";
	}
	
	// 이메일 중복체를 위한 메소드
	// 매개변수: 계정 이메일
	// 리턴값: 회원가입페이지
	@PostMapping("/accountEmailCheck")
	public String accountEmailCheck(@RequestParam(value = "accountEmail", required = true) String accountEmail) {
		return "noPass";
	}
	
	// 회원가입을 위한 주소 목록을 받아오는 메소드
	// 매개변수:
	// model(주소 목록 리스트),
	// RequestParam(넘겨진 우편번호)
	// 리턴값: 학생 회원가입 폼
	@GetMapping("/address")
	public List<String> addressListByZipCode(@RequestParam(value = "zipCode", required = true) String zipCode) {
		return signUpService.getAddressListByZipCode(zipCode);
	}
}
