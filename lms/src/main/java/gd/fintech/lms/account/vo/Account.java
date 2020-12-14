package gd.fintech.lms.account.vo;

import lombok.Data;

// 모든 계층의 계정 정보 vo
@Data
public class Account {
	private String accountId;	// 계정 아이디
	private String accountPw;	// 계정 비밀번호
	private String accountState;	// 계정 상태
	private int accountLevel;		// 계정 등급
	private String accountCreatedate;	// 계정 생성일
	private String accountUpdatedate;	// 계정 수정일
}
