package gd.fintech.lms.account.vo;

import lombok.Data;

// 학력(운영자,강사) 정보가 담긴 VO

@Data
public class Education {
	// 학력번호(AUTO_INCREMENT, PK)
	private String educationNo;		
	
	// 계정 아이디(FK)
	private String accountId;		
	
	// 출신학교
	private String educationSchool;	
	
	// 전공
	private String educationMajor;	
	
	// 입학일
	private String educationStartDate;	
	
	// 졸업일
	private String educationEndDate;	
}
