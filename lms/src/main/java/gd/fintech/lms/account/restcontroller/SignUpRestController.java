package gd.fintech.lms.account.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gd.fintech.lms.account.restservice.SignUpRestService;

// 회원가입시 주소 정보를 가져오기 위한 비동기 컨트롤러 클래스

@RestController
public class SignUpRestController {
	// SignUpService 객체 주입
	@Autowired SignUpRestService signUpRestService;
	
	// 회원가입을 위한 주소 목록을 받아오는 메소드
	// 매개변수:
	// model(주소 목록 리스트),
	// RequestParam(넘겨진 우편번호)
	// 리턴값: 학생 회원가입 폼
	@GetMapping("/address")
	public List<String> addressListByZipCode(@RequestParam(value = "zipCode", required = true) String zipCode) {
		return signUpRestService.getAddressListByZipCode(zipCode);
	}
}