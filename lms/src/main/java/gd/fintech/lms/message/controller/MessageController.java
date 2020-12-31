package gd.fintech.lms.message.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	public String receiveMessageList(HttpSession session, Model model) {
		String toId = session.getAttribute("accountId").toString();
		List<Message> receiveList = messageService.getReceiveMessageList(toId);
		model.addAttribute("list", receiveList);
		return "receiveMessage";
	}
	
	// 보낸 쪽지함 페이지로 이동하는 메소드
	// 매개변수: 로그인한 계정 아이디
	// 리턴값: 보낸 쪽지함 리스트 페이지
	@GetMapping("/sendMessage")
	public String sendMessageList(HttpSession session, Model model) {
		String fromId = session.getAttribute("accountId").toString();
		List<Message> sendList = messageService.getSendMessageList(fromId);
		model.addAttribute("list", sendList);
		return "sendMessage";
	}
	
	// 받은 쪽지 상세보기를 위한 메소드
	// 매개변수: 쪽지 번호, 수신자 아이디
	// 리턴값: 쪽지 상세 내용 리스트
	@PostMapping("/receiveMessageDetail")
	public String receiveMessageDetail(Model model, @RequestParam(value = "messageNo", required = true) int messageNo, 
			@RequestParam(value = "id", required = true) String id) {
		// 메세지 상세정보 가져오기
		model.addAttribute("message", messageService.getReceiveMessageDetail(messageNo));
		// 메세지 읽음 상태로 변경
		Map<String, Object> map = new HashMap<>();
		map.put("messageNo", messageNo);
		map.put("id", id);
		messageService.modifyReceiveMessageConfirm(map);
		messageService.modifySendMessageConfirm(map);
		return "messageDetail";
	}
	
	// 보낸 쪽지 상세보기를 위한 메소드
	// 매개변수: 쪽지 번호, 발신자 아이디
	// 리턴값: 쪽지 상세 내용 리스트
	@PostMapping("/sendMessageDetail")
	public String sendMessageDetail(Model model, @RequestParam(value = "messageNo", required = true) int messageNo) {
		// 메세지 상세정보 가져오기
		model.addAttribute("message", messageService.getSendMessageDetail(messageNo));
		return "messageDetail";
	}
	
	// 쪽지 보내기 입력폼으로 이동하는 메소드
	// 리턴값: 쪽지 보내기 페이지
	@PostMapping("/messageForm")
	public String messageForm(@RequestParam(value = "accountId", required = true) String accountId, Model model) {
		// 발신자의 정보를 가져오는 메소드
		Map<String, Object> map = messageService.getCallerInfo(accountId);
		model.addAttribute("fromId", map.get("fromId"));
		model.addAttribute("fromName", map.get("fromName"));
		return "messageForm";
	}
	
	// 쪽지 보내기 액션을 수행하는 메소드
	// 매개변수: 입력폼에서 입력된 값
	// 리턴값: 쪽지 보내기 입력 수행한 행
	@PostMapping("/sendMessage")
	public String sendMessageAction(Message message) {
		// 수신,발신 DB에 메세지 정보 입력
		messageService.createReceiveMessage(message);
		messageService.createSendMessage(message);
		return "redirect:/sendMessage";
	}
	
	// 받은 쪽지 삭제를 위한 메소드
	// 매개변수: 쪽지 번호
	// 리턴값: 삭제가 된 행
	@GetMapping("/removeReceiveMessage")
	public String removeReceiveMessage(@RequestParam(value = "messageNo", required = true) int messageNo) {
		messageService.removeReceiveMessageByMessageNo(messageNo);
		return "redirect:/receiveMessage";
	}
	
	// 보낸 쪽지 삭제를 위한 메소드
	// 매개변수: 쪽지 번호
	// 리턴값: 삭제가 된 행
	@GetMapping("/removeSendMessage")
	public String removeSendMessage(@RequestParam(value = "messageNo", required = true) int messageNo) {
		messageService.removeSendMessageByMessageNo(messageNo);
		return "redirect:/sendMessage";
	}
}
