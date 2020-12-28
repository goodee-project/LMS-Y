package gd.fintech.lms.message.vo;

import lombok.Data;

// 메세지(쪽지) 정보를 담는 VO 클래스

@Data
public class Message {
	// 쪽지 번호(AUTO_INCREMENT, PK)
	private int messageNo;
	
	// 발신자 ID(FK)
	private String fromId;
	
	// 수신자 ID(FK)
	private String toId;
	
	// 발신자 이름
	private String fromName;
	
	// 쪽지 내용
	private String messageContent;
	
	// 쪽지 보낸 시각
	private String messageDateTime;
	
	// 쪽지 확인 여부
	private String isConfirm;
}
