package gd.fintech.lms.admin.vo;

import lombok.Data;

// 회원가입 승인대기 중인 운영자의 개인정보 vo

@Data
public class ManagerQueue {
	// 운영자 아이디
	private String accountId;
	
	// 운영자 이메일
	private String managerEmail;
	
	// 운영자 이름
	private String managerName;
	
	// 운영자 전화번호
	private String managerPhone;
	
	// 운영자 성별
	private String managerGender;
	
	// 운영자 생년월일
	private String managerBirth;
	
	// 운영자 메인주소
	private String managerAddressMain;
	
	// 운영자 서브주소
	private String managerAddressSub;
}
