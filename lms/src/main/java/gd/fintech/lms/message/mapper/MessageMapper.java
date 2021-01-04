package gd.fintech.lms.message.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import gd.fintech.lms.message.vo.Message;

// 쪽지 기능을 구현하기 위한 메세지 매퍼 인터페이스

@Mapper
public interface MessageMapper {
	// 전체 받은 쪽지 리스트를 조회하는 메소드(받은쪽지함)
	// 매개변수: 로그인된 계정 ID(수신자), 페이징정보(시작ROW,ROW개수)
	// 리턴값: 받은 쪽지의 리스트 결과
	List<Message> selectReceiveMessageList(Map<String, Object> map);
	
	// 전체 보낸 쪽지 리스트를 조회하는 메소드(보낸쪽지함)
	// 매개변수: 로그인된 계정 ID(발신자), 페이징정보(시작ROW,ROW개수)
	// 리턴값: 보낸 쪽지의 리스트 결과
	List<Message> selectSendMessageList(Map<String, Object> map);
	
	// 검색조건(수신자아이디)에 따른 보낸 쪽지 리스트를 조회하는 메소드(보낸쪽지함)
	// 매개변수: 로그인된 계정 ID(발신자), 수신자ID, 페이징정보(시작ROW,ROW개수)
	// 리턴값: 검색조건에 따른 보낸 쪽지의 리스트 결과
	List<Message> selectSendMessageListByToId(Map<String, Object> map);
	
	// 검색조건(내용)에 따른 보낸 쪽지 리스트를 조회하는 메소드(보낸쪽지함)
	// 매개변수: 로그인된 계정 ID(발신자), 쪽지내용, 페이징정보(시작ROW,ROW개수)
	// 리턴값: 검색조건에 따른 보낸 쪽지의 리스트 결과
	List<Message> selectSendMessageListByMessageContent(Map<String, Object> map);
		
	// 받은 쪽지 상세보기 메소드
	// 매개변수: 쪽지 번호
	// 리턴값: 쪽지 상세보기 내용
	Message selectReceiveMessageDetail(int messageNo);
	
	// 보낸 쪽지 상세보기 메소드
	// 매개변수: 쪽지 번호
	// 리턴값: 쪽지 상세보기 내용
	Message selectSendMessageDetail(int messageNo);

	// 쪽지 보내기 기능을 하는 메소드(받은쪽지 입력)
	// 매개변수: Message 정보(발신자, 수신자, 발신자이름, 메세지내용)
	// 리턴값: 메세지 입력한 행
	int insertReceiveMessage(Message message);	
	
	// 쪽지 보내기 기능을 하는 메소드(보낸쪽지 입력)
	// 매개변수: Message 정보(발신자, 수신자, 메세지내용)
	// 리턴값: 메세지 입력한 행
	int insertSendMessage(Message message);
	
	// 수신 쪽지 삭제 기능을 하는 메소드
	// 매개변수: 쪽지 번호
	// 리턴값: 삭제된 쪽지 결과 행
	int deleteReceiveMessageByMessageNo(int messageNo);
	
	// 발신 쪽지 삭제 기능을 하는 메소드
	// 매개변수: 쪽지 번호
	// 리턴값: 삭제된 쪽지 결과 행
	int deleteSendMessageByMessageNo(int messageNo);

	// 수신 쪽지 확인상태를 읽음으로 수정하는 메소드
	// 매개변수: 쪽지 번호(messageNo), 수신자 아이디(toId)
	// 리턴값: 쪽지 상태를 읽음으로 변경한 행
	int updateReceiveMessageConfirm(Map<String, Object> map);	
	
	// 발신 쪽지 확인상태를 읽음으로 수정하는 메소드
	// 매개변수: 쪽지 번호(messageNo), 발신자 아이디(fromId)
	// 리턴값: 쪽지 상태를 읽음으로 변경한 행
	int updateSendveMessageConfirm(Map<String, Object> map);
	
	// 쪽지보내기 위한 수신자 아이디 정보를 검색하는 메소드
	// 매개변수: 계정 ID
	// 리턴값: 아이디로 조회한 정보(아이디,이름)
	Map<String, Object> selectReceiverInfoByAccountId(String accountId);
	
	// 발신자의 정보를 조회하는 메소드(수신자 확인용)
	// 매개변수: 계정 ID
	// 리턴값: 아이디로 조회한 정보(아이디,이름)
	Map<String, Object> selectCallerInfoAccountId(String accountId);
	
	// 받은쪽지함 전체 수(페이징)
	// 매개변수: 발신자 아이디
	// 리턴값: 발신함 쪽지 전체 개수
	int selectCountReceiveMassageByAccountId(String accountId);
	
	// 보낸쪽지함 전체 수(페이징)
	// 매개변수: 수신자 아이디
	// 리턴값: 수신함 쪽지 전체 개수
	int selectCountSendMassageByAccountId(String accountId);
}
