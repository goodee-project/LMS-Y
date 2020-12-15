package gd.fintech.lms.student.vo;

import lombok.Data;
//학생이 질문을 등록하는 질문 게시판 vo
@Data
public class Question {
	
	private int questionNo;				// 질문번호
	private int lectureNo;				// 강좌번호
	private String accountId;			// 학생의 id
	private String questionWriter;		// 질문 작성자
	private String questionTitle;		// 질문 제목
	private String questionContext;		//질문 내용
	private String questionCreateDate;	//질문 생성 날짜
	private String questionUpdateDate;	//질문 수정 날짜
	private int questionCount;			//질문 조회수
	private String questionPassword;	// 질문 비밀번호 (비밀글 사용시)
	//리스트 끌고오기
	
}
