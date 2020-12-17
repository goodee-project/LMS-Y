package gd.fintech.lms.manager.vo;

import lombok.Data;

// 운영자 vo : 운영자 정보 관련 vo

@Data
public class Manager {
	// 계정 id
	private String accountId; 
	
	// 운영자 E-mail
	private String managerEmail;
	
	// 운영자 이름
	private String managerName;
	
	// 운영자 핸드폰 번호
	private String managerPhone;
	
	// 운영자 성별 
	private String managerGender;
	
	// 운영자 생일
	private String managerBirth;
	
	// 운영자 직책
	private String managerPosition; 
	
	// 운영자 주소
	private String managerAddressMain;
	
	// 운영자 상세 주소
	private String managerAddressSub;
	
	// 운영자 프로필 이미지
	private String managerImage; 
	
	// 운영자 승인 날짜
	private String managerAccessDate;
	
	// 운영자 개인정보 수정 날짜
	private String managerUpdateDate; 
	
}
