package gd.fintech.lms.message.service;

import java.util.HashMap;
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
	// 매개변수: 로그인한 아이디(수신자), 현재 페이지
	// 리턴값: 쪽지리스트와 페이징 정보
	public Map<String, Object> getReceiveMessageList(String toId, int currentPage) {
		int rowPerPage = 10;	// 한 페이지에 보여지는 리스트 행의 수
		int beginRow = (currentPage-1)*rowPerPage;	// 시작 페이지
		// 받은 쪽지 리스트를 가져오기
		Map<String, Object> listMap = new HashMap<>();
		listMap.put("toId", toId);
		listMap.put("beginRow", beginRow);
		listMap.put("rowPerPage", rowPerPage);
		List<Message> list = messageMapper.selectReceiveMessageList(listMap);
		
		// 페이징을 위한 lastPage, 쪽지 리스트, 네이게이션 페이징 map에 담아서 넘기기
		Map<String, Object> map = new HashMap<>();
		// 전체 ROW 개수 가져오기
		int totalRow = messageMapper.selectCountReceiveMassageByAccountId(toId);
		// 마지막 페이지 구하기
		int lastPage = totalRow/rowPerPage;
		if(totalRow%rowPerPage != 0) {
			lastPage += 1;
		}		
		// 네비게이션 페이징
		int navPerPage = 10;	// 네비게이션 인덱스 개수
		int navStartPage = currentPage-(currentPage%navPerPage)+1;	// 시작 인덱스
		int navEndPage = navStartPage+(navPerPage-1);	// 마지막 인덱스
		// 현재 페이지 위치가 navPerPage 위치라면 navStartPage 넘어가지 않고 navEndPage 위치에 고정
		if(currentPage%navEndPage == 0) {
			navStartPage = navStartPage-navPerPage;
			navEndPage = navEndPage-navPerPage;
		}
		// navEndPage가 마지막 페이지 값을 넘길 경우
		if(navEndPage > lastPage) {
			navEndPage = lastPage;
		}
		
		map.put("list", list);
		map.put("lastPage", lastPage);
		map.put("navPerPage", navPerPage);
		map.put("navStartPage", navStartPage);
		map.put("navEndPage", navEndPage);
		
		return map;
	}
	
	// 보낸 쪽지 리스트를 가져오는 서비스 메소드
	// 매개변수: 로그인한 아이디, 검색조건, 현재 페이지
	// 리턴값: 쪽지리스트와 페이징 정보
	public Map<String, Object> getSendMessageList(Map<String, Object> mapParam) {
		// map에 담겨진 값 변수에 담기(발신자,현재페이지,검색조건선택값,검색어)
		String fromId = mapParam.get("fromId").toString();
		int currentPage = Integer.parseInt(mapParam.get("currentPage").toString());
		String sel = null;
		if(mapParam.get("sel") != null) {
			sel = mapParam.get("sel").toString();
		}
		String search = null;
		if(mapParam.get("search") != null) {
			search = mapParam.get("search").toString();
		}		
		int rowPerPage = 10;	// 한 페이지에 보여지는 리스트 행의 수
		int beginRow = (currentPage-1)*rowPerPage;	// 시작 페이지
		int totalRow = 0;	// 페이지 ROW 개수를 담는 변수
		
		// 쪽지 리스트를 가져오기 위한 매개변수 map
		Map<String, Object> listMap = new HashMap<>();
		listMap.put("fromId", fromId);
		listMap.put("search", search);
		listMap.put("beginRow", beginRow);
		listMap.put("rowPerPage", rowPerPage);		
		
		List<Message> list = null;	// 쪽지 리스를 담는 변수
		// 선택된 검색 조건값이 아이디인 경우
		if(sel != null && sel.equals("id")) {
			// 쪽지 리스트 가져오기
			list = messageMapper.selectSendMessageListByToId(listMap);
			// 아이디 검색 조건에 따른 전체 ROW 개수 가져오기
			totalRow = messageMapper.selectCountSendMassageBySearchId(fromId,search);
		}
		// 선택된 검색 조건값이 내용인 경우
		else if(sel != null && sel.equals("content")) {
			// 쪽지 리스트 가져오기
			list = messageMapper.selectSendMessageListByMessageContent(listMap);
			// 내용 검색 조건에 따른 전체 ROW 개수 가져오기
			totalRow = messageMapper.selectCountSendMassageBySearchContent(fromId, search);
		}
		// 선택된 검색 조건값이 전체인 경우
		else if(sel == null || sel.equals("")){
			// 쪽지 리스트 가져오기
			list = messageMapper.selectSendMessageList(listMap);
			// 전체 ROW 개수 가져오기
			totalRow = messageMapper.selectCountSendMassageByAccountId(fromId);
		}
		
		// 마지막 페이지 구하기
		int lastPage = totalRow/rowPerPage;
		if(totalRow%rowPerPage != 0) {
			lastPage += 1;
		}		
		// 네비게이션 페이징
		int navPerPage = 10;	// 네비게이션 인덱스 개수
		int navStartPage = currentPage-(currentPage%navPerPage)+1;	// 시작 인덱스
		int navEndPage = navStartPage+(navPerPage-1);	// 마지막 인덱스
		// 현재 페이지 위치가 navPerPage 위치라면 navStartPage 넘어가지 않고 navEndPage 위치에 고정
		if(currentPage%navPerPage == 0) {
			navStartPage = navStartPage-navPerPage;
			navEndPage = navEndPage-navPerPage;
		}
		// navEndPage가 마지막 페이지 값을 넘길 경우
		if(navEndPage > lastPage) {
			navEndPage = lastPage;
		}
		// 페이징을 위한 lastPage, 쪽지 리스트, 네이게이션 페이징 map에 담아서 넘기기
		Map<String, Object> map = new HashMap<>();
		map.put("list", list);
		map.put("lastPage", lastPage);
		map.put("navPerPage", navPerPage);
		map.put("navStartPage", navStartPage);
		map.put("navEndPage", navEndPage);
		
		return map;
	}
	
	// 받은 쪽지 상세보기 내용을 가져오는 서비스 메소드
	// 매개변수: 쪽지 번호
	// 리턴값: 상세조회한 내용 목록
	public Message getReceiveMessageDetail(int messageNo) {
		return messageMapper.selectReceiveMessageDetail(messageNo);
	}

	// 보낸 쪽지 상세보기 내용을 가져오는 서비스 메소드
	// 매개변수: 쪽지 번호
	// 리턴값: 상세조회한 내용 목록
	public Message getSendMessageDetail(int messageNo) {
		return messageMapper.selectSendMessageDetail(messageNo);
	}
	
	// 쪽지 보내기 기능 서비스 메소드(받은 쪽지)
	// 매개변수: 입력폼에서 넘어온 쪽지 내용
	// 리턴값: 입력된 행의 수
	public int createReceiveMessage(Message message) {
		return messageMapper.insertReceiveMessage(message);
	}
	
	// 쪽지 보내기 기능 서비스 메소드(받은 쪽지)
	// 매개변수: 입력폼에서 넘어온 쪽지 내용
	// 리턴값: 입력된 행의 수
	public int createSendMessage(Message message) {
		return messageMapper.insertSendMessage(message);
	}
	
	// 받은 쪽지 삭제 기능을 위한 서비스 메소드
	// 매개변수: 쪽지 번호
	// 리턴값: 삭제된 행의 수
	public int removeReceiveMessageByMessageNo(int messageNo) {
		return messageMapper.deleteReceiveMessageByMessageNo(messageNo);
	}
	
	// 보낸 쪽지 삭제 기능을 위한 서비스 메소드
	// 매개변수: 쪽지 번호
	// 리턴값: 삭제된 행의 수
	public int removeSendMessageByMessageNo(int messageNo) {
		return messageMapper.deleteSendMessageByMessageNo(messageNo);
	}
	
	// 수신 쪽지 읽음으로 수정하는 서비스 메소드
	// 매개변수: 쪽지 번호, 수신자 아이디
	// 리턴값: 수정된 행의 수
	public int modifyReceiveMessageConfirm(Map<String, Object> map) {
		return messageMapper.updateReceiveMessageConfirm(map);
	}
	
	// 발신 쪽지 읽음으로 수정하는 서비스 메소드(수신자가 읽었을 떄)
	// 매개변수: 쪽지 번호, 발신자 아이디
	// 리턴값: 수정된 행의 수
	public int modifySendMessageConfirm(Map<String, Object> map) {
		return messageMapper.updateSendveMessageConfirm(map);
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
