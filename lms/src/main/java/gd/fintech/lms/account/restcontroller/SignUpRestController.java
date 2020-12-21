package gd.fintech.lms.account.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gd.fintech.lms.account.restservice.SignUpRestService;
import gd.fintech.lms.account.vo.Address;

// 회원가입 처리를 위한 컨트롤러 클래스

@Controller
public class SignUpRestController {
	// SignUpService 객체 주입
	@Autowired SignUpRestService signUpRestService;
	
	// 회원가입을 위한 주소 목록을 받아오는 메소드
	// 매개변수:
	// model(주소 목록 리스트),
	// RequestParam(넘겨진 우편번호)
	// 리턴값: 학생 회원가입 폼
	@GetMapping("/address")
	public String addressListByZipCode(Model model,
			@RequestParam(value = "zipCode", required = true) String zipCode) {
		System.out.println(zipCode + ": 입력된 우편번호");
		List<String> list =  signUpRestService.getAddressListByZipCode(zipCode);
		model.addAttribute("list", list);
		return "signUpStudent";
	}
}
