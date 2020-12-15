package gd.fintech.lms.account.vo;

import lombok.Data;

// 경력(운영자,강사)의 정보가 담긴 VO

@Data
public class Career {
	// 경력 번호(AUTO_INCREMENT, PK)
	private int careerNo;			
	
	// 계정 아이디(FK)
	private String accountId;		
	
	// 경력 내용
	private String careerContent;	
	
	// 경력 시작일
	private String careerStartDate;	
	
	// 경력 끝일
	private String careerEndDate;	
}
