package gd.fintech.lms.manager.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gd.fintech.lms.manager.service.ManagerService;

//운영자 정보 관련 비동기 Controller

@RestController
public class ManagerRestController {
	
	// 운영자 관련 Service
	@Autowired ManagerService managerService;
	
	// 운영자의 비밀번호를 수정할때 확인하는 메소드
	// 매개변수: 운영자의 계정id, 계정pw
	// 리턴값: 승인 또는 비승인
	@PostMapping("/manager/managerPwCheck")
	public String managerPasswordCheck(@RequestParam(value="accountId",required= true)String accountId,
			@RequestParam(value="accountPw",required= true)String accountPw){
		String managerPwck = managerService.getManagerPassword(accountId, accountPw);
		return managerPwck;
	}

}