package gd.fintech.lms.dto;

import lombok.Data;

// 회원가입 입력폼 처리를 위한 DTO

@Data
public class SignUpForm {
	// 계정 아이디
	private String accountId;
	
	// 계정 비밀번호
	private String accountPw;
	
	// 계정 권한
	private int accountLevel;
	
	// 계정 이메일
	private String accountEmail;
	
	// 사용자 이름
	private String accountName;
	
	// 사용자 전화번호
	private String accountPhone;
	
	// 사용자 성별
	private String accountGender;
	
	// 사용자 생년월일
	private String accountBirth;
	
	// 사용자 메인주소
	private String accountAddressMain;
	
	// 사용자 상세주소
	private String accountAddressSub;
}
