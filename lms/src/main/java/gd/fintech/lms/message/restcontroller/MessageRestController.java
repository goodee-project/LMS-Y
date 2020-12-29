package gd.fintech.lms.message.restcontroller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gd.fintech.lms.message.service.MessageService;

// 쪽지를 보내기 위해 사용자 정보를 검색하는 비동기 컨트롤러

@RestController
public class MessageRestController {
	// MessageService 객체 주입
	@Autowired MessageService messageService;
	
	// 수신자의 계정 정보(아이디, 이름) 조회하는 메소드
	// 매개변수: 수신자 아이디
	// 리턴값: 계정 정보(아이디, 이름)
	@GetMapping("/serchToId")
	public Map<String, Object> accountInfo(@RequestParam(value = "accountId", required = true) String accountId) {
		return messageService.getAccountInfo(accountId);
	}
}
