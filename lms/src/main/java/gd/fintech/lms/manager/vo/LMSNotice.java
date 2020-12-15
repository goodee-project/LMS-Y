package gd.fintech.lms.manager.vo;

import lombok.Data;

// lms 공지vo : 운영자가 관리하는 lms의 공지사항 vo

@Data
public class LMSNotice {
	// LMS공지사항 번호
	private int lmsNoticeNo;
	
	// 작성자 ID
	private String accountId;
	
	// 작성자 이름
	private String lmsNoticeWriter;
	
	// 공지사항 제목
	private String lmsNoticeTitle; 
	
	// 공지사항 내용
	private String lmsNoticeContent; 
	
	// 공지사항 조회수 
	private int lmsNoticeCount;
	
	// 공지사항 입력날짜
	private String lmsNoticeCreateDate; 
	
	// 공지사항 수정날짜
	private String lmsNoticeUpdateDate; 
}
