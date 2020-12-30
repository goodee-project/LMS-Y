package gd.fintech.lms.message.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gd.fintech.lms.message.mapper.MessageMapper;
import gd.fintech.lms.message.vo.Message;

// 쪽지 기능을 구현하기 위한 메세지 서비스 클래스

@Service
@Transactional
public class MessageService {
	// MessageMapper 객체를 주입
	@Autowired private MessageMapper messageMapper;
	
	// 받은 쪽지 리스트를 가져오는 서비스 메소드
	// 매개변수: 로그인한 아이디
	// 리턴값: 받은 쪽지 리스트
	public List<Message> getReceiveMessageList(String toId) {
		return messageMapper.selectReceiveMessageList(toId);
	}
	
	// 보낸 쪽지 리스트를 가져오는 서비스 메소드
	// 매개변수: 로그인한 아이디
	// 리턴값: 보낸 쪽지 리스트
	public List<Message> getSendMessageList(String fromId) {
		return messageMapper.selectSendMessageList(fromId);
	}
	
	// 쪽지 상세보기 내용을 가져오는 서비스 메소드
	// 매개변수: 쪽지 번호
	// 리턴값: 상세조회한 내용 목록
	public Message getMessageDetail(int messageNo) {
		return messageMapper.selectMessageDetail(messageNo);
	}
	
	// 쪽지 보내기 기능 서비스 메소드
	// 매개변수: 입력폼에서 넘어온 쪽지 내용
	// 리턴값: 입력된 행의 수
	public int createSendMessage(Message message) {
		return messageMapper.insertSendMessage(message);
	}
	
	// 쪽지 삭제 기능을 위한 서비스 메소드
	// 매개변수: 쪽지 번호
	// 리턴값: 삭제된 행의 수
	public int removeMessageByMessageNo(int messageNo) {
		return messageMapper.deleteMessageByMessageNo(messageNo);
	}
	
	// 쪽지 읽음으로 수정하는 서비스 메소드
	// 매개변수: 쪽지 번호, 수신자 아이디
	// 리턴값: 수정된 행의 수
	public int modifyMessageConfirm(Map<String, Object> map) {
		return messageMapper.updateMessageConfirm(map);
	}
	
	// 쪽지 보내기 위해 수신자 계정 정보를 검색하는 메소드
	// 매개변수: 수신자 아이디
	// 리턴값: 아이디로 조회한 계정 정보(아이디,이름)
	public Map<String, Object> getReceiverInfo(String accountId) {
		return messageMapper.selectReceiverInfoByAccountId(accountId);
	}
	
	// 발신자의 계정 정보를 조회하는 메소드
	// 매개변수: 발신자 아이디
	// 리턴값: 아이디로 조회한 계정 정보(아이디,이름)
	public Map<String, Object> getCallerInfo(String accountId) {
		return messageMapper.selectCallerInfoAccountId(accountId);
	}
}
