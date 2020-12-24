package gd.fintech.lms.account.vo;

import lombok.Data;

// 로그인 정보를 담는 VO 클래스

@Data
public class LoginLog {
	// 로그인한 세션 아이디값
	private String loginId;
	
	// 로그인한 아이디
	private String accountId;
	
	// 로그인한 날짜시각
	private String loginDateTime;
	
	// 로그아웃한 날짜시각
	private String logoutDateTime;
}
