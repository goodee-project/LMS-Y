package gd.fintech.lms.account.vo;

import lombok.Data;

// 자격증(운영자,강사,학생) 정보가 담긴 VO

@Data
public class License {
	// 자격증 순번(AUTO_INCREMENT, PK)
	private int licenseNo;			
	
	// 계정 아이디(FK)
	private String accountId;		
	
	// 자격증 일련번호
	private String licenseNumber;	
	
	// 자격증명
	private String licenseName;		
	
	// 발급기관
	private String licenseAgency;	
	
	// 발급일
	private String licenseGetDate;	
	
}
