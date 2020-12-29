package gd.fintech.lms.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import gd.fintech.lms.message.service.MessageService;
import gd.fintech.lms.message.vo.Message;

// 쪽지 기능을 구현하기 위한 컨트롤러 클래스

@Controller
public class MessageController {
	// MessageService 객체 주입
	@Autowired private MessageService messageService;
	
	// 받은 쪽지함 페이지로 이동하는 메소드
	// 매개변수: 로그인한 계정 아이디
	// 리턴값: 받은 쪽지함 리스트 페이지
	@GetMapping("/receiveMessage")
	public String receiveMessageList(String toId) {
		return "receiveMessage";
	}
	
	// 보낸 쪽지함 페이지로 이동하는 메소드
	// 매개변수: 로그인한 계정 아이디
	// 리턴값: 보낸 쪽지함 리스트 페이지
	@GetMapping("/sendMessage")
	public String sendMessageList(String fromId) {
		return "sendMessage";
	}
	
	// 쪽지 보내기 액션을 수행하는 메소드
	// 매개변수: 입력폼에서 입력된 값
	// 리턴값: 쪽지 보내기 입력 수행한 행
	@PostMapping("sendMessage")
	public String sendMessageAction(Message message) {
		return "";
	}
	
	// 쪽지 보내기 입력폼으로 이동하는 메소드
	// 리턴값: 쪽지 보내기 페이지
	@GetMapping("/messageForm")
	public String messageForm() {
		return "messageForm";
	}
}
