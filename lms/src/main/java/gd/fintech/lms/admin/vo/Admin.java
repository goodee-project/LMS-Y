package gd.fintech.lms.admin.vo;

import lombok.Data;

// 관리자 개인정보 vo

@Data
public class Admin {
	// 관리자 아이디
	private String accountId;
	
	// 관리자 이메일
	private String adminEmail;
	
	// 관리자 전화번호
	private String adminPhone;
	
	// 관리자 이름
	private String adminName;
}
