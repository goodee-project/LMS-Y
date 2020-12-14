package gd.fintech.lms.admin.vo;

import lombok.Data;

// 관리자 정보 관련 vo
@Data
public class Admin {
	private String adminId;		// 관리자 아이디
	private String adminEmail;	// 관리자 이메일
	private String adminPhone;	// 관리자 전화번호
	private String adminName;	// 관리자 이름
}
