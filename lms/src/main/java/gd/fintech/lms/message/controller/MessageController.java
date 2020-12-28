package gd.fintech.lms.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import gd.fintech.lms.message.service.MessageService;

// 쪽지 기능을 구현하기 위한 컨트롤러 클래스

@Controller
public class MessageController {
	// MessageService 객체 주입
	@Autowired private MessageService messageService;
	
	// 받은 쪽지함 페이지로 이동하는 메소드
	// 매개변수: 로그인한 계정 아이디
	// 리턴값: 받은 쪽지함 리스트 페이지
	@GetMapping("/receiveMessage")
	public String messageListByToId(String toId) {
		return "receiveMessage";
	}
}
