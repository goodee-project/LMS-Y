package gd.fintech.lms.account.vo;

import lombok.Data;

// 모든 계층의 등록된 계정 정보를 담는 VO

@Data
public class Account {
	// 계정 아이디(PK)
	private String accountId;
	
	// 계정 비밀번호
	private String accountPw;	
	
	// 계정 이메일
	private String accountEmail;
	
	// 계정 상태(활성화, 비활성화, 승인대기)
	private String accountState;
	
	// 계정 권한등급(학생:1, 강사:2, 운영자:3, 관리자:4)
	private int accountLevel;
	
	// 계정 생성일
	private String accountCreateDate;
	
	// 계정 수정일
	private String accountUpdateDate;
}
